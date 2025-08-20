package com.example.skillsSwap.dto;

import lombok.Data;

@Data
public class SkillDTO {

    private Long id;
    private String name;
    private String description;
    private String category;
    private Long userId;
}
