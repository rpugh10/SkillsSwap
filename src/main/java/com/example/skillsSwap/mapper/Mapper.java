package com.example.skillsSwap.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.skillsSwap.dto.SkillDTO;
import com.example.skillsSwap.dto.SkillRequestDTO;
import com.example.skillsSwap.model.Skill;

@Component 
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public SkillDTO conDto(Skill skill){
        SkillDTO skillDTO = modelMapper.map(skill, SkillDTO.class);
        return skillDTO;
        
    }

    public SkillRequestDTO conRequestDTO(SkillRequestDTO skillRequestDTO){
        SkillRequestDTO skillRequestDTO2 = modelMapper.map(skillRequestDTO, SkillRequestDTO.class);
        return skillRequestDTO2;
    }
}
