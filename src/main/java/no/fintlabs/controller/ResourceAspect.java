package no.fintlabs.controller;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.exception.exceptions.ResourceNotFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect
@Component
@Slf4j
public class ResourceAspect {

    private final Set<String> resourceNames;

    public ResourceAspect(Set<String> resourceNames) {
        this.resourceNames = resourceNames;
    }

    @Before("execution(* no.fintlabs.controller.CoreController.*(..)) && args(resource,..)")
    public void validateResource(String resource) {
        if (!resourceNames.contains(resource)) {
            log.debug("Resource not found: {}", resource);
            throw new ResourceNotFoundException();
        }
    }
}
