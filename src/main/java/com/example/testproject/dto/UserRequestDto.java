package com.example.testproject.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    private String changeEmail;

    private String changePassword;
}
