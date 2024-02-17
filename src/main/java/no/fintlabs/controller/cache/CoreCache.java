package no.fintlabs.controller.cache;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.FintMainObject;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ToString
@Slf4j
public class CoreCache {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, CacheObject>> objectMapper;

    @Getter
    private final AtomicLong cacheSize = new AtomicLong(0);

    @Getter
    private volatile long lastUpdated;

    public CoreCache(ConcurrentHashMap<String, ConcurrentHashMap<String, CacheObject>> objectMapper) {
        this.objectMapper = objectMapper;
        lastUpdated = System.currentTimeMillis();
    }

    public void put(FintMainObject fintMainObject) {
        CacheObject cacheObject = new CacheObject(fintMainObject);
        fintMainObject.getIdentifikators().forEach((idField, identifikator) -> {
            if (identifikator != null) {
                objectMapper.get(idField).put(identifikator.getIdentifikatorverdi(), cacheObject);
                lastUpdated = System.currentTimeMillis();
                cacheSize.incrementAndGet();
            }
        });
    }

    @Nullable
    public CacheObject findById(String idField, String idValue) {
        if (objectMapper.get(idField).containsKey(idValue)) {
            return objectMapper.get(idField).get(idValue);
        }
        return null;
    }

}
