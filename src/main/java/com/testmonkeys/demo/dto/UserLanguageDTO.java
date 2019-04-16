package com.testmonkeys.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmonkeys.demo.entity.UserLanguage;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UserLanguageDTO {

    @JsonProperty("id")
    private long id;

    @NotNull
    @JsonProperty("userId")
    private long userId;

    @NotNull
    @JsonProperty("language")
    private LanguageDTO language;

    @NotNull
    @JsonProperty("level")
    private LanguageLevelDTO level;

    @JsonProperty("nextLevel")
    private LanguageLevelDTO nextLevel;

    @JsonProperty("lastReview")
    private LocalDate lastReview;

    @NotNull
    @JsonProperty("rating")
    private short rating;

    public static UserLanguageDTO create(@NotNull UserLanguage userLanguage) {
        return new UserLanguageDTO()
                .setId(userLanguage.getId())
                .setLevel(LanguageLevelDTO.create(userLanguage.getLanguageLevel()))
                .setNextLevel(LanguageLevelDTO.create(userLanguage.getNextLanguageLevel()))
                .setLanguage(LanguageDTO.create(userLanguage.getLanguage()))
                .setLastReview(userLanguage.getLastReview())
                .setRating(userLanguage.getRating())
                .setUserId(userLanguage.getUser().getId());
    }

}