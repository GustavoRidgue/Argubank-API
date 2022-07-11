package br.com.ridgue.argubankapi.config.interceptor;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.http.domain.response.ClientResponse;
import br.com.ridgue.argubankapi.http.domain.response.DefaultResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class WSHandleErrors {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultResponse> handleInternalServerError(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new DefaultResponse("ERROR", e.getMessage()));
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<DefaultResponse> handlePropertyReferenceException(PropertyReferenceException e) {
        return ResponseEntity.badRequest().body(new DefaultResponse("FAIL", e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DefaultResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.badRequest().body(new DefaultResponse("FAIL", e.getMessage()));
    }
}
