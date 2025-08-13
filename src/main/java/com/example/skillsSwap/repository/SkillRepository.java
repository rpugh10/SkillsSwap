package com.example.skillsSwap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skillsSwap.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{

    List<Skill> findByUserId(Long userId);
}
