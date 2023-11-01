package no.fintlabs.library.controller.exceptions;

public class IdentificatorNotFoundException extends RuntimeException {
    public IdentificatorNotFoundException(String resource, String idField) {
        super("Identificator Field " + idField + " not found for Resource " + resource);
    }
}
