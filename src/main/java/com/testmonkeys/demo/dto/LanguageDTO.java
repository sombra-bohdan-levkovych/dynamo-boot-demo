package com.testmonkeys.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testmonkeys.demo.entity.Language;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LanguageDTO {

    @JsonProperty("id")
    private long id;

    @NotNull
    @JsonProperty("name")
    private String name;

    public static LanguageDTO create(@NotNull Language language) {
        return new LanguageDTO()
                .setId(language.getId())
                .setName(language.getName());
    }

}