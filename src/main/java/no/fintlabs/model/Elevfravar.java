package no.fintlabs.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.*;
import no.fint.model.utdanning.vurdering.Fravarsregistrering;
import no.fint.model.felles.kompleksedatatyper.Identifikator;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Elevfravar implements FintMainObject {
    public enum Relasjonsnavn {
            ELEVFORHOLD
    }

    private List<@Valid Fravarsregistrering> fravar;
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
