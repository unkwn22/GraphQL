package com.example.testproject.controller;

import com.example.testproject.dto.CreateRequestDto;
import com.example.testproject.dto.UserRequestDto;
import com.example.testproject.dto.UserResponseDto;
import com.example.testproject.entity.User;
import com.example.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("api/user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateRequestDto createRequestDto){
        User user = userService.createUser(createRequestDto);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @GetMapping("api/user")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping ("api/user/{id}")
    public ResponseEntity<UserResponseDto> updateUser (@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        User user = userService.updateUser(id, userRequestDto);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return ResponseEntity.ok().body(userResponseDto);
    }


}
