package com.testmonkeys.demo.controller;


import com.testmonkeys.demo.entity.Technology;
import com.testmonkeys.demo.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technology")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @GetMapping
    public List<Technology> getUsers() {
        return technologyService.findAll();

    }

}
