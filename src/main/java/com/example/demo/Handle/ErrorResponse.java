package com.example.demo.Handle;


import lombok.Data;

@Data
public class ErrorResponse {
    private int error;
    private String message;
    private Long timespan;

    public ErrorResponse(int error, String message) {
        this.error = error;
        this.message = message;
        this.timespan = System.currentTimeMillis();
    }
}
