package no.fintlabs.library.controller.cache;

import no.fint.model.FintMainObject;
import no.fintlabs.cache.Cache;
import no.fintlabs.library.ReflectionService;
import no.fintlabs.library.config.ConsumerConfig;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class CacheService {

    private final Map<String, Cache<? extends FintMainObject>> caches;
    private final ConsumerConfig consumerConfig;

    public CacheService(Map<String, Cache<? extends FintMainObject>> caches, ReflectionService reflectionService, ConsumerConfig consumerConfig) {
        this.caches = caches;
        this.consumerConfig = consumerConfig;
        initializeCaches(reflectionService.getClasses());
    }

}
