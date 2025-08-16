package com.example.skillsSwap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skillsSwap.model.SkillRequest;

public interface SkillRequestRepository extends JpaRepository<SkillRequest, Long>{
    List<SkillRequest> findByRequesterId(Long userId);
}
