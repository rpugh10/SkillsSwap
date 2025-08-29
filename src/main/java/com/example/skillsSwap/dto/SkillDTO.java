package com.example.skillsSwap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SkillDTO {

    private Long id;
    @NotBlank(message = "skill_name cannot be empty")
    private String skill_name;
    @NotBlank(message = "description cannot be empty")
    @Size(max = 200, message = "description cannot exceed 200 characters")
    private String description;
    @NotBlank(message = "category cannot be blank")
    private String category;
    @NotNull(message = "userId is required")
    @Positive(message = "userId must be a positive number")
    private Long userId;
}
