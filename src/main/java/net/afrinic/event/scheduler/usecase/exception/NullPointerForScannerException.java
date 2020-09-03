package net.afrinic.event.scheduler.usecase.exception;

import lombok.Getter;

import java.time.LocalDateTime;

public class NullPointerForScannerException  extends RuntimeException {
    @Getter
    ErrorResponse errorResponse;

    public NullPointerForScannerException() {

        errorResponse = new ErrorResponse();
        errorResponse.setErrorDescription("Scanner Input is Empty, please confirm you are passing the right file or directory ");
        errorResponse.setResponseTime(LocalDateTime.now());
    }
}