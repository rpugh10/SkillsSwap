package com.example.skillsSwap.dto;

import lombok.Data;

@Data
public class SkillRequestDTO {

    private Long id;
    private String message;
    private String status;
    private Long userId;
    private Long skillId;
}
