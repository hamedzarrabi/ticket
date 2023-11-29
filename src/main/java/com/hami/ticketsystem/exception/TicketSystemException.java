package com.hami.ticketsystem.exception;

import org.springframework.http.HttpStatus;

public class TicketSystemException extends RuntimeException{
    private final HttpStatus status;
    private final String message;

    public TicketSystemException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public TicketSystemException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
