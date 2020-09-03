package net.afrinic.event.scheduler.usecase.exception;

import lombok.Getter;

import java.time.LocalDateTime;

public class FilePathInvalidException  extends RuntimeException {
    @Getter
    ErrorResponse errorResponse;
    public FilePathInvalidException() {

        errorResponse = new ErrorResponse();
        errorResponse.setErrorDescription("No such file or directory");
        errorResponse.setResponseTime(LocalDateTime.now());
    }
}
