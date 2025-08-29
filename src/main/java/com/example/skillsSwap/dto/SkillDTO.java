package com.example.skillsSwap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SkillDTO {

    private Long id;
    @NotBlank(message = "skill_name cannot be empty")
    private String skill_name;
    @NotBlank(message = "description cannot be empty")
    @Size(max = 200, message = "description cannot exceed 200 character")
    private String description;
    @NotBlank(message = "category cannot be blank")
    private String category;
    private Long userId;
}
