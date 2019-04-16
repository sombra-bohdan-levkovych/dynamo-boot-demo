package com.testmonkeys.demo.service;

import com.mysql.cj.util.StringUtils;
import com.testmonkeys.demo.dto.AccountDTO;
import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.entity.UserProject;
import com.testmonkeys.demo.enums.Office;
import com.testmonkeys.demo.enums.PositionEnum;
import com.testmonkeys.demo.mapper.UserMapper;
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
                && u.getPosition().getValue().contains("Middle Java developer");
        return userRepository.findAll().stream()
                .filter(middleJavaDeveloper)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public UserDTO createUser(final UserDTO userDTO) {
        validateUserDTO(userDTO);
        final User user = this.fromDTO(userDTO);
        return this.toDTO(userRepository.save(user));
    }

    private void validateUser(AccountDTO user) {
        userValidator.checkEmailEquality(user.getEmail(), user.getPersonalEmail());
        userValidator.checkPhonesEquality(user.getPhoneOne(), user.getPhoneTwo());
        userValidator.checkDatesValidity(user.getBirthDate(), user.getStartsWork());
        userValidator.checkRoles(user.getRoles());

        if ((!userValidator.checkEmailUser(user.getEmail(), user.getId()) || (!userValidator.checkEmailUser(user.getPersonalEmail(),
                user.getId())))) {
            throw new RuntimeException("Email is already in use");
        }

        if ((!userValidator.checkPhoneUser(user.getPhoneOne(), user.getId()) || (!userValidator.checkPhoneUser(user.getPhoneTwo(),
                user.getId())))) {
            throw new RuntimeException("Phone is already in use");
        }

        if ((!userValidator.checkSkypeUser(user.getSkype(), user.getId()))) {
            throw new RuntimeException("Skype is already in use");
        }

        if ((!userValidator.checkUniquenessOfHeadsOfOfficeAdministrations(user.getEmail(), user.getPosition(), user.getOffice()))) {
            throw new RuntimeException("Position is already occupied");
        }
    }

    @Override
    @Transactional
    public UserDTO createNewUserByRecruiter(@Valid AccountDTO accountDTO) {

        validateUser(accountDTO);
        userValidator.checkAvatarForUser(accountDTO.getBase64Avatar());
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
        User user = new User()
                .setFirstname(accountDTO.getFirstname())
                .setLastname(accountDTO.getLastname())
                .setPersonalEmail(accountDTO.getPersonalEmail())
                .setEmail(accountDTO.getEmail())
                .setPhoneOne(formatePhoneNumber(accountDTO.getPhoneOne()))
                .setPhoneTwo(accountDTO.getPhoneTwo() == null ? null : formatePhoneNumber(accountDTO.getPhoneTwo()))
                .setSkype(accountDTO.getSkype())
                .setMentor(mentor)
                .setMyHR(hr)
                .setMainManager(mainManger)
                .setActivated(true)
                .setPassword(passwordEncoder.encode("sombra"))
                .setVacationDays(vacationDaysFromStart);

        if (Office.isOfficeExist(accountDTO.getOffice())) {
            user.setOffice(accountDTO.getOffice().toUpperCase());
        }



        setProjectsToUser(user, accountDTO.getProjectsId());
        userRepository.save(user);

        Logger.info("id = {}", user.getId());
        return userMapper.toDTO(user);
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

    private UserDTO toDTO(final User user) {
        final String position = Optional.ofNullable(user)
                .orElseThrow(() -> new IllegalArgumentException("User cannot be null at the mapper"))
                .getPosition().getValue();
        final String rank = position.substring(0, position.indexOf(" "));
        return new UserDTO()
                .setId(user.getId())
                .setPosition(position.substring(position.indexOf(" ") + 1));
    }


    private String formatePhoneNumber(String phoneNumber) {
        return new StringBuilder(phoneNumber).insert(3, " ").insert(7, " ").insert(10, " ").toString();
    }

    private void setProjectsToUser(User user, List<Long> projectsId) {
        if (Objects.nonNull(projectsId)) {
            final List<UserProject> userProjects = projectsId.stream()
                    .map(projectId -> getUserProject(user, projectId))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            user.setProjectsForUser(userProjects);
        }
    }

    private UserProject getUserProject(final User user, final Long projectId) {
        return projectRepository.findById(projectId)
                .map(project ->
                        new UserProject()
                                .setUser(user)
                                .setProject(project)
                                .setStartWork(LocalDateTime.now()))
                .orElse(null);
    }

    private User fromDTO(UserDTO userDTO) {
        return new User().setId(userDTO.getId())
                .setFirstname(userDTO.getFirstname())
                .setPosition(PositionEnum.findByPosition(userDTO.getPosition()));
    }

}
