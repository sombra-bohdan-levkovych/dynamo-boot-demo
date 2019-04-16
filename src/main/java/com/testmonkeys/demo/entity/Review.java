package com.testmonkeys.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "reviews")
@Data
@EqualsAndHashCode(callSuper = false,
        exclude = {"assignedTo", "creator", "reviewers", "answers", "softReview", "techReview"})
@ToString(callSuper = true,
        exclude = {"assignedTo", "creator", "reviewers", "answers", "softReview", "techReview"})
public class Review extends IdComponent<Review> {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private User assignedTo;

    @ManyToOne
    private User creator;



    @Column(name = "scheduled_date", nullable = false)
    private LocalDateTime scheduledDate;

    @Column(name = "assigned_date", nullable = false)
    private LocalDateTime assignedDate;

    @ManyToMany
    @JoinTable(name = "reviews_reviewers",
            joinColumns = @JoinColumn(name = "reviews_id"),
            inverseJoinColumns = @JoinColumn(name = "reviewers_id")
    )
    private Set<User> reviewers;


    @OneToOne(cascade = CascadeType.ALL)
    private Review softReview;

    @OneToOne(cascade = CascadeType.ALL)
    private Review techReview;


}
