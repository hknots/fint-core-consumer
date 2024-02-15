package no.fintlabs.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import no.fint.event.model.EventResponse;
import no.fint.model.FintMainObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractCollection;
import java.util.Map;

import static no.fintlabs.config.EndPoints.*;

@Slf4j
@RestController(COMPONENT)
public class CoreController {

    @GetMapping(RESOURCE)
    public ResponseEntity<AbstractCollection<FintMainObject>> allResources(@PathVariable String resource) {
        return null;
    }

    @GetMapping(RESOURCE_ID)
    public ResponseEntity<FintMainObject> resourceById(@PathVariable String resource,
                                                       @PathVariable String idField,
                                                       @PathVariable String idValue) {
        return null;
    }

    @GetMapping(RESOURCE_LAST_UPDATED)
    public ResponseEntity<Map<String, String>> resourceLastUpdated(@PathVariable String resource) {
        return null;
    }

    @GetMapping(RESOURCE_CACHE_SIZE)
    public ResponseEntity<ImmutableMap<String, Integer>> resourceCacheSize(@PathVariable String resource) {
        return null;
    }

    @GetMapping(RESOURCE_STATUS_ID)
    public ResponseEntity<EventResponse> resourceCacheSize(@PathVariable String resource,
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
                                            @PathVariable String idValue) {
        return null;
    }

}