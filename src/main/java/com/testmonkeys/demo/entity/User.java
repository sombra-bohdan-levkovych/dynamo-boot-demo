package com.testmonkeys.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testmonkeys.demo.enums.PositionEnum;
import com.testmonkeys.demo.utils.Constants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private Long id;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;

    @NotNull(message = "User firstname can't be empty")
    @Size(min = 1, max = 50, message = "User firstname can't be empty or longer than 50 characters")
    @Pattern(regexp = Constants.NAME_REGEX, message = "User firstname must be a valid name")
    @Column(name = "firstname", length = 50)
    private String firstname;

    @NotNull(message = "User lastname can't be empty")
    @Size(min = 1, max = 50, message = "User lastname can't be empty or longer than 50 characters")
    @Pattern(regexp = Constants.NAME_REGEX, message = "User lastname must be a valid name")
    @Column(name = "lastname", length = 50)
    private String lastname;

    @NotNull(message = "User personal email can't be empty")
    @Size(min = 1, max = 100, message = "User personal email can't be empty or longer than 100 characters")
    @Column(name = "personal_email", length = 100, unique = true)
    private String personalEmail;

    @NotNull(message = "User email can't be empty")
    @Size(min = 1, max = 100, message = "User email can't be empty or longer than 100 characters")
    @Pattern(regexp = Constants.CORPORATE_EMAIL_REGEX, message = "User email must be a valid address")
    @Column(length = 100, unique = true)
    private String email;


    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(min = 2, max = 5)
    @Column(name = "lang_key", length = 5)
    private String langKey;


    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;


    @Column(name = "reset_date")
    private LocalDateTime resetDate = null;


    @Column(name = "last_login")
    private LocalDateTime lastLogin;


    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name = "phone_one", length = 20, unique = true)
    private String phoneOne;

    @Column(name = "phone_two", length = 20, unique = true)
    private String phoneTwo;



    @NotNull(message = "User skype can't be empty")
    @Size(min = 1, max = 50, message = "User skype can't be empty or longer than 50 characters")
    @Column(name = "skype", length = 100, unique = true)
    private String skype;


    @Column(name = "starts_work")
    private LocalDate startsWork;



    @Column(name = "office", length = 50)
    private String office;


    @Column(name = "vacation_days")
    private Integer vacationDays;



    @Column(name = "position")
    private PositionEnum position;


    private String got_fired;

    @Column(name = "sombra_money")
    private Integer sombraMoney;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkillMark> skillMark;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private ProjectFolder department;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLanguage> languages;

    @OneToOne
    @JsonIgnore
    private User mentor;

    @OneToOne
    @JsonIgnore
    private User myHR;

    @OneToOne
    @JsonIgnore
    private User mainManager;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserProject> projectsForUser;
}
