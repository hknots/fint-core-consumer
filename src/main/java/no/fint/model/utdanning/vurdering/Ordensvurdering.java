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
import no.fint.model.FintAbstractObject;
import no.fint.model.FintIdentifikator;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Ordensvurdering  implements FintAbstractObject {
    @Getter
    public enum Relasjonsnavn {
            ATFERD("no.fint.model.utdanning.vurdering.Karakterverdi", "1"),
            ORDEN("no.fint.model.utdanning.vurdering.Karakterverdi", "1"),
            SKOLEAR("no.fint.model.utdanning.kodeverk.Skolear", "0..1");
	
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


    @NotBlank
    private String kommentar;
    @NotNull
    private @Valid Identifikator systemId;
    @NotNull
    private Date vurderingsdato;
}
