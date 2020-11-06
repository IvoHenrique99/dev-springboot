package br.com.dev.handler;

import br.com.dev.error.ResoucerNotFoundDetails;
import br.com.dev.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ResoucerNotFoundDetails rnfDetails = ResoucerNotFoundDetails.Builder
               .newBuilder()
               .timestamp(new Date().getTime())
               .status(HttpStatus.NOT_FOUND.value())
               .title("Resource not found")
               .detail(rfnException.getMessage())
               .developerMessage(rfnException.getClass().getName())
               .build();
       return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }
}
