package com.example.skillsSwap.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.skillsSwap.dto.SkillDTO;
import com.example.skillsSwap.dto.SkillRequestDTO;
import com.example.skillsSwap.dto.UserDTO;
import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.model.SkillRequest;
import com.example.skillsSwap.model.User;

@Component 
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;


    public SkillDTO convertSkillToDTO(Skill skill){
        SkillDTO skillDTO = modelMapper.map(skill, SkillDTO.class);
        skillDTO.setUserId(skill.getUser().getId()); //Prevent sending the full User object
        return skillDTO;
    }

    public Skill convertDTOToSkill(SkillDTO dto, User user){
        Skill skill = modelMapper.map(dto, Skill.class);
        skill.setUser(user);
        return skill;
    }

    public SkillRequestDTO convertToDTO(SkillRequest skillRequest){
        SkillRequestDTO dto = modelMapper.map(skillRequest, SkillRequestDTO.class);
        dto.setUserId(skillRequest.getRequester().getId());
        dto.setSkillId(skillRequest.getSkill().getId());
        return dto;
    }

    public SkillRequest convertToEntity(SkillRequestDTO dto, User user, Skill skill){
        SkillRequest request = modelMapper.map(dto, SkillRequest.class);
        request.setRequester(user);
        request.setSkill(skill);
        return request;
    }

    public UserDTO convertUserToDTO(User user){
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        return dto;
    }

    public User convertUserDTOToEntity(UserDTO dto){
        User user = modelMapper.map(dto, User.class);
        return user;
    }
}
