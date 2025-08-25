package com.example.skillsSwap.apiError;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiError {

    private int status;
    private String error;
    private String message;
    private LocalDateTime timeStamp;
    
    public ApiError(int status, String error, String message, LocalDateTime timeStamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timeStamp = timeStamp;
    }

}
