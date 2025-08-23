package com.example.skillsSwap.exceptions;

public class SkillRequestException extends RuntimeException{

    public SkillRequestException(Long id){
        super("Skill request with id " + id + " not found");
    }

}
