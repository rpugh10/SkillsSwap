package com.example.skillsSwap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillsSwap.dto.SkillDTO;
import com.example.skillsSwap.exceptions.SkillNotFoundException;
import com.example.skillsSwap.exceptions.UserNotFoundException;
import com.example.skillsSwap.mapper.Mapper;
import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.model.User;
import com.example.skillsSwap.repository.SkillRepository;
import com.example.skillsSwap.repository.UserRepository;

@Service
public class SkillsService {

    @Autowired
    private SkillRepository repository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserRepository userRepository;

    public List<SkillDTO> getAllSkills(){
        return repository.findAll()
            .stream()
            .map(skill -> mapper.toSkillDTO(skill))
            .toList();
    }

    public SkillDTO saveSkill(Long userId, SkillDTO dto){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        
        Skill skill = mapper.toSkill(dto, user);
        skill.setUser(user);

        Skill savedSkill = repository.save(skill);
        return mapper.toSkillDTO(savedSkill);
    }

    public SkillDTO getSkillById(Long id){
        Skill skill = repository.findById(id)
            .orElseThrow(() -> new SkillNotFoundException(id));
        return mapper.toSkillDTO(skill);
    }

    public SkillDTO updateSkill(Long id, SkillDTO dto){
        Skill existingSkill = repository.findById(id)
            .orElseThrow(() -> new SkillNotFoundException(id));

        existingSkill.setSkill_name(dto.getSkill_name());
        existingSkill.setDescription(dto.getDescription());
        existingSkill.setCategory(dto.getCategory());

        Skill updatedSkill = repository.save(existingSkill);

        return mapper.toSkillDTO(updatedSkill);
    }
    

    public void deleteSkill(Long id){
        repository.findById(id)
        .orElseThrow(() -> new SkillNotFoundException(id));
        repository.deleteById(id);
    }

    public List<SkillDTO> getSkillByUserId(Long userId){
        userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        
        return repository.findByUserId(userId)
            .stream()
            .map(mapper::toSkillDTO)
            .toList();
    }
}
