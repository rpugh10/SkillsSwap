package com.example.skillsSwap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.model.SkillRequest;
import com.example.skillsSwap.model.User;
import com.example.skillsSwap.service.SkillRequestService;
import com.example.skillsSwap.service.SkillsService;
import com.example.skillsSwap.service.UserService;


@RestController
public class SkillRequestController {

    @Autowired
    private SkillRequestService service;

    @Autowired
    private UserService userService;

    @Autowired 
    private SkillsService skillsService;

    @PostMapping("/api/skill-request/user/{userId}/skill/{skillId}")
    public ResponseEntity<SkillRequest> createSkillRequest(@PathVariable Long userId, @PathVariable Long skillId, @RequestBody SkillRequest request){
        Optional<User> existingUser = userService.getUserById(userId);
        Optional<Skill> existingSkill = skillsService.getSkillById(skillId);

        if(existingUser.isPresent() && existingSkill.isPresent()){
            User user = existingUser.get();
            Skill skill = existingSkill.get();

            request.setRequester(user);
            request.setSkill(skill);

            SkillRequest skillRequest = service.createSkillRequest(request);
            return ResponseEntity.ok(skillRequest);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/skill-request/{id}")
    public ResponseEntity<SkillRequest> getSkillRequestById(@PathVariable Long id){
        return service.getSkillRequestById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/api/users/{userId}/skill-request")
    public ResponseEntity<List<SkillRequest>> getAllSkillRequests(@PathVariable Long userId){
        Optional<User> existingUser = userService.getUserById(userId);

        if(existingUser.isPresent()){
            List<SkillRequest> skillRequests = service.getSkillRequestsByUserId(userId);
            return ResponseEntity.ok(skillRequests);
        }

        return ResponseEntity.notFound().build();
    }
}
