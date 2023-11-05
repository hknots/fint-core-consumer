package no.fintlabs.library.controller.cache;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import no.fint.model.FintMainObject;
import no.fintlabs.library.ReflectionService;
import no.fintlabs.library.controller.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

@Service
public class CacheService {

    private final Map<String, AsyncCache<String, FintMainObject>> caches;
    private final ConcurrentMap<String, String> idToIndex;

    public CacheService(ReflectionService reflectionService) {
        this.caches = new ConcurrentHashMap<>();
        this.idToIndex = new ConcurrentHashMap<>();
        reflectionService.getClassMap().keySet().forEach(resourceType -> {
            this.caches.put(resourceType, Caffeine.newBuilder()
                    .maximumSize(1000)
                    .expireAfterWrite(5, TimeUnit.MINUTES)
                    .executor(Executors.newCachedThreadPool())
                    .buildAsync());
        });
    }

    public CompletableFuture<FintMainObject> put(String resourceType, List<String> identifiers, FintMainObject object) {
        AsyncCache<String, FintMainObject> cache = caches.get(resourceType);
        String uniqueIndex = UUID.randomUUID().toString();
        CompletableFuture<FintMainObject> future = CompletableFuture.completedFuture(object);

        if (cache != null) {
            cache.put(uniqueIndex, future);
            identifiers.forEach(identifier -> idToIndex.put(identifier, uniqueIndex));
        }

        return future;
    }

    public CompletableFuture<FintMainObject> get(String resourceType, String identifier) {
        AsyncCache<String, FintMainObject> cache = caches.get(resourceType);
        String uniqueIndex = idToIndex.get(identifier);
        if (cache != null && uniqueIndex != null) {
            return cache.getIfPresent(uniqueIndex);
        }
        throw new ResourceNotFoundException(resourceType);
    }

    public void remove(String resourceType, String identifier) {
        AsyncCache<String, FintMainObject> cache = caches.get(resourceType);
        String uniqueIndex = idToIndex.remove(identifier);
        if (cache != null && uniqueIndex != null) {
            cache.synchronous().invalidate(uniqueIndex);
        }
    }

}
