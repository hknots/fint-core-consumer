package no.fintlabs.reflection;

import lombok.RequiredArgsConstructor;
import no.fint.model.FintMainObject;
import no.fintlabs.config.ConsumerConfig;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReflectionService {

    private final ConsumerConfig props;

    private String getFullPackageName() {
        return String.format("no.fint.model.%s.%s", props.getDomainName(), props.getPackageName());
    }

    @Bean
    public Set<Class<? extends FintMainObject>> resources() {
        return new Reflections(getFullPackageName()).getSubTypesOf(FintMainObject.class);
    }

    // TODO: Add common entities as well
    @Bean("resourceNames")
    public Set<String> resourceNames(Set<Class<? extends FintMainObject>> resources) {
        return resources.stream()
                .map(clazz -> clazz.getSimpleName().toLowerCase())
                .collect(Collectors.toUnmodifiableSet());
    }

}
