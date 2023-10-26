package no.fintlabs.library.controller;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.core.resource.server.security.CorePrincipal;
import no.fintlabs.library.ReflectionService;
import no.fintlabs.library.controller.exceptions.ResourceNotFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Aspect
@Slf4j
@Component
public class ResourceValidationAspect {

    private final Set<String> resources;

    public ResourceValidationAspect(ReflectionService reflectionService) {
        resources = reflectionService.getClassMap().keySet();
    }

    @Pointcut(value = "execution(* no.fintlabs.library.controller.FintController.*(..)) && args(corePrincipal, resource, ..)",
            argNames = "corePrincipal,resource")
    public void allControllerMethods(CorePrincipal corePrincipal, String resource) {
    }

    @Before(value = "allControllerMethods(corePrincipal, resource)",
            argNames = "corePrincipal,resource")
    public void validateResourceBeforeMethod(CorePrincipal corePrincipal, String resource) {
        String username = Optional.ofNullable(corePrincipal)
                .map(CorePrincipal::getUsername)
                .orElse("localhost");

        log.debug("Validating for user: [{}] on resource: [{}]", username, resource);
        validateIfResourceExists(username, resource);
    }

    private void validateIfResourceExists(String username, String resource) {
        if (!resources.contains(resource.toLowerCase())) {
            log.warn("Resource not found for user: [{}] - [{}]", username, resource);
            throw new ResourceNotFoundException(resource);
        }
        log.debug("Resource validated successfully for user: [{}] - [{}]", username, resource);
    }

}
