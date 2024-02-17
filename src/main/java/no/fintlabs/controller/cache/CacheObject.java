package no.fintlabs.controller.cache;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import no.fint.model.FintIdentifikator;
import no.fint.model.FintMainObject;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class CacheObject {

    public CacheObject(FintMainObject resource) {
        this.resource = resource;
        this.identificatorValues = extractIdentifikatorverdi(resource.getIdentifikators());
    }

    private Map<String, String> identificatorValues;
    private final FintMainObject resource;
    private long lastUpdated;

    private Map<String, String> extractIdentifikatorverdi(Map<String, FintIdentifikator> originalIdentifikators) {
        Map<String, String> identifikatorsVerdi = new HashMap<>();
        originalIdentifikators.forEach((key, value) -> {
            if (value != null) {
                identifikatorsVerdi.put(key, value.getIdentifikatorverdi());
            }
        });
        return identifikatorsVerdi;
    }

}
