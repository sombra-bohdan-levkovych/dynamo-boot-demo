package com.testmonkeys.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "language")
public class Language {


    @Id
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

}