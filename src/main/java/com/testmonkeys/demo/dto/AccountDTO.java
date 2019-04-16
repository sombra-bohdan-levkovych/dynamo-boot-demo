package com.testmonkeys.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmonkeys.demo.entity.Role;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.utils.Constants;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Data
@ToString(doNotUseGetters = true, exclude = {"base64Avatar"})
public class AccountDTO {
    private Long id;

    @NotNull(message = "User firstname can't be empty")
    @Size(min = 1, max = 20, message = "User firstname can't be empty or longer than 50 characters")
    @Pattern(regexp = Constants.NAME_REGEX, message = "User firstname must be a valid name")
    private String firstname;

    @NotNull(message = "User lastname can't be empty")
    @Size(min = 1, max = 20, message = "User lastname can't be empty or longer than 50 characters")
    @Pattern(regexp = Constants.NAME_REGEX, message = "User lastname must be a valid name")
    private String lastname;

    @NotNull(message = "User email can't be empty")
    @Size(min = 1, max = 100, message = "User email can't be empty or longer than 100 characters")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "User email must be valid")
    private String email;

    @NotNull(message = "User personal email can't be empty")
    @Size(min = 1, max = 100, message = "User personal email can't be empty or longer than 100 characters")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "User personal email must be valid")
    private String personalEmail;

    @NotEmpty(message = "User's roles can't be empty")
    private List<String> roles;

    @NotNull(message = "User's birthday date can't be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "User's starts work day can't be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startsWork;

    private List<Long> projectsId;

    @NotNull
    @Pattern(regexp = Constants.PHONE_REGEX, message = "User's phone must contain 10 numbers")
    private String phoneOne;

    @Pattern(regexp = Constants.PHONE_REGEX, message = "User's phone must contain 10 numbers")
    private String phoneTwo;

    @NotNull(message = "User skype can't be empty")
    @Pattern(regexp = Constants.SKYPE_REGEX, message = "User's skype must be valid")
    @Size(min = 1, max = 50, message = "User skype can't be empty or longer than 50 characters")
    private String skype;

    @NotNull(message = "User must be associated with office")
    private String office;

    @NotNull(message = "User position can't be empty")
    @Size(min = 1, max = 40, message = "User position can't be empty or longer than 20 characters")
    private String position;

    @NotNull(message = "User department can't be empty")
    private String department;

    @Size(min = 1, max = 60, message = "User email can't be empty or longer than 60 characters")
    private String managerEmail;

    @Size(min = 1, max = 60, message = "User email can't be empty or longer than 60 characters")
    @JsonProperty("hrEmail")
    private String hrEmail;

    @Size(min = 1, max = 60, message = "User email can't be empty or longer than 60 characters")
    private String mentorEmail;

    private boolean activated;

    private String langKey;

    private List<String> sendEmailTo;

    private String base64Avatar;
    private String avatarPath;

    private Boolean canManageStatus;

    private List<UserLanguageDTO> languages;

    public AccountDTO() {
    }

    public AccountDTO(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.personalEmail = user.getPersonalEmail();
        this.phoneOne = user.getPhoneOne();
        this.phoneTwo = user.getPhoneTwo();
        this.skype = user.getSkype();

        this.office = user.getOffice();
        this.activated = user.isActivated();
        this.langKey = user.getLangKey();
        this.roles = user.getRoles()
                .stream()
                .map(Role::getAuthority)
                .collect(Collectors.toList());

        if (nonNull(user.getPosition())) {
            this.position = user.getPosition().getValue();
        }
        this.department = user.getDepartment().getTitle();

        if (nonNull(user.getLanguages())) {
            this.languages = user.getLanguages()
                    .stream()
                    .map(UserLanguageDTO::create)
                    .collect(Collectors.toList());
        }
    }
}