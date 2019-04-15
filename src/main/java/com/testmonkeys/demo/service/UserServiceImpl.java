package com.testmonkeys.demo.service;

import com.google.gson.Gson;
import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

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
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        final User user = fromDTO(userDTO);
        return toDTO(userRepository.save(user));
    }

    private UserDTO toDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setName(user.getFirstname());
    }

    private User fromDTO(UserDTO userDTO) {
        return new User().setId(userDTO.getId())
                .setFirstname(userDTO.getName());
    }

}
