package no.fintlabs.reflection;

import lombok.RequiredArgsConstructor;
import no.fint.model.FintMainObject;
import no.fintlabs.config.ConsumerConfig;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Set;


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

}
