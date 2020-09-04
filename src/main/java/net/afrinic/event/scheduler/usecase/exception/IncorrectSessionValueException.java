package net.afrinic.event.scheduler.usecase.exception;

import lombok.Getter;

import java.time.LocalDateTime;

public class IncorrectSessionValueException extends RuntimeException {

    public IncorrectSessionValueException() {

        var errorResponse = new ErrorResponse();
        errorResponse.setErrorDescription("Sessions is less than 2");
        errorResponse.setResponseTime(LocalDateTime.now());
    }
}
