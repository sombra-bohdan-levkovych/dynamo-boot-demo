package com.testmonkeys.demo.service;

import com.testmonkeys.demo.entity.SkillMark;
import com.testmonkeys.demo.repo.SkillMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillMarkServiceImpl implements SkillMarkService {

    @Autowired
    private SkillMarkRepository skillMarkRepository;


    @Override
    public List<SkillMark> findAll() {
        return skillMarkRepository.findAll();
    }
}
