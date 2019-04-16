package com.testmonkeys.demo.service;

import com.mysql.cj.util.StringUtils;
import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.repo.UserRepository;
import org.apache.tomcat.util.file.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final static Pattern emailPatter = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

//    @Autowired
//    private UserMapper userMapper;

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
                && u.getActivated()
                && Objects.nonNull(u.getPosition())
                && u.getPosition().contains("Middle Java developer");
        return userRepository.findAll().stream()
                .filter(middleJavaDeveloper)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO createUser(final UserDTO userDTO) {
        validateUserDTO(userDTO);
        final User user = this.fromDTO(userDTO);
        return this.toDTO(userRepository.save(user));
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
                .getPosition();
        final String rank = position.substring(0, position.indexOf(" "));
        return new UserDTO()
                .setId(user.getId())
                .setName(user.getFirstname())
                .setPosition(position.substring(position.indexOf(" ") + 1))
                .setRank(rank);
    }

    private User fromDTO(UserDTO userDTO) {
        return new User().setId(userDTO.getId())
                .setFirstname(userDTO.getName())
                .setPosition(userDTO.getRank() + " " + userDTO.getPosition());
    }

}
