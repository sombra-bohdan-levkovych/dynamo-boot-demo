package com.testmonkeys.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "technologies")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"projects", "skillMarks"})
@ToString(callSuper = true, exclude = {"projects", "skillMarks"})
public class Technology {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Column(name = "title", length = 100, unique = true)
    private String title;

    @NotNull
    @Column(name = "description", length = 400, columnDefinition = "VARCHAR(400)")
    private String description;

    @ManyToMany(mappedBy = "technologies", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Project> projects;

    @OneToMany(mappedBy = "technology", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<SkillMark> skillMarks;

}
