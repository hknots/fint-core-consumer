package no.fintlabs.library.controller.validation;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.core.resource.server.security.CorePrincipal;
import no.fintlabs.library.ReflectionService;
import no.fintlabs.library.controller.exceptions.IdentificatorNotFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Aspect
@Slf4j
@Component
@Order(2)
public class IdentificatorValidationAspect {

    private final Map<String, Set<String>> identificators;

    public IdentificatorValidationAspect(ReflectionService reflectionService) {
        identificators = reflectionService.getIdentificatorMap();
    }

    @Pointcut(value = "execution(* no.fintlabs.library.controller.CoreRestController.getResourceById(..)) && args(corePrincipal,..,resource,idField)",
            argNames = "corePrincipal,resource,idField")
    public void getResourceByIdPointcut(CorePrincipal corePrincipal, String resource, String idField) {
    }

    @Before(value = "getResourceByIdPointcut(corePrincipal, resource, idField)",
            argNames = "corePrincipal,resource,idField")
    public void beforeGetResourceById(CorePrincipal corePrincipal, String resource, String idField) {
        String username = getUsername(corePrincipal);

        if (idFieldDoesntExist(resource, idField)) {
            log.warn("Identificator Field not found for user: [{}] - [{}] - [{}]", username, resource, idField);
            throw new IdentificatorNotFoundException(resource, idField);
        }
    }

    private String getUsername(CorePrincipal corePrincipal) {
        return Optional.ofNullable(corePrincipal)
                .map(CorePrincipal::getUsername)
                .orElse("localhost");
    }

    private boolean idFieldDoesntExist(String resource, String idField) {
        return !identificators.getOrDefault(resource, Collections.emptySet()).contains(idField.toLowerCase());
    }

}
