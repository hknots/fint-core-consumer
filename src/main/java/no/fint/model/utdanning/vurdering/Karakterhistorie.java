package no.fint.model.utdanning.vurdering;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Karakterhistorie  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            OPPDATERTAV("no.fint.model.utdanning.elev.Skoleressurs", "0..1"),
            OPPRINNELIGKARAKTERVERDI("no.fint.model.utdanning.vurdering.Karakterverdi", "0..1"),
            OPPRINNELIGKARAKTERSTATUS("no.fint.model.utdanning.kodeverk.Karakterstatus", "0..1"),
            KARAKTERVERDI("no.fint.model.utdanning.vurdering.Karakterverdi", "0..1"),
            KARAKTERSTATUS("no.fint.model.utdanning.kodeverk.Karakterstatus", "0..1");
	
        private final String typeName;
        private final String multiplicity;

        private Relasjonsnavn(String typeName, String multiplicity) {
            this.typeName = typeName;
            this.multiplicity = multiplicity;
        }
    }

	
	@JsonIgnore
	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
		identifikators.put("systemId", this.systemId);
    
    	return identifikators;
	}


    @NotNull
    private @Valid Date endretDato;
    @NotNull
    private @Valid Identifikator systemId;
}
