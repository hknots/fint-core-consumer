package no.fint.model.felles.kompleksedatatyper;

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
import no.fint.model.FintComplexDatatypeObject;
import no.fint.model.FintIdentifikator;
import no.fint.model.felles.kompleksedatatyper.Adresse;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Matrikkelnummer  implements FintComplexDatatypeObject {
    @Getter
    public enum Relasjonsnavn {
            KOMMUNENUMMER("no.fint.model.felles.kodeverk.Kommune", "0..1");
	
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
    
    	return identifikators;
	}


    private @Valid Adresse adresse;
    private String bruksnummer;
    private String festenummer;
    private String gardsnummer;
    private String seksjonsnummer;
}
