package com.example.skillsSwap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillsSwap.dto.UserDTO;
import com.example.skillsSwap.mapper.Mapper;
import com.example.skillsSwap.model.User;
import com.example.skillsSwap.service.UserService;


@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final UserService userService;
    private final Mapper mapper;

    public AuthController(UserService userService, Mapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
        User savedUser = userService.register(mapper.toUser(userDTO));
        return ResponseEntity.ok(mapper.toUserDTO(savedUser));
    }
    
    
}
