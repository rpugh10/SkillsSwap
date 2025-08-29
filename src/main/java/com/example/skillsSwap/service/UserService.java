package com.example.skillsSwap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillsSwap.dto.UserDTO;
import com.example.skillsSwap.exceptions.UserNotFoundException;
import com.example.skillsSwap.mapper.Mapper;
import com.example.skillsSwap.model.User;
import com.example.skillsSwap.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper mapper;

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user ->  mapper.toUserDTO(user))
                .toList();
    }

    public UserDTO postUser(UserDTO newUser){
        User userEntity = mapper.toUser(newUser);
        User savedUser = userRepository.save(userEntity);
        return mapper.toUserDTO(savedUser);
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toUserDTO(user);
    }
    
    public UserDTO updateUser(Long id, UserDTO userDTO){
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
        
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setBio(userDTO.getBio());

            User updatedUser = userRepository.save(existingUser);
            return mapper.toUserDTO(updatedUser);
    }

    public void deleteUser(Long id){
        userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

}
