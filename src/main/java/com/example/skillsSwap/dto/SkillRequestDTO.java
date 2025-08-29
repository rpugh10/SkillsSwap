package com.example.skillsSwap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SkillRequestDTO {

    private Long id;
    @NotBlank(message = "message cannot be empty")
    @Size(max = 100, message = "message cannot be more than 100 characters")
    private String message;
    @NotBlank(message = "status cannot be empty")
    private String status;
    @NotNull(message = "userId is required")
    @Positive(message = "userId must be a positive number")
    private Long userId;
    @NotNull(message = "skillId is required")
    @Positive(message = "skillId must be a positive number")
    private Long skillId;
}
