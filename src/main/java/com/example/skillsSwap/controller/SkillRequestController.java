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

import com.example.skillsSwap.dto.SkillRequestDTO;
import com.example.skillsSwap.service.SkillRequestService;


@RestController
public class SkillRequestController {

    @Autowired
    private SkillRequestService service;


    @PostMapping("/api/skill-request/user/{userId}/skill/{skillId}")
    public ResponseEntity<SkillRequestDTO> createSkillRequest(@PathVariable Long userId, @PathVariable Long skillId, @RequestBody SkillRequestDTO request){
        SkillRequestDTO requestDTO = service.createSkillRequest(userId, skillId, request);
        
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(requestDTO.getId())
        .toUri();
        
        return ResponseEntity.created(location).body(requestDTO);
    }

    @GetMapping("/api/skill-request/{id}")
    public ResponseEntity<SkillRequestDTO> getSkillRequestById(@PathVariable Long id){
        SkillRequestDTO requestDTO = service.getSkillRequestById(id);
        return ResponseEntity.ok(requestDTO);
    }
    
    @GetMapping("/api/users/{userId}/skill-request")
    public ResponseEntity<List<SkillRequestDTO>> getAllSkillRequests(@PathVariable Long userId){
       List<SkillRequestDTO> requestDTO = service.getSkillRequestsByUserId(userId);
       return ResponseEntity.ok(requestDTO);
    }

    @PutMapping("/api/skill-request/{id}")
    public ResponseEntity<SkillRequestDTO> updateRequest(@PathVariable Long id, @RequestBody SkillRequestDTO updatedRequest){
       SkillRequestDTO requestDTO = service.updateSkillRequest(id, updatedRequest);
       return ResponseEntity.ok(requestDTO);
    }

    @DeleteMapping("/api/skill-request/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id){
        service.deleteSkillRequest(id);
        return ResponseEntity.noContent().build();
    }
}
