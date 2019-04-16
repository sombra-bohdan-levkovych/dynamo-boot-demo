package com.testmonkeys.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "team")
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"project", "user"})
@ToString(callSuper = true, exclude = {"project", "user"})
public class UserProject extends IdComponent<UserProject> implements Comparable<UserProject> {
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_work")
    private LocalDateTime startWork;

    @Column(name = "end_work")
    private LocalDateTime endWork;

    @Column(name = "project_activated")
    private boolean activated = true;

    @Override
    public int compareTo(UserProject o) {
        String firstFullName = user.getFirstname() + " " + user.getLastname();
        String secondFullName = o.getUser().getFirstname() + " " + o.getUser().getLastname();
        return firstFullName.compareTo(secondFullName);
    }
}
