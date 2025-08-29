package com.example.skillsSwap.controller;

import java.util.List;

import org.modelmapper.internal.bytebuddy.build.HashCodeAndEqualsPlugin.ValueHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillsSwap.dto.UserDTO;
import com.example.skillsSwap.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/api/users")
    public UserDTO postUser(@Valid @RequestBody UserDTO user){
        return userService.postUser(user);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO updatedUser){
       UserDTO user = userService.updateUser(id, updatedUser);
       return ResponseEntity.ok(user);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
