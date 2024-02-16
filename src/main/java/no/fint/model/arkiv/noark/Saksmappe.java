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
import no.fint.model.FintAbstractObject;
import no.fint.model.FintIdentifikator;
import no.fint.model.arkiv.noark.Registrering;
import no.fint.model.arkiv.noark.Journalpost;
import java.util.Date;
import no.fint.model.arkiv.noark.Mappe;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public abstract class Saksmappe extends Mappe  implements FintAbstractObject {
    @Getter
    public enum Relasjonsnavn {
            SAKSMAPPETYPE("no.fint.model.arkiv.kodeverk.Saksmappetype", "0..1"),
            SAKSSTATUS("no.fint.model.arkiv.kodeverk.Saksstatus", "1"),
            JOURNALENHET("no.fint.model.arkiv.noark.AdministrativEnhet", "0..1"),
            ADMINISTRATIVENHET("no.fint.model.arkiv.noark.AdministrativEnhet", "1"),
            SAKSANSVARLIG("no.fint.model.arkiv.noark.Arkivressurs", "1");
	
        private final String typeName;
        private final String multiplicity;

        private Relasjonsnavn(String typeName, String multiplicity) {
            this.typeName = typeName;
            this.multiplicity = multiplicity;
        }
    }


	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
		identifikators.putAll(super.getIdentifikators());
    
    	return identifikators;
	}


    private List<@Valid Registrering> arkivnotat;
    private List<@Valid Journalpost> journalpost;
    private String saksaar;
    private @Valid Date saksdato;
    private String sakssekvensnummer;
    private @Valid Date utlaantDato;
}
