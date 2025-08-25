package com.example.skillsSwap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillsSwap.dto.SkillRequestDTO;
import com.example.skillsSwap.mapper.Mapper;
import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.model.SkillRequest;
import com.example.skillsSwap.model.User;
import com.example.skillsSwap.repository.SkillRequestRepository;

@Service
public class SkillRequestService {

    @Autowired
    private SkillRequestRepository repository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private SkillsService skillService;

    @Autowired
    private UserService userService;

    public SkillRequestDTO createSkillRequest(SkillRequestDTO dto){
        User user = userService.getUserById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Skill skill = skillService.getSkillById(dto.getSkillId())
            .orElseThrow(() -> new RuntimeException("Skill not found"));

        SkillRequest request = mapper.convertToEntity(dto, user, skill);
        SkillRequest saved = repository.save(request);
        return mapper.convertToDTO(saved);
    }

    public SkillRequestDTO getSkillRequestById(Long id){
        SkillRequest request = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Request not found"));
        return mapper.convertToDTO(request);
    }

    public List<SkillRequestDTO> getSkillRequestsByUserId(Long userId){
        return repository.findByRequesterId(userId).stream()
            .map(mapper::convertToDTO)
            .collect(Collectors.toList());
    }

    public void deleteSkillRequest(Long id){
        repository.deleteById(id);
    }

    public SkillRequestDTO updateSkillRequest(Long id, SkillRequestDTO request){
       SkillRequest updatedRequest = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Request not found"));

        updatedRequest.setMessage(request.getMessage());
        updatedRequest.setStatus(request.getStatus());

        SkillRequest updated = repository.save(updatedRequest);
        return mapper.convertToDTO(updated);
    }

    
}
