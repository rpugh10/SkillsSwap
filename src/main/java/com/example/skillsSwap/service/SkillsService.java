package com.example.skillsSwap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillsSwap.dto.SkillDTO;
import com.example.skillsSwap.mapper.Mapper;
import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.model.User;
import com.example.skillsSwap.repository.SkillRepository;

@Service
public class SkillsService {

    @Autowired
    private SkillRepository repository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserService userService;

    public List<SkillDTO> getAllSkills(){
        return repository.findAll()
            .stream()
            .map(skill -> mapper.convertSkillToDTO(skill))
            .toList();
    }

    public SkillDTO saveSkill(SkillDTO dto){
        User user = userService.getUserById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Skill skill = mapper.convertDTOToSkill(dto, user);
        Skill saved = repository.save(skill);
        return mapper.convertSkillToDTO(saved);
    }

    public Optional<SkillDTO>getSkillByIdDTO(Long id){
        return repository.findById(id)
            .map(skill -> mapper.convertSkillToDTO(skill));
           
    }

    public Optional<Skill> getSkillById(Long id){
        return repository.findById(id);
    }

    public SkillDTO updateSkill(Long id, SkillDTO dto){
        Skill existingSkill = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Skill not found"));

        existingSkill.setSkill_name(dto.getName());
        existingSkill.setDescription(dto.getDescription());
        existingSkill.setCategory(dto.getCategory());

        Skill updatedSkill = repository.save(existingSkill);

        return mapper.convertSkillToDTO(updatedSkill);
    }
    

    public void deleteSkill(Long id){
        repository.deleteById(id);
    }

    public List<SkillDTO> getSkillByUserId(Long userId){
        User user = userService.getUserById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        List<Skill> skill = repository.findByUserId(userId);
        
        return skill.stream()
            .map(skills -> mapper.convertSkillToDTO(skills))
            .toList();
    }
}
