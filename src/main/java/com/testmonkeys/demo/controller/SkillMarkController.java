package com.testmonkeys.demo.controller;

import com.testmonkeys.demo.entity.SkillMark;
import com.testmonkeys.demo.service.SkillMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skillmark")
public class SkillMarkController {


    @Autowired
    private SkillMarkService skillMarkService;

    @GetMapping
    public List<SkillMark> getUsers() {
        return skillMarkService.findAll();

    }

}
