package com.example.testproject.controller;

import com.example.testproject.dto.CreateRequestDto;
import com.example.testproject.dto.CreateResponseDto;
import com.example.testproject.entity.User;
import com.example.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("api/user")
    public ResponseEntity<CreateResponseDto> createUser(@RequestBody CreateRequestDto createRequestDto){
        User user = userService.createUser(createRequestDto);
        CreateResponseDto createResponseDto = new CreateResponseDto(user);
        return ResponseEntity.ok().body(createResponseDto);
    }
}
