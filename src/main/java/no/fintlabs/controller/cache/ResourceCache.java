package no.fintlabs.controller.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class ResourceCache {

    private final ConcurrentHashMap<String, CoreCache> resourceCache;

    public ResourceCache(@Qualifier("cacheContainerRegistry") ConcurrentHashMap<String, CoreCache> resourceCache) {
        this.resourceCache = resourceCache;
    }

    public CoreCache getContainer(String resource) {
        return resourceCache.get(resource);
    }

}
