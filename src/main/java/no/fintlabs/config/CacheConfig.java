package no.fintlabs.config;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.FintMainObject;
import no.fintlabs.controller.cache.CacheObject;
import no.fintlabs.controller.cache.CoreCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Slf4j
public class CacheConfig {

    @Bean("cacheContainerRegistry")
    public ConcurrentHashMap<String, CoreCache> resourceCache(Set<Class<? extends FintMainObject>> resources) {
        ConcurrentHashMap<String, CoreCache> resourceCache = new ConcurrentHashMap<>();
        resources.forEach(clazz -> {
            try {
                FintMainObject instance = clazz.getDeclaredConstructor().newInstance();
                ConcurrentHashMap<String, ConcurrentHashMap<String, CacheObject>> idMapper = new ConcurrentHashMap<>();

                instance.getIdentifikators().keySet().forEach(idField -> {
                    idMapper.put(idField, new ConcurrentHashMap<>());
                });

                resourceCache.put(clazz.getSimpleName().toLowerCase(), new CoreCache(idMapper));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                log.error("Error generating resourceCache: {}", e.getMessage());
            }
        });
        return resourceCache;
    }

}
