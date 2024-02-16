package no.fint.model.utdanning.vurdering;

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
import no.fint.model.felles.kompleksedatatyper.Identifikator;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Deprecated
public class Vurdering  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            ELEVFORHOLD("no.fint.model.utdanning.elev.Elevforhold", "0..1"),
            FAG("no.fint.model.utdanning.timeplan.Fag", "0..1"),
            UNDERVISNINGSGRUPPE("no.fint.model.utdanning.timeplan.Undervisningsgruppe", "0..1"),
            EKSAMENSGRUPPE("no.fint.model.utdanning.vurdering.Eksamensgruppe", "0..1"),
            KARAKTER("no.fint.model.utdanning.vurdering.Karakterverdi", "1");
	
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
    private Boolean endelig;
    @NotBlank
    private String kommentar;
    @NotNull
    private @Valid Identifikator systemId;
}