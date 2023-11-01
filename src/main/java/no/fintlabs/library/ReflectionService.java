package no.fintlabs.library;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.FintMainObject;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fintlabs.library.config.ConsumerConfig;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class ReflectionService {

    private final ConsumerConfig consumerConfig;

    @Getter
    private final Map<String, Class<? extends FintMainObject>> classMap;

    @Getter
    private final Map<String, Set<String>> identificatorMap;

    public ReflectionService(ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
        this.classMap = introspectClasses();
        this.identificatorMap = initializeIdentifikatorMap();
        log.info(identificatorMap.toString());
    }

    private Map<String, Set<String>> initializeIdentifikatorMap() {
        HashMap<String, Set<String>> newIdentifikatorMap = new HashMap<>();

        classMap.forEach((className, clazz) -> newIdentifikatorMap.put(className, getIdentificators(clazz)));

        return newIdentifikatorMap;
    }

    public Set<String> getIdentificators(Class<?> clazz) {
        Set<String> identifikatorFieldNames = new HashSet<>();

        for (Class<?> currentClass = clazz; currentClass != null; currentClass = currentClass.getSuperclass()) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.getType().equals(Identifikator.class)) {
                    identifikatorFieldNames.add(field.getName().toLowerCase());
                }
            }
        }
        return identifikatorFieldNames;
    }

    private HashMap<String, Class<? extends FintMainObject>> introspectClasses() {
        Reflections mainReflections = new Reflections(String.format("no.fint.model.%s.%s", consumerConfig.getDomain(), consumerConfig.getPackageName()));
        return mainReflections.getSubTypesOf(FintMainObject.class).stream()
                .collect(HashMap::new, (map, clazz) -> map.put(getClassNameWithoutResource(clazz.getSimpleName()), clazz), HashMap::putAll);
    }

    private String getClassNameWithoutResource(String className) {
        return className.toLowerCase().replace("resource", "");
    }

}
