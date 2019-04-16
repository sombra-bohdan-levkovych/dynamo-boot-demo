package com.testmonkeys.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String position;
    private String rank;
    private String name;
    private String email;
}
