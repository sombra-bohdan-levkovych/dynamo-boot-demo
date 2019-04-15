package com.testmonkeys.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "skills")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"user", "technology", "project"})
@ToString(callSuper = true, exclude = {"user", "technology", "project"})
public class SkillMark {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Technology technology;

    @JoinTable(name = "project_skillMarks", joinColumns = @JoinColumn(name = "skill_id", referencedColumnName =
            "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    @ManyToMany
    private List<Project> project;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

}
