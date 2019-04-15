package com.testmonkeys.demo.controller;


import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<User> getUsers() {

       return userService.findAll();

    }


}
