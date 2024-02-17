package no.fintlabs.controller.exception;

import no.fintlabs.controller.exception.exceptions.IdentificatorFieldNotFound;
import no.fintlabs.controller.exception.exceptions.EntityNotFoundException;
import no.fintlabs.controller.exception.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoreExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class, IdentificatorFieldNotFound.class, EntityNotFoundException.class})
    public ResponseEntity<Void> handleResourceNotFoundException() {
        return ResponseEntity.notFound().build();
    }

}
