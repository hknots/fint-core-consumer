package no.fint.model.arkiv.personal;

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
import no.fint.model.felles.kompleksedatatyper.Personnavn;
import no.fint.model.arkiv.noark.Saksmappe;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class Personalmappe extends Saksmappe  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            PERSON("no.fint.model.felles.Person", "1"),
            LEDER("no.fint.model.administrasjon.personal.Personalressurs", "1"),
            ARBEIDSSTED("no.fint.model.administrasjon.organisasjon.Organisasjonselement", "1"),
            PERSONALRESSURS("no.fint.model.administrasjon.personal.Personalressurs", "1");
	
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
		identifikators.putAll(super.getIdentifikators());
    
    	return identifikators;
	}


    @NotNull
    private @Valid Personnavn navn;
}
