package no.fintlabs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.felles.kompleksedatatyper.Periode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Deprecated
public class Fravar implements FintMainObject {
    public enum Relasjonsnavn {
        ELEVFORHOLD,
        REGISTRERTAV,
        UNDERVISNINGSGRUPPE,
        EKSAMENSGRUPPE,
        FRAVARSTYPE
    }

    @Deprecated
    @NotNull
    private Boolean dokumentert;
    @NotNull
    private Boolean foresPaVitnemal;
    @NotNull
    private @Valid Periode gjelderPeriode;
    @NotBlank
    private String kommentar;
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
