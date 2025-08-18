package com.example.skillsSwap.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.skillsSwap.dto.SkillDTO;
import com.example.skillsSwap.dto.SkillRequestDTO;
import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.model.SkillRequest;
import com.example.skillsSwap.model.User;

@Component 
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public SkillDTO conDto(Skill skill){
        SkillDTO skillDTO = modelMapper.map(skill, SkillDTO.class);
        return skillDTO;
        
    }

    public SkillRequestDTO conRequestDTO(SkillRequest skillRequest){
        SkillRequestDTO dto = modelMapper.map(skillRequest, SkillRequestDTO.class);
        dto.setUserId(skillRequest.getRequester().getId());
        dto.setSkillId(skillRequest.getSkill().getId());
        return dto;
    }

    public SkillRequest conRequestEntity(SkillRequestDTO dto, User user, Skill skill){
        SkillRequest request = modelMapper.map(dto, SkillRequest.class);
        request.setRequester(user);
        request.setSkill(skill);
        return request;
    }
}
