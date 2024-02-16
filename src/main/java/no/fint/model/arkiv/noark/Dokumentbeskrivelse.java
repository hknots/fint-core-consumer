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
import no.fint.model.FintComplexDatatypeObject;
import no.fint.model.FintIdentifikator;
import no.fint.model.arkiv.noark.Dokumentobjekt;
import java.util.Date;
import no.fint.model.arkiv.noark.Part;
import no.fint.model.arkiv.noark.Skjerming;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Dokumentbeskrivelse  implements FintComplexDatatypeObject {
    @Getter
    public enum Relasjonsnavn {
            DOKUMENTSTATUS("no.fint.model.arkiv.kodeverk.DokumentStatus", "1"),
            DOKUMENTTYPE("no.fint.model.arkiv.kodeverk.DokumentType", "1"),
            TILKNYTTETREGISTRERINGSOM("no.fint.model.arkiv.kodeverk.TilknyttetRegistreringSom", "1..*"),
            TILKNYTTETAV("no.fint.model.arkiv.noark.Arkivressurs", "1"),
            OPPRETTETAV("no.fint.model.arkiv.noark.Arkivressurs", "1");
	
        private final String typeName;
        private final String multiplicity;

        private Relasjonsnavn(String typeName, String multiplicity) {
            this.typeName = typeName;
            this.multiplicity = multiplicity;
        }
    }


	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
    
    	return identifikators;
	}


    private String beskrivelse;
    private Long dokumentnummer;
    private List<@Valid Dokumentobjekt> dokumentobjekt;
    private List<String> forfatter;
    private @Valid Date opprettetDato;
    private List<@Valid Part> part;
    private List<String> referanseArkivdel;
    private @Valid Skjerming skjerming;
    private @Valid Date tilknyttetDato;
    @NotBlank
    private String tittel;
}