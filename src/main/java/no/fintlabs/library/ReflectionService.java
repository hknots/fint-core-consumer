package no.fintlabs.library;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import no.fintlabs.library.config.ConsumerConfig;
import no.fintlabs.model.FintMainObject;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ReflectionService {

    private final ConsumerConfig consumerConfig;

    @Getter
    private final Set<Class<? extends FintMainObject>> classes;

    public ReflectionService(ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
        this.classes = introspectClasses();
    }

    public Set<Class<? extends FintMainObject>> introspectClasses() {
        Reflections mainReflections = new Reflections(getPackageName());
        return mainReflections.getSubTypesOf(FintMainObject.class);
    }

    public Set<String> getFilteredClassNames() {
        return classes.stream()
                .map(clazz -> clazz.getSimpleName().toLowerCase().replace("resource", ""))
                .collect(Collectors.toSet());
    }

    private String getPackageName() {
        return String.format("no.fint.model.resource.%s.%s", consumerConfig.getDomain(), consumerConfig.getPackageName());
    }

}
