package com.testmonkeys.demo.service;

import com.google.gson.Gson;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
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
    public List<User> findAll(){
       return userRepository.findAll();
    }

}
