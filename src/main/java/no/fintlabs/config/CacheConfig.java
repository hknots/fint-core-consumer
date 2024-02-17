package no.fintlabs.config;

import no.fint.model.FintMainObject;
import no.fintlabs.controller.cache.CoreCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CacheConfig {

    @Bean("resourceCache")
    public ConcurrentHashMap<String, CoreCache> resourceCache(Set<Class<? extends FintMainObject>> resources) {
        ConcurrentHashMap<String, CoreCache> resourceCache = new ConcurrentHashMap<>();
        resources.forEach(clazz -> resourceCache.put(clazz.getSimpleName().toLowerCase(), new CoreCache()));
        return resourceCache;
    }

}
