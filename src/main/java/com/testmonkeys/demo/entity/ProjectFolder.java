package com.testmonkeys.demo.entity;


import com.testmonkeys.demo.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "projectFolder")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"parentProjectFolder", "projectFolders", "projects", "users"})
@ToString(callSuper = true, exclude = {"parentProjectFolder", "projectFolders", "projects", "users"})
public class ProjectFolder extends IdComponent<ProjectFolder>   {

    @NotNull(message = "ProjectFolder title can't be empty")
    @Size(min = 1, max = 100, message = "ProjectFolder title can't be empty or longer than 100 characters")
    @Pattern(regexp = Constants.NAME_REGEX_EXTENDED, message = "ProjectFolder title must be a valid title")
    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "color", length = 25)
    private String color;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_project_folder")
    private ProjectFolder parentProjectFolder;

    @OneToMany(mappedBy = "parentProjectFolder", fetch = FetchType.LAZY)
    private List<ProjectFolder> projectFolders;

    @OneToMany(mappedBy = "parentProjectFolder", fetch = FetchType.LAZY)
    @OrderBy("title asc")
    private List<Project> projects;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<User> users;

}
