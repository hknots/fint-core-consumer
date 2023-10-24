package no.fintlabs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.utdanning.vurdering.Fagvurdering;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Halvarsfagvurdering extends Fagvurdering implements FintMainObject {
    public enum Relasjonsnavn {
        ELEVFORHOLD
    }

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
