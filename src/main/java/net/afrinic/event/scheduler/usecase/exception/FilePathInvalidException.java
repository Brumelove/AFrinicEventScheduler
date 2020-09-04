package net.afrinic.event.scheduler.usecase.exception;


import java.time.LocalDateTime;

public class FilePathInvalidException extends RuntimeException {

    public FilePathInvalidException() {

        var errorResponse = new ErrorResponse();
        errorResponse.setErrorDescription("No such file or directory");
        errorResponse.setResponseTime(LocalDateTime.now());
    }
}
