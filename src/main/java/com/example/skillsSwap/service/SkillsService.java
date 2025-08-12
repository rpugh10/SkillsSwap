package com.example.skillsSwap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.repository.SkillRepository;

@Service
public class SkillsService {

    @Autowired
    private SkillRepository repository;

    public List<Skill> getAllSkills(){
        return repository.findAll();
    }

    public Skill saveSkill(Skill skill){
        return repository.save(skill);
    }

    public Optional<Skill> getSkillById(Long id){
        return repository.findById(id);
    }

    public Skill updateSkill(Skill skill){
        return repository.save(skill);
    }

    public void deleteSkill(Long id){
        repository.deleteById(id);
    }

    public Optional<Skill> getSkillByUserId(Long userId){
        return repository.findById(userId);
    }
}
