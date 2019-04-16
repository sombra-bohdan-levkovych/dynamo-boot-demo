package com.testmonkeys.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "language_level")
public class LanguageLevel extends IdComponent<LanguageLevel> {

    @Column(name = "level")
    private String level;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1500, columnDefinition = "VARCHAR(1500)")
    private String description;

    @Column(name = "sequence_number", unique = true)
    private short sequenceNumber;

}
