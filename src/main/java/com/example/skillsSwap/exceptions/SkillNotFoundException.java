package com.example.skillsSwap.exceptions;

public class SkillNotFoundException extends RuntimeException{
    public SkillNotFoundException(Long id){
        super("Skill not found with id " + id);
    }

}
