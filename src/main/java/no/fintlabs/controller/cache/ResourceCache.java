package no.fintlabs.controller.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ResourceCache {

    private final ConcurrentHashMap<String, CoreCache> resourceCache;

    public CoreCache getContainer(String resource) {
        return resourceCache.get(resource);
    }

}
