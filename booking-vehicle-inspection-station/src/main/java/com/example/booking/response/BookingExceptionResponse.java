package com.example.booking.response;


import java.time.LocalDateTime;

public class BookingExceptionResponse {
    private final LocalDateTime timestamp;
    private final String message;
    private final String details;

    public BookingExceptionResponse(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}