package com.testmonkeys.demo.service;

import com.mysql.cj.util.StringUtils;
import com.testmonkeys.demo.dto.AccountDTO;
import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.entity.UserProject;
import com.testmonkeys.demo.enums.PositionEnum;
import com.testmonkeys.demo.exception.BadRequestParametersException;
import com.testmonkeys.demo.mapper.UserMapper;
import com.testmonkeys.demo.repo.ProjectFolderRepository;
import com.testmonkeys.demo.repo.ProjectRepository;
import com.testmonkeys.demo.repo.UserRepository;
import com.testmonkeys.demo.utils.Logger;
import com.testmonkeys.demo.utils.RoleType;
import com.testmonkeys.demo.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.testmonkeys.demo.utils.UserValidator.checkAvatarForUser;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Value("18")
    private Integer vacationDaysFromStart;

    @Autowired
    private ProjectFolderRepository projectFolderRepository;


    private final static Pattern emailPatter = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not exist"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAllActivatedBEMiddleDevs() {
        final Predicate<User> middleJavaDeveloper = u -> Objects.nonNull(u)
                && u.isActivated()
                && Objects.nonNull(u.getPosition())
                && u.getPosition().contains("Middle Java developer");
        return userRepository.findAll().stream()
                .filter(middleJavaDeveloper)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public UserDTO createUser(final UserDTO userDTO) {
        validateUserDTO(userDTO);
        final User user = UserMapper.fromDTO(userDTO);
        return UserMapper.toDTO(userRepository.save(user));
    }

    public void validateUser(AccountDTO user) {
        UserValidator.checkEmailEquality(user.getEmail(), user.getPersonalEmail());
        UserValidator.checkPhonesEquality(user.getPhoneOne(), user.getPhoneTwo());
        UserValidator.checkDatesValidity(user.getBirthDate(), user.getStartsWork());
        UserValidator.checkRoles(user.getRoles());

        if ((!userValidator.checkEmailUser(user.getEmail(), user.getId()) || (!userValidator.checkEmailUser(user.getPersonalEmail(),user.getId())))) {
            throw new BadRequestParametersException("Email is already in use");
        }

        if ((!userValidator.checkPhoneUser(user.getPhoneOne(), user.getId())
                || (!userValidator.checkPhoneUser(user.getPhoneTwo(),user.getId())))) {
            throw new BadRequestParametersException("Phone is already in use");
        }

        if ((!userValidator.checkSkypeUser(user.getSkype(), user.getId()))) {
            throw new BadRequestParametersException("Skype is already in use");
        }
    }

    @Override
    @Transactional
    public UserDTO createNewUserByRecruiter(@Valid AccountDTO accountDTO) {
        validateUser(accountDTO);
        checkAvatarForUser(accountDTO.getBase64Avatar());
        User mainManger = null;
        User hr = null;
        if (!accountDTO.getRoles().contains(RoleType.ROLE_TOP_MANAGER.getValue())) {
            mainManger = userRepository
                    .findOneByEmail(accountDTO.getManagerEmail())
                    .orElseThrow(() -> new RuntimeException("User with email" + accountDTO.getManagerEmail() + "does not exists"));
            if (!accountDTO.getPosition().equalsIgnoreCase(PositionEnum.HR_COORDINATOR.getValue())) {
                hr = userRepository
                        .findOneByEmail(accountDTO.getHrEmail())
                        .orElseThrow(() -> new RuntimeException("No Hr with this email"));
            }
        }
        User mentor = userRepository.findByEmail(accountDTO.getMentorEmail());
        User user = createFromAccountDTO(accountDTO, mentor, hr, mainManger);
        UserValidator.checkAndValidateOffice(user, accountDTO);
        setProjectsToUser(user, accountDTO.getProjectsId());
        userRepository.save(user);

        Logger.info("id = {}", user.getId());
        return UserMapper.toDTO(user);
    }

    public User createFromAccountDTO(AccountDTO accountDTO, User mentor, User hr, User mainManger) {
        User user = AccountDTO.createUser(accountDTO);
        user.setMyHR(hr);
        user.setMentor(mentor);
        user.setMainManager(mainManger);
        user.setPosition(accountDTO.getPosition());
        user.setDepartment(projectFolderRepository.findFirstById(1l));
        user.setPassword(passwordEncoder.encode("sombra"));
        user.setVacationDays(vacationDaysFromStart);
        return user;
    }


    private void validateUserDTO(final UserDTO userDTO) {
        final String email = Optional.ofNullable(userDTO)
                .orElseThrow(() -> new IllegalArgumentException("User dto cannot be null"))
                .getEmail();
        if (StringUtils.isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email cannot be blank or empty");
        }
        if (!emailPatter.matcher(email).find()) {
            throw new IllegalArgumentException("Email format is invalid!");
        }
        if(Objects.nonNull(userRepository.findByEmail(email))){
            throw new InternalError("Email already exists");
        }
    }






    public void setProjectsToUser(User user, List<Long> projectsId) {
        if (Objects.nonNull(projectsId)) {
            final List<UserProject> userProjects = projectsId.stream()
                    .map(projectId -> getUserProject(user, projectId))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            user.setProjectsForUser(userProjects);
        }
    }

    public UserProject getUserProject(final User user, final Long projectId) {
        return projectRepository.findById(projectId)
                .map(project ->
                        new UserProject()
                                .setUser(user)
                                .setProject(project)
                                .setStartWork(LocalDateTime.now()))
                .orElse(null);
    }



}
