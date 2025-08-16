package com.example.skillsSwap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillsSwap.service.SkillRequestService;

@RestController
public class SkillRequestController {

    @Autowired
    private SkillRequestService service;

    
}
