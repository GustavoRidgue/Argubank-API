package br.com.ridgue.argubankapi.config.interceptor;

import br.com.ridgue.argubankapi.exception.InvalidTokenFormatException;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.exception.TokenNotAuthenticatedException;
import br.com.ridgue.argubankapi.http.domain.response.DefaultResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new DefaultResponse("FAIL", e.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<DefaultResponse> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(403).body(new DefaultResponse("FAIL", e.getMessage()));
    }

    @ExceptionHandler(InvalidTokenFormatException.class)
    public ResponseEntity<DefaultResponse> handleInvalidTokenFormatException(InvalidTokenFormatException e) {
        return ResponseEntity.badRequest().body(new DefaultResponse("FAIL", e.getMessage()));
    }

    @ExceptionHandler(TokenNotAuthenticatedException.class)
    public ResponseEntity<DefaultResponse> handleTokenNotAuthenticatedException(TokenNotAuthenticatedException e) {
        return ResponseEntity.status(403).body(new DefaultResponse("FAIL", e.getMessage()));
    }
}
