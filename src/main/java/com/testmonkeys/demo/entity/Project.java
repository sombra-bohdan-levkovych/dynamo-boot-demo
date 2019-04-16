package com.testmonkeys.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"manager", "hrManager", "timeLogs", "userInProject",
        "requests", "teamLead", "skillMarks", "technologies", "parentProjectFolder"})
@ToString(callSuper = true, exclude = {"manager", "hrManager", "timeLogs", "userInProject",
        "requests", "teamLead", "skillMarks", "technologies", "parentProjectFolder"})
public class Project extends IdComponent<Project> {

    @NotNull(message = "Project title can't be empty")
    @Size(min = 1, max = 100, message = "Project title can't be empty or longer than 100 characters")
    @Column(name = "title", length = 100)
    private String title;

    @ManyToOne
    private User manager;

    @ManyToOne
    @JsonIgnore
    private User hrManager;

    @NotNull
    @Column(nullable = false)
    private boolean activated = true;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.REMOVE,}, fetch = FetchType.LAZY)
    private List<UserProject> userInProject;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    // TODO SONAR-MAJOR I have a feeling, that there is no need to use ZonedDateTime here
    @Column(name = "modified_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime modifiedAt;


    @Column(name = "color", length = 25)
    private String color;

    @Size(min = 1, max = 20, message = "Project shortname can't be empty or longer than 20 characters")
    @Column(name = "short_name", length = 20)
    private String shortName;

    @Column(name = "is_temporary")
    private boolean temporary = false;

    @ManyToOne
    private User teamLead;

    @ManyToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<SkillMark> skillMarks;

    @JoinTable(name = "project_technologies", joinColumns = @JoinColumn(name = "project_id", referencedColumnName =
            "id"), inverseJoinColumns = @JoinColumn(name = "technology_id", referencedColumnName = "id"))
    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Technology> technologies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_project_folder")
    private ProjectFolder parentProjectFolder;

    @Column(name = "accessible_team_lead_for_temp_management", nullable = false)
    private boolean accessibleTeamLeadForTmpManagement = false;

    @Column( length = 150, name = "confluence_group_title")
    private String confluenceGroup;

}
