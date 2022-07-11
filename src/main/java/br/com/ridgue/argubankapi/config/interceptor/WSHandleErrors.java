package br.com.ridgue.argubankapi.config.interceptor;

import br.com.ridgue.argubankapi.http.domain.response.DefaultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WSHandleErrors {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultResponse> handleInternalServerError(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new DefaultResponse("ERROR", e.getMessage()));
    }
}
