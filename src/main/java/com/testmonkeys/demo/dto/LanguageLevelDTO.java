package com.testmonkeys.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmonkeys.demo.entity.LanguageLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LanguageLevelDTO {

    @JsonProperty("id")
    private long id;

    @NotNull
    @JsonProperty("level")
    private String level;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("description")
    private String description;

    @NotNull
    @JsonProperty("sequenceNumber")
    private short sequenceNumber;

    public static LanguageLevelDTO create(@NotNull LanguageLevel languageLevel) {
        return new LanguageLevelDTO()
                .setLevel(languageLevel.getLevel())
                .setId(languageLevel.getId())
                .setName(languageLevel.getName())
                .setDescription(languageLevel.getDescription())
                .setSequenceNumber(languageLevel.getSequenceNumber());
    }

}