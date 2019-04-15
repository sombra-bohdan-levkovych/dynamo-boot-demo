package com.testmonkeys.demo.repo;

import com.testmonkeys.demo.entity.SkillMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillMarkRepository  extends JpaRepository<SkillMark, Long> {


    List<SkillMark> findAll();
}
