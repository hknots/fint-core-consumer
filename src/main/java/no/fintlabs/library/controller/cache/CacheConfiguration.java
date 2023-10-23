package no.fintlabs.library.controller.cache;

import no.fint.model.FintMainObject;
import no.fintlabs.cache.Cache;
import no.fintlabs.library.ReflectionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CacheConfiguration {

    private final ReflectionService reflectionService;

    public CacheConfiguration(ReflectionService reflectionService) {
        this.reflectionService = reflectionService;
    }

    @Bean
    public Map<String, Cache<? extends FintMainObject>> getCaches() {
        return reflectionService.getClasses().stream()
                .map(Class::getSimpleName)
                .flatMap()
    }

}
