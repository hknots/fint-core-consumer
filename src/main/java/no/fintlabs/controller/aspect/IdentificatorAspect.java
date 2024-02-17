package no.fintlabs.controller.aspect;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.controller.exception.exceptions.IdentificatorFieldNotFound;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@Aspect
@Order(1)
public class IdentificatorAspect {

    private final Map<String, Set<String>> identificatorMap;

    public IdentificatorAspect(@Qualifier("identificatorNames") Map<String, Set<String>> identificatorMap) {
        this.identificatorMap = identificatorMap;
    }

    @Before(value = "execution(* no.fintlabs.controller.CoreController.*(..)) && args(resource, idField, ..)")
    public void validateIdField(String resource, String idField) {
        log.trace("Validating idField: [{}] for [{}]", idField, resource);
        if (!identificatorMap.get(resource).contains(idField)) {
            log.debug("Resource: {} does not have idField: {}", resource, idField);
            throw new IdentificatorFieldNotFound();
        }
    }

}
