package no.fintlabs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.utdanning.vurdering.Fravarsprosent;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Fravarsoversikt implements FintMainObject {
    public enum Relasjonsnavn {
        ELEVFORHOLD,
        FAG
    }

    @NotNull
    private @Valid Fravarsprosent halvar;
    @NotNull
    private @Valid Fravarsprosent skolear;
    @NotNull
    private @Valid Identifikator systemId;

    @Override
    public Map<String, Identifikator> getIdentifikators() {
        Map<String, Identifikator> map = new HashMap<>();

        Class<?> currentClass = this.getClass();

        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.getType().equals(Identifikator.class)) {
                    try {
                        Identifikator identifikator = (Identifikator) field.get(this);
                        if (identifikator != null) {
                            map.put(field.getName(), identifikator);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        return map;
    }

}
