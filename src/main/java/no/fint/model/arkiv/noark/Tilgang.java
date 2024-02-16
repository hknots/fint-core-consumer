package no.fint.model.arkiv.noark;

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
public class Tilgang  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            ROLLE("no.fint.model.arkiv.kodeverk.Rolle", "1"),
            ADMINISTRATIVENHET("no.fint.model.arkiv.noark.AdministrativEnhet", "0..1"),
            ARKIVDEL("no.fint.model.arkiv.noark.Arkivdel", "0..1"),
            ARKIVRESSURS("no.fint.model.arkiv.noark.Arkivressurs", "0..*");
	
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
    private @Valid Identifikator systemId;
    @NotBlank
    private String tittel;
}