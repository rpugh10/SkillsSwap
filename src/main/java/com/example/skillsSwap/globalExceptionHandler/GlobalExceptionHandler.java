package com.example.skillsSwap.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.skillsSwap.apiError.ApiError;
import com.example.skillsSwap.exceptions.SkillNotFoundException;
import com.example.skillsSwap.exceptions.UserNotFoundException;

@RestControllerAdvice   
public class GlobalExceptionHandler {

    @ExceptionHandler(SkillNotFoundException.class)
    public ResponseEntity<ApiError> handleSkillNotFound(SkillNotFoundException ex){
        ApiError error = new ApiError(
        HttpStatus.NOT_FOUND.value(),
        "Skill not found",
        ex.getMessage(),
        java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex){
        ApiError error = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            "User not found",
            ex.getMessage(),
            java.time.LocalDateTime.now()
           );
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> runtimeException(RuntimeException ex){
        ApiError error = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            "Server error",
            ex.getMessage(),
            java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    

}
