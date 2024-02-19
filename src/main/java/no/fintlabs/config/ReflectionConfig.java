package no.fintlabs.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.FintMainObject;
import no.fint.model.FintRelation;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ReflectionConfig {

    private final ConsumerConfig props;

    private String getFullPackageName() {
        return String.format("no.fint.model.%s.%s", props.getDomainName(), props.getPackageName());
    }

    @Bean("commonResources")
    public Set<Class<? extends FintMainObject>> commonResources() {
        return new Reflections("no.fint.model.felles").getSubTypesOf(FintMainObject.class);
    }

    @Bean("mainResources")
    public Set<Class<? extends FintMainObject>> mainResources() {
        return new Reflections(getFullPackageName()).getSubTypesOf(FintMainObject.class);
    }

    @Bean("resources")
    public Set<Class<? extends FintMainObject>> resources(Set<Class<? extends FintMainObject>> mainResources, Set<Class<? extends FintMainObject>> commonResources) {
        return Stream.concat(mainResources.stream(), commonResources.stream())
                .filter(clazz -> mainResources.contains(clazz) || getRelationPackageNames(mainResources).contains(clazz.getName()))
                .collect(Collectors.toSet());
    }

    @Bean("resourceNames")
    public Set<String> resourceNames(Set<Class<? extends FintMainObject>> resources) {
        return resources.stream().map(clazz -> clazz.getSimpleName().toLowerCase()).collect(Collectors.toSet());
    }

    @Bean("identificatorNames")
    public Map<String, Set<String>> identificatorNames(Set<Class<? extends FintMainObject>> resources) {
        return resources.stream()
                .collect(Collectors.toMap(
                        clazz -> clazz.getSimpleName().toLowerCase(),
                        this::getIdentifikatorNames,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    private Set<String> getIdentifikatorNames(Class<? extends FintMainObject> clazz) {
        return processFintMainObject(clazz, instance -> instance.getIdentifikators().keySet());
    }

    private List<String> getRelationPackageNames(Set<Class<? extends FintMainObject>> resources) {
        return resources.stream()
                .flatMap(clazz -> processFintMainObject(clazz, fintMainObject ->
                        fintMainObject.getRelations().stream().map(FintRelation::getPackageName)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static <R> R processFintMainObject(Class<? extends FintMainObject> clazz, Function<FintMainObject, R> processor) {
        try {
            FintMainObject fintMainObject = clazz.getDeclaredConstructor().newInstance();
            return processor.apply(fintMainObject);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            log.error("Error processing FintMainObject: {} - {}", e.getMessage(), clazz.getSimpleName());
            return null;
        }
    }

}
