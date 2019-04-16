package com.testmonkeys.demo.service;

import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User getOne(Long id);

    List<UserDTO> findAllActivatedBEMiddleDevs();

    UserDTO createUser(UserDTO userDTO);
 }
