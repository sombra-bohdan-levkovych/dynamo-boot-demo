package com.testmonkeys.demo.service;

import com.testmonkeys.demo.entity.Technology;
import com.testmonkeys.demo.repo.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }
}
