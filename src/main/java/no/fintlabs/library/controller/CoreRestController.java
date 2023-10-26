package no.fintlabs.library.controller;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.FintMainObject;
import no.fintlabs.core.resource.server.security.CorePrincipal;
import no.fintlabs.library.config.ConfigConstants;
import no.fintlabs.library.controller.cache.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.AbstractCollection;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(ConfigConstants.RESOURCE_PATH)
public class CoreRestController {

    private final CacheService cacheService;

    public CoreRestController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping
    public Mono<ResponseEntity<AbstractCollection<? extends FintMainObject>>> getResources(@AuthenticationPrincipal CorePrincipal corePrincipal,
                                                                                           @PathVariable String resource,
                                                                                           @RequestParam(defaultValue = "0") long sinceTimeStamp,
                                                                                           @RequestParam(defaultValue = "0") int size,
                                                                                           @RequestParam(defaultValue = "0") int offset,
                                                                                           @RequestParam(required = false) String $filter) {
        return Mono.just(ResponseEntity.ok().build());
    }

    @GetMapping("/{idName}/{idValue}")
    public Mono<ResponseEntity<? extends FintMainObject>> getResource(@AuthenticationPrincipal CorePrincipal coreprincipal,
                                                                      @PathVariable String resource,
                                                                      @PathVariable String idName,
                                                                      @PathVariable String idValue) {
        return Mono.just(ResponseEntity.ok().build());
    }

    @GetMapping("/last-updated")
    public Mono<ResponseEntity<Map<String, String>>> getLastUpdated(@AuthenticationPrincipal CorePrincipal corePrincipal,
                                                                    @PathVariable String resource) {
        return Mono.just(ResponseEntity.ok().build());
    }

    @GetMapping("/cache/size")
    public Mono<ResponseEntity<Map<String, Integer>>> getCacheSize(@AuthenticationPrincipal CorePrincipal corePrincipal,
                                                                   @PathVariable String resource) {
        return Mono.just(ResponseEntity.ok().build());
    }

}
