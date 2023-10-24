package no.fintlabs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.fint.model.FintComplexDatatypeObject;
import no.fint.model.felles.kompleksedatatyper.Periode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Fravarsregistrering implements FintComplexDatatypeObject {
    public enum Relasjonsnavn {
        REGISTRERTAV,
        FAGGRUPPE,
        UNDERVISNINGSGRUPPE,
        FRAVARSTYPE
    }

    @NotNull
    private Boolean foresPaVitnemal;
    @NotBlank
    private String kommentar;
    @NotNull
    private @Valid Periode periode;
}
