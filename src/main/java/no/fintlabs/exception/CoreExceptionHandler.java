package no.fintlabs.exception;

import no.fintlabs.exception.exceptions.IdentificatorFieldNotFound;
import no.fintlabs.exception.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoreExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class, IdentificatorFieldNotFound.class})
    public ResponseEntity<Void> handleResourceNotFoundException() {
        return ResponseEntity.notFound().build();
    }

}
