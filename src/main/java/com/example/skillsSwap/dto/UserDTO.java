package com.example.skillsSwap.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotBlank(message = "email cannot be empty")
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotBlank(message = "bio cannot be empty")
    private String bio;
}
