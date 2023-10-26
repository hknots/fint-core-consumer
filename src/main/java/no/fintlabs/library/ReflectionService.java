package no.fintlabs.library;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import no.fintlabs.model.FintMainObject;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ReflectionService {

    @Getter
    private final Map<String, Class<? extends FintMainObject>> classMap;

    public ReflectionService() {
        this.classMap = introspectClasses();
    }

    public HashMap<String, Class<? extends FintMainObject>> introspectClasses() {
        Reflections mainReflections = new Reflections("no.fintlabs.model");
        return mainReflections.getSubTypesOf(FintMainObject.class).stream()
                .collect(HashMap::new, (map, clazz) -> map.put(getClassNameWithoutResource(clazz.getSimpleName()), clazz), HashMap::putAll);
    }

    private String getClassNameWithoutResource(String className) {
        return className.toLowerCase().replace("resource", "");
    }

}
