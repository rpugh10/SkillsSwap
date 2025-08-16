package com.example.skillsSwap.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.model.User;
import com.example.skillsSwap.service.SkillsService;
import com.example.skillsSwap.service.UserService;


@RestController
public class SkillsController {
    
    @Autowired
    private SkillsService service;

    @Autowired
    private UserService userService;

    @GetMapping("/api/skill")
    public List<Skill> getSkills(){
        return service.getAllSkills();
    }

    @PostMapping("/api/skill/user/{userId}")
    public ResponseEntity<Skill> postSkill(@PathVariable Long userId, @RequestBody Skill skill){
        Optional<User> user = userService.getUserById(userId);
        
        if(user.isPresent()){
           User currentUser = user.get();
           skill.setId(null);
           skill.setUser(currentUser);
           Skill savedSkill = service.saveSkill(skill);

           URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(savedSkill.getId())
                .toUri();

           return ResponseEntity.created(location).body(savedSkill);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/skill/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id){
        return service.getSkillById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/api/skill/{id}/user/{userId}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @PathVariable Long userId, @RequestBody Skill newSkill){
        Optional<Skill> existingSkill = service.getSkillById(id);
        Optional<User> user = userService.getUserById(userId);

        if(existingSkill.isPresent() && user.isPresent()){
            User currentUser = user.get();
            Skill skill = existingSkill.get();

            skill.setUser(currentUser);
            skill.setSkill_name(newSkill.getSkill_name());
            skill.setDescription(newSkill.getDescription());
            skill.setCategory(newSkill.getCategory());
            return ResponseEntity.ok(service.updateSkill(skill));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/skill/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id){
        Optional<Skill> skill = service.getSkillById(id);

        if(skill.isPresent()){
            service.deleteSkill(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/users/{userId}/skill")
    public ResponseEntity<List<Skill>> findByUserId(@PathVariable Long userId){
        Optional<User> user = userService.getUserById(userId);

        if(!user.isPresent()){
             return ResponseEntity.notFound().build();
        }
        
           List<Skill> skills = service.findByUserId(userId);
           return ResponseEntity.ok(skills);
        
    }

}
