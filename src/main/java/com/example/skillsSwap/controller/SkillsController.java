package com.example.skillsSwap.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.skillsSwap.dto.SkillDTO;
import com.example.skillsSwap.service.SkillsService;

import jakarta.validation.Valid;


@RestController
public class SkillsController {
    
    @Autowired
    private SkillsService service;


    @GetMapping("/api/skill")
    public List<SkillDTO> getSkills(){
        return service.getAllSkills();
    }

    @PostMapping("/api/skill/user/{userId}")
    public ResponseEntity<SkillDTO> postSkill(@PathVariable Long userId, @Valid @RequestBody SkillDTO dto){
        SkillDTO savedSkill = service.saveSkill(userId, dto);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedSkill.getId())
        .toUri();

        return ResponseEntity.created(location).body(savedSkill);

    }

    @GetMapping("/api/skill/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id){
       SkillDTO skill = service.getSkillById(id);
       return ResponseEntity.ok(skill);
    }

    @PutMapping("/api/skill/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable Long id, @Valid @RequestBody SkillDTO dto){
       SkillDTO updatedSkill = service.updateSkill(id, dto);
       return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/api/skill/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id){
       service.deleteSkill(id);
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/users/{userId}/skill")
    public ResponseEntity<List<SkillDTO>> findByUserId(@PathVariable Long userId){
        List<SkillDTO> skills = service.getSkillByUserId(userId);
        return ResponseEntity.ok(skills);
        
    }

}
