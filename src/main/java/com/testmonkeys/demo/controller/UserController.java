package com.testmonkeys.demo.controller;


import com.testmonkeys.demo.dto.SombraUser;
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

    @RequestMapping("/users")
    public List<SombraUser> getUsers() throws IOException {

       return userService.getUserFromJson();

    }


}
