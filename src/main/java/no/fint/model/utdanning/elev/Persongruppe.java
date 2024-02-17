package no.fint.model.utdanning.elev;

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
import no.fint.model.utdanning.basisklasser.Gruppe;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class Persongruppe extends Gruppe  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            ELEV("no.fint.model.utdanning.elev.Elev", "0..*"),
            PERSONGRUPPEMEDLEMSKAP("no.fint.model.utdanning.elev.Persongruppemedlemskap", "0..*"),
            TERMIN("no.fint.model.utdanning.kodeverk.Termin", "0..*"),
            UNDERVISNINGSFORHOLD("no.fint.model.utdanning.elev.Undervisningsforhold", "0..*"),
            SKOLE("no.fint.model.utdanning.utdanningsprogram.Skole", "0..*"),
            SKOLERESSURS("no.fint.model.utdanning.elev.Skoleressurs", "0..*"),
            SKOLEAR("no.fint.model.utdanning.kodeverk.Skolear", "0..*");
	
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


}
