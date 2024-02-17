package no.fintlabs.controller.cache;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.FintMainObject;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;

@ToString
@Slf4j
@AllArgsConstructor
public class CoreCache {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, CacheObject>> objectMapper;

    public void put(FintMainObject fintMainObject) {
        CacheObject cacheObject = new CacheObject(fintMainObject);
        fintMainObject.getIdentifikators().forEach((idField, identifikator) -> {
            if (identifikator != null) {
                objectMapper.get(idField).put(identifikator.getIdentifikatorverdi(), cacheObject);
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
