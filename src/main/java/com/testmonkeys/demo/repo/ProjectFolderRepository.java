package com.testmonkeys.demo.repo;

import com.testmonkeys.demo.entity.ProjectFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectFolderRepository extends JpaRepository<ProjectFolder, Long> {

    @Query(value = "SELECT DISTINCT p FROM ProjectFolder p WHERE p.id=:id")
    ProjectFolder findFirstById(@Param("id") Long id);



}
