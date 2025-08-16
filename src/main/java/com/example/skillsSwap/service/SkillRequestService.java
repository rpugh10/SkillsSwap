package com.example.skillsSwap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillsSwap.model.SkillRequest;
import com.example.skillsSwap.repository.SkillRequestRepository;

@Service
public class SkillRequestService {

    @Autowired
    private SkillRequestRepository repository;

    public SkillRequest createSkillRequest(SkillRequest request){
        return repository.save(request);
    }

    public Optional<SkillRequest> getSkillRequestById(Long id){
        return repository.findById(id);
    }

    public List<SkillRequest> getSkillRequestsByUserId(Long userId){
        return repository.findByRequesterId(userId);
    }

    public void deleteSkillRequest(Long id){
        repository.deleteById(id);
    }

    public SkillRequest updateSkillRequest(Long id, SkillRequest updatedRequest){
        return repository.findById(id)
            .map(existing -> {
                existing.setMessage(updatedRequest.getMessage());
                existing.setStatus(updatedRequest.getStatus());
                return repository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("Skill Request not found"));
    }
}
