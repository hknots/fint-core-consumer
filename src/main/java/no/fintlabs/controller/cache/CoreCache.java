package no.fintlabs.controller.cache;

import no.fint.model.FintMainObject;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CoreCache {

    private final ConcurrentHashMap<String, String> idMapper = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, FintMainObject> objectCache = new ConcurrentHashMap<>();

    public void put(FintMainObject fintObject) {
        String id = UUID.randomUUID().toString();
        fintObject.getIdentifikators().values()
                .forEach(identifikator -> idMapper.put(identifikator.getIdentifikatorverdi(), id));
        objectCache.put(id, fintObject);
    }

    public Collection<FintMainObject> findAll() {
        return objectCache.values();
    }

    @Nullable
    public FintMainObject findById(String id) {
        if (idMapper.contains(id)) {
            return objectCache.get(idMapper.get(id));
        }
        return null;
    }


}
