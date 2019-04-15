package com.testmonkeys.demo.repo;

import com.testmonkeys.demo.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository  extends JpaRepository<Technology, Long> {


    List<Technology> findAll();
}
