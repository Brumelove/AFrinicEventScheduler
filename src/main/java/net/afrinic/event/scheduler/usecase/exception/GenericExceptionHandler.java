package net.afrinic.event.scheduler.usecase.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.util.stream.Collectors.joining;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GenericExceptionHandler {

    @ExceptionHandler(IncorrectSessionValueException.class)
    public ResponseEntity<ErrorResponse> incorrectSessionValueException(IncorrectSessionValueException incorrectSessionValueException){
        return new ResponseEntity<>(incorrectSessionValueException.getErrorResponse(), HttpStatus.PRECONDITION_FAILED);
    }
    @ExceptionHandler(EventDurationInvalidException.class)
    public ResponseEntity<ErrorResponse> eventDurationInvalidException(EventDurationInvalidException eventDurationInvalidException){
        return new ResponseEntity<>(eventDurationInvalidException.getErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerForScannerException.class)
    public ResponseEntity<ErrorResponse> nullPointerForScannerException(NullPointerForScannerException nullPointerForScannerException){
        return new ResponseEntity<>(nullPointerForScannerException.getErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(FilePathInvalidException.class)
    public ResponseEntity<ErrorResponse> filePathInvalidException(FilePathInvalidException filePathInvalidException){
        return new ResponseEntity<>(filePathInvalidException.getErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
