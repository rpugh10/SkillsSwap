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

            request.setId(null);
            request.setRequester(user);
            request.setSkill(skill);

            SkillRequest skillRequest = service.createSkillRequest(request);

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(skillRequest.getId())
                .toUri();

            return ResponseEntity.created(location).body(skillRequest);
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

    @PutMapping("/api/skill-request/{id}")
    public ResponseEntity<SkillRequest> updateRequest(@PathVariable Long id, @RequestBody SkillRequest updatedRequest){
       Optional<SkillRequest> existingSkillRequest = service.getSkillRequestById(id);

       if(existingSkillRequest.isPresent()){
            return ResponseEntity.ok(service.updateSkillRequest(id, updatedRequest));
       }

       return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/skill-request/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id){
        Optional<SkillRequest> existingSkillRequest = service.getSkillRequestById(id);

        if(existingSkillRequest.isPresent()){
            service.deleteSkillRequest(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
