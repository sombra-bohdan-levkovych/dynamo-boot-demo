package com.testmonkeys.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private Long id;
    private String password_hash;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean activated;
    private String lang_key;
    private String activation_key;
    private String reset_key;
    private String reset_date;
    private String last_login;
    private String birth_date;
    private String phone_one;
    private String phone_two;
    private String personal_email;
    private String skype;
    private String starts_work;
    private String office;
    private Integer vacation_days;
    private String position;
    private String got_fired;
    private Integer sombra_money;
}
