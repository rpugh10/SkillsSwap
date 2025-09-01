package com.example.skillsSwap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillsSwap.dto.SkillRequestDTO;
import com.example.skillsSwap.exceptions.SkillNotFoundException;
import com.example.skillsSwap.exceptions.SkillRequestException;
import com.example.skillsSwap.exceptions.UserNotFoundException;
import com.example.skillsSwap.mapper.Mapper;
import com.example.skillsSwap.model.Skill;
import com.example.skillsSwap.model.SkillRequest;
import com.example.skillsSwap.model.User;
import com.example.skillsSwap.repository.SkillRepository;
import com.example.skillsSwap.repository.SkillRequestRepository;
import com.example.skillsSwap.repository.UserRepository;

@Service
public class SkillRequestService {

    @Autowired
    private SkillRequestRepository repository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    public SkillRequestDTO createSkillRequest(Long userId, Long skillId, SkillRequestDTO dto){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        Skill skill = skillRepository.findById(skillId)
            .orElseThrow(() -> new SkillNotFoundException(skillId));

        SkillRequest request = mapper.toSkillRequest(dto, user, skill);
        request.setRequester(user);
        request.setSkill(skill);

        SkillRequest savRequest = repository.save(request);

        return mapper.toSkillRequestDTO(savRequest);
    }

    public SkillRequestDTO getSkillRequestById(Long id){
        SkillRequest request = repository.findById(id)
            .orElseThrow(() -> new SkillRequestException(id));
        return mapper.toSkillRequestDTO(request);
    }

    public List<SkillRequestDTO> getSkillRequestsByUserId(Long userId){
        userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
         return repository.findByRequesterId(userId)
            .stream()
            .map(mapper::toSkillRequestDTO)
            .toList();
    }

    public void deleteSkillRequest(Long id){
        repository.findById(id)
            .orElseThrow(() -> new SkillRequestException(id));
        repository.deleteById(id);
    }

    public SkillRequestDTO updateSkillRequest(Long id, SkillRequestDTO request){
       SkillRequest updatedRequest = repository.findById(id)
        .orElseThrow(() -> new SkillRequestException(id));

        updatedRequest.setMessage(request.getMessage());
        updatedRequest.setStatus(request.getStatus());

        SkillRequest updated = repository.save(updatedRequest);
        return mapper.toSkillRequestDTO(updated);
    }

    
}
