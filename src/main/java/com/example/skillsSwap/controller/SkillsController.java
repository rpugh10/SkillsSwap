package com.example.skillsSwap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.service.SkillsService;


@RestController
public class SkillsController {
    
    @Autowired
    private SkillsService service;

    @GetMapping("/api/skill")
    public List<Skill> getSkills(){
        return service.getAllSkills();
    }

    @PostMapping("/api/skill")
    public Skill postSkill(@RequestBody Skill skill){
        return service.saveSkill(skill);
    }

    @GetMapping("/api/skill/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id){
        return service.getSkillById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/api/skill/{id}")
    public ResponseEntity<Skill> updateUser(@PathVariable Long id, @RequestBody Skill newSkill){
        Optional<Skill> existingSkill = service.getSkillById(id);

        if(existingSkill.isPresent()){
            Skill skill = existingSkill.get();
            skill.setUser(newSkill.getUser());
            skill.setSkill_name(newSkill.getSkill_name());
            skill.setDescription(newSkill.getDescription());
            skill.setCategory(newSkill.getCategory());
            return ResponseEntity.ok(service.updateSkill(skill));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
