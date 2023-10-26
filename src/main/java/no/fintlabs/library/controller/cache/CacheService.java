package no.fintlabs.library.controller.cache;

import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.library.ReflectionService;
import no.fintlabs.library.config.ConsumerConfig;
import no.fintlabs.model.FintMainObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {

    private final CacheManager cacheManager;
    private final ConsumerConfig consumerConfig;
    private final Map<String, Cache<? extends FintMainObject>> caches;

    public CacheService(CacheManager cacheManager, ConsumerConfig consumerConfig, ReflectionService reflectionService) {
        this.cacheManager = cacheManager;
        this.consumerConfig = consumerConfig;
        this.caches = initializeCaches(reflectionService.getClassMap());
    }

    public Cache<? extends FintMainObject> getCache(String resourceName) {
        return caches.get(resourceName);
    }

    private Map<String, Cache<? extends FintMainObject>> initializeCaches(Map<String, Class<? extends FintMainObject>> classMap) {
        Map<String, Cache<? extends FintMainObject>> tempCaches = new HashMap<>();

        classMap.forEach((clazzName, clazz) -> tempCaches.put(clazzName, createCache(clazzName)));

        return tempCaches;
    }

    private <T extends FintMainObject> Cache<T> createCache(String resourceName) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), resourceName);
    }

}
