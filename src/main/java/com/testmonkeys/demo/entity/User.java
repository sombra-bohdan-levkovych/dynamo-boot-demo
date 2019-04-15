package com.testmonkeys.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLang_key() {
        return lang_key;
    }

    public void setLang_key(String lang_key) {
        this.lang_key = lang_key;
    }

    public String getActivation_key() {
        return activation_key;
    }

    public void setActivation_key(String activation_key) {
        this.activation_key = activation_key;
    }

    public String getReset_key() {
        return reset_key;
    }

    public void setReset_key(String reset_key) {
        this.reset_key = reset_key;
    }

    public String getReset_date() {
        return reset_date;
    }

    public void setReset_date(String reset_date) {
        this.reset_date = reset_date;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone_one() {
        return phone_one;
    }

    public void setPhone_one(String phone_one) {
        this.phone_one = phone_one;
    }

    public String getPhone_two() {
        return phone_two;
    }

    public void setPhone_two(String phone_two) {
        this.phone_two = phone_two;
    }

    public String getPersonal_email() {
        return personal_email;
    }

    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getStarts_work() {
        return starts_work;
    }

    public void setStarts_work(String starts_work) {
        this.starts_work = starts_work;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getVacation_days() {
        return vacation_days;
    }

    public void setVacation_days(Integer vacation_days) {
        this.vacation_days = vacation_days;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGot_fired() {
        return got_fired;
    }

    public void setGot_fired(String got_fired) {
        this.got_fired = got_fired;
    }

    public Integer getSombra_money() {
        return sombra_money;
    }

    public void setSombra_money(Integer sombra_money) {
        this.sombra_money = sombra_money;
    }
}
