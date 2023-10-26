package no.fintlabs.library.controller.cache;

import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheObjectFactory;
import no.fintlabs.cache.FintCache;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.library.ReflectionService;
import no.fintlabs.library.config.ConsumerConfig;
import no.fintlabs.model.FintMainObject;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheService {

    private final ConsumerConfig consumerConfig;
    private final Map<String, Cache<? extends FintMainObject>> caches;

    public CacheService(ConsumerConfig consumerConfig, ReflectionService reflectionService) {
        this.consumerConfig = consumerConfig;
        caches = initializeCaches(reflectionService.getClassMap());
    }

    private Map<String, Cache<? extends FintMainObject>> initializeCaches(Map<String, Class<? extends FintMainObject>> classMap) {
        return null;
    }

    private <T extends FintMainObject> FintCache<T> getCache() {
        new FintCache<>(new CacheObjectFactory<>(PackingTypes.POJO), "urn?");
    }

}
