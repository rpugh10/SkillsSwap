package com.example.skillsSwap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.skillsSwap.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
