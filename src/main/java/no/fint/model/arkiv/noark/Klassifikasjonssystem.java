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
import java.util.Date;
import no.fint.model.arkiv.noark.Klasse;
import no.fint.model.felles.kompleksedatatyper.Identifikator;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Klassifikasjonssystem  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            KLASSIFIKASJONSTYPE("no.fint.model.arkiv.kodeverk.Klassifikasjonstype", "0..1"),
            ARKIVDEL("no.fint.model.arkiv.noark.Arkivdel", "1..*");
	
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


    private String avsluttetAv;
    private @Valid Date avsluttetDato;
    private String beskrivelse;
    @NotEmpty
    private List<@Valid Klasse> klasse;
    @NotBlank
    private String opprettetAv;
    @NotNull
    private @Valid Date opprettetDato;
    @NotNull
    private @Valid Identifikator systemId;
    @NotBlank
    private String tittel;
}
