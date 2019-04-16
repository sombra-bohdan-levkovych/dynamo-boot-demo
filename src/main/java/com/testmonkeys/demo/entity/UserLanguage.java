package com.testmonkeys.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_language")
public class UserLanguage extends IdComponent<UserLanguage> {

    @ManyToOne
    private User user;

    @ManyToOne
    private LanguageLevel languageLevel;

    @ManyToOne
    private LanguageLevel nextLanguageLevel;

    @ManyToOne
    private Language language;

    @Column(name = "last_review")
    private LocalDate lastReview;

    @Column(name = "rating")
    private short rating;

}
