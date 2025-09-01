package com.example.skillsSwap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.example.skillsSwap.status.*;

@Data
public class SkillRequestDTO {

    private Long id;
    @NotBlank(message = "message cannot be empty")
    @Size(max = 100, message = "message cannot be more than 100 characters")
    private String message;
    @NotNull(message = "status cannot be empty")
    private Status status;
    
    private Long userId;
   
    private Long skillId;
}
