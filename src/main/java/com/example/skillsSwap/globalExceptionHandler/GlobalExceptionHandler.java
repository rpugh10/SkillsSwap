package com.example.skillsSwap.globalExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.skillsSwap.apiError.ApiError;
import com.example.skillsSwap.exceptions.SkillNotFoundException;
import com.example.skillsSwap.exceptions.SkillRequestException;
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

    @ExceptionHandler(SkillRequestException.class)
    public ResponseEntity<ApiError> handleSkillRequestNotFound(SkillRequestException ex){
        ApiError error = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            "Skill request not found",
            ex.getMessage(),
            java.time.LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(
    MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });

    ApiError apiError = new ApiError(
        HttpStatus.BAD_REQUEST.value(),
        "Validation Error", 
        errors.toString(),
        LocalDateTime.now()
    );

    return ResponseEntity.badRequest().body(apiError);
    
}
    
    

    

}
