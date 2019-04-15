package com.testmonkeys.demo.controller;


import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/all")
    public List<User> getUsers() throws IOException {

       return userService.findAll();

    }


}
