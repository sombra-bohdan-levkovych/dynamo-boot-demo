package com.testmonkeys.demo.dto;

import com.testmonkeys.demo.entity.Role;
import com.testmonkeys.demo.enums.PositionEnum;
import com.testmonkeys.demo.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {

    private Long id;

    @NotNull(message = "User firstname can't be empty")
    @Size(min = 1, max = 50, message = "User firstname can't be empty or longer than 50 characters")
    @Pattern(regexp = Constants.NAME_REGEX, message = "User firstname must be a valid name")
    private String firstname;

    @NotNull(message = "User lastname can't be empty")
    @Size(min = 1, max = 50, message = "User lastname can't be empty or longer than 50 characters")
    @Pattern(regexp = Constants.NAME_REGEX, message = "User lastname must be a valid name")
    private String lastname;

    @NotNull(message = "User email can't be empty")
    @Size(min = 1, max = 100, message = "User email can't be empty or longer than 100 characters")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "User email must be a valid address")
    private String email;

    @NotNull(message = "User personal email can't be empty")
    @Size(min = 1, max = 100, message = "User personal email can't be empty or longer than 100 characters")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "User personal email must be a valid address")
    private String personalEmail;

    private String phoneOne;

    private String phoneTwo;

    @NotNull(message = "User skype can't be empty")
    @Size(min = 1, max = 50, message = "User skype can't be empty or longer than 50 characters")
    private String skype;

    private boolean activated = false;

    private String langKey;

    private Integer sombraMoney;

    private String position;

    private LocalDate birthDate;

    private String office;

    private String avatar;

    private LocalDate startsWork;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String avatar, String personalEmail, String phoneOne, String phoneTwo, String skype, boolean activated, String langKey, List<Role> roles, Integer sombraMoney, LocalDate birthDate, PositionEnum position) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.id = id;
        this.email = email;
        this.avatar = avatar;
        this.personalEmail = personalEmail;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.skype = skype;
        this.activated = activated;
        this.langKey = langKey;

        this.sombraMoney = sombraMoney;
        this.birthDate = birthDate;

    }



    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public UserDTO setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserDTO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getPhoneOne() {
        return phoneOne;
    }

    public UserDTO setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
        return this;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public UserDTO setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
        return this;
    }

    public String getSkype() {
        return skype;
    }

    public UserDTO setSkype(String skype) {
        this.skype = skype;
        return this;
    }

    public boolean isActivated() {
        return activated;
    }

    public UserDTO setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public Integer getSombraMoney() {
        return sombraMoney;
    }

    public UserDTO setSombraMoney(Integer sombraMoney) {
        this.sombraMoney = sombraMoney;
        return this;
    }

    public String getLangKey() {
        return langKey;
    }

    public UserDTO setLangKey(String langKey) {
        this.langKey = langKey;
        return this;
    }


    public String getOffice() {
        return office;
    }

    public UserDTO setOffice(String office) {
        this.office = office;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserDTO setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public UserDTO setPosition(String position) {
        this.position = position;
        return this;
    }

    public LocalDate getStartsWork() {
        return startsWork;
    }

    public UserDTO setStartsWork(LocalDate startsWork) {
        this.startsWork = startsWork;
        return this;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", phoneOne='" + phoneOne + '\'' +
                ", phoneTwo='" + phoneTwo + '\'' +
                ", skype='" + skype + '\'' +
                ", activated=" + activated +
                ", langKey='" + langKey + '\'' +
                ", sombraMoney=" + sombraMoney +
                ", position='" + position + '\'' +
                ", birthDate=" + birthDate +
                ", startsWork=" + startsWork +
                '}';
    }
}