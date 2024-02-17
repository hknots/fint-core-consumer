package no.fintlabs.controller.cache;

import lombok.Getter;
import no.fint.model.FintMainObject;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class CoreCache {

    private ConcurrentHashMap<String, String> idCache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, FintMainObject> objectCache = new ConcurrentHashMap<>();

    public void put(FintMainObject fintResource) {
        String id = UUID.randomUUID().toString();
        fintResource.getIdentifikators().values()
                .forEach(identifikator -> idCache.put(identifikator.getIdentifikatorverdi(), id));
        objectCache.put(id, fintResource);
    }

    public Collection<FintMainObject> findAll() {
        return objectCache.values();
    }

    @Nullable
    public FintMainObject findById(String id) {
        if (idCache.contains(id)) {
            return objectCache.get(idCache.get(id));
        }
        return null;
    }


}
