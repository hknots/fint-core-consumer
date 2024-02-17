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
import no.fint.model.FintAbstractObject;
import no.fint.model.FintIdentifikator;
import java.util.Date;
import no.fint.model.arkiv.noark.Klasse;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.arkiv.noark.Merknad;
import no.fint.model.arkiv.noark.Part;
import no.fint.model.arkiv.noark.Skjerming;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Mappe  implements FintAbstractObject {
    @Getter
    public enum Relasjonsnavn {
            ARKIVDEL("no.fint.model.arkiv.noark.Arkivdel", "0..1"),
            AVSLUTTETAV("no.fint.model.arkiv.noark.Arkivressurs", "0..1"),
            OPPRETTETAV("no.fint.model.arkiv.noark.Arkivressurs", "1");
	
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
		identifikators.put("mappeId", this.mappeId);
		identifikators.put("systemId", this.systemId);
    
    	return identifikators;
	}


    private @Valid Date avsluttetDato;
    private String beskrivelse;
    private List<@Valid Klasse> klasse;
    private @Valid Identifikator mappeId;
    private List<@Valid Merknad> merknad;
    private List<String> noekkelord;
    private String offentligTittel;
    private @Valid Date opprettetDato;
    private List<@Valid Part> part;
    private @Valid Skjerming skjerming;
    private @Valid Identifikator systemId;
    private String tittel;
}
