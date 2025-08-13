package com.example.skillsSwap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillsSwap.repository.SkillRequestRepository;

@Service
public class SkillRequestService {

    @Autowired
    private SkillRequestRepository repository;
}
