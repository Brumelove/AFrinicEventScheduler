package net.afrinic.event.scheduler.usecase.exception;

import lombok.Getter;

import java.time.LocalDateTime;

public class EventDurationInvalidException extends RuntimeException {

    public EventDurationInvalidException() {

        var errorResponse = new ErrorResponse();
        errorResponse.setErrorDescription("Please check that your String input has a 'Xmin' format, where X is a digit of any length");
        errorResponse.setResponseTime(LocalDateTime.now());
    }
}
