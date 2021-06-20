package com.example.testproject.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateRequestDto {

    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
