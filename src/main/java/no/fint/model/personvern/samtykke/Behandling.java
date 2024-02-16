package no.fint.model.personvern.samtykke;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.validation.Valid;
import javax.validation.constraints.*;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.FintMainObject;
import no.fint.model.FintIdentifikator;
import java.util.Date;
import no.fint.model.felles.kompleksedatatyper.Identifikator;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Behandling  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            BEHANDLINGSGRUNNLAG("no.fint.model.personvern.kodeverk.Behandlingsgrunnlag", "1"),
            PERSONOPPLYSNING("no.fint.model.personvern.kodeverk.Personopplysning", "1"),
            SAMTYKKE("no.fint.model.personvern.samtykke.Samtykke", "0..*"),
            TJENESTE("no.fint.model.personvern.samtykke.Tjeneste", "1");
	
        private final String typeName;
        private final String multiplicity;

        private Relasjonsnavn(String typeName, String multiplicity) {
            this.typeName = typeName;
            this.multiplicity = multiplicity;
        }
    }


	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
		identifikators.put("systemId", this.systemId);
    
    	return identifikators;
	}


    @NotNull
    private Boolean aktiv;
    @NotBlank
    private String formal;
    private @Valid Date slettet;
    @NotNull
    private @Valid Identifikator systemId;
}