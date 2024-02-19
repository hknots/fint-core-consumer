package no.fintlabs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.fint.event.model.EventResponse;
import no.fint.model.FintMainObject;
import no.fintlabs.controller.cache.CacheObject;
import no.fintlabs.controller.cache.ResourceCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractCollection;
import java.util.Map;

import static no.fintlabs.controller.EndPoints.*;

@Slf4j
@RequestMapping(COMPONENT)
@RestController
@RequiredArgsConstructor
public class CoreController {

    private final ResourceCache resourceCache;

    @GetMapping(RESOURCE)
    public ResponseEntity<AbstractCollection<FintMainObject>> allResources(@PathVariable String resource) {
        return null;
    }

    @GetMapping(RESOURCE_ID)
    public ResponseEntity<FintMainObject> resourceById(@PathVariable String resource,
                                                       @PathVariable String idField,
                                                       @PathVariable String idValue) {
        CacheObject cacheObject = resourceCache.getContainer(resource).findById(idField, idValue);
        if (cacheObject != null) {
            return ResponseEntity.ok(cacheObject.getResource());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(RESOURCE_LAST_UPDATED)
    public ResponseEntity<Map<String, Long>> resourceLastUpdated(@PathVariable String resource) {
        return ResponseEntity.ok(Map.of("lastUpdated", resourceCache.getContainer(resource).getLastUpdated()));
    }

    @GetMapping(RESOURCE_CACHE_SIZE)
    public ResponseEntity<Map<String, Long>> resourceCacheSize(@PathVariable String resource) {
        return ResponseEntity.ok(Map.of("size", resourceCache.getContainer(resource).getCacheSize().get()));
    }

    @GetMapping(RESOURCE_STATUS_ID)
    public ResponseEntity<EventResponse> resourceStatusId(@PathVariable String resource,
                                                          @PathVariable String idValue) {
        return null;
    }

    @PostMapping(RESOURCE)
    public ResponseEntity<Void> postResource(@PathVariable String resource,
                                             @RequestBody Map<String, Object> request) {
        return null;
    }

    @PutMapping(RESOURCE_ID)
    public ResponseEntity<Void> putResource(@PathVariable String resource,
                                            @PathVariable String idField,
                                            @PathVariable String idValue,
                                            @RequestBody Map<String, Object> request) {
        return null;
    }

}
