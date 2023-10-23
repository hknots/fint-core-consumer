package no.fintlabs.library.controller.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource) {
        super("Resource " + resource + " not found");
    }
}
