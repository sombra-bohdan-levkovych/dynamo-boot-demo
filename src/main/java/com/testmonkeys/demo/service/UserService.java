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
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public List<User> findAll(){
       return userRepository.findAll();
    }

    public List<User> getUserFromJson() throws IOException {
        Gson gson = new Gson();

        String json = StreamUtils.copyToString(
                new ClassPathResource("users.json").getInputStream(),
                Charset.defaultCharset());

        User[] arr = gson.fromJson(json, User[].class);

        return Arrays.asList(arr);
    }

}
