package com.example.testproject.dto;

import com.example.testproject.entity.User;
import lombok.Data;

@Data
public class CreateResponseDto {

    private String email;

    private String username;

    private String password;

    public CreateResponseDto(User user){
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
