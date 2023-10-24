package no.fintlabs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.fint.model.FintAbstractObject;
import no.fint.model.felles.kompleksedatatyper.Identifikator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Fagvurdering implements FintAbstractObject {
    public enum Relasjonsnavn {
        FAG,
        UNDERVISNINGSGRUPPE,
        SKOLEAR,
        KARAKTER
    }

    @NotBlank
    private String kommentar;
    @NotNull
    private @Valid Identifikator systemId;
    @NotNull
    private Date vurderingsdato;
}
