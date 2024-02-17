package no.fintlabs.controller.aspect;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.exception.exceptions.ResourceNotFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@Aspect
@Order(0)
public class ResourceAspect {

    private final Set<String> resourceNames;

    public ResourceAspect(Set<String> resourceNames) {
        this.resourceNames = resourceNames;
    }

    @Before("execution(* no.fintlabs.controller.CoreController.*(..)) && args(resource,..)")
    public void validateResource(String resource) {
        log.trace("Validating resource: [{}]", resource);
        if (!resourceNames.contains(resource)) {
            log.debug("Resource not found: {}", resource);
            throw new ResourceNotFoundException();
        }
    }

}
