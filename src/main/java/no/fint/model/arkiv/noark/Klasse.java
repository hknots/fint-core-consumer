package no.fint.model.arkiv.noark;

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
import no.fint.model.arkiv.noark.Skjerming;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Klasse  implements FintComplexDatatypeObject {
    @Getter
    public enum Relasjonsnavn {
            KLASSIFIKASJONSSYSTEM("no.fint.model.arkiv.noark.Klassifikasjonssystem", "1");
	
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


    @NotBlank
    private String klasseId;
    private Integer rekkefolge;
    private @Valid Skjerming skjerming;
    @NotBlank
    private String tittel;
}
