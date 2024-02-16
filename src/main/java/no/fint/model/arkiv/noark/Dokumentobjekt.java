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

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Dokumentobjekt  implements FintComplexDatatypeObject {
    @Getter
    public enum Relasjonsnavn {
            FILFORMAT("no.fint.model.arkiv.kodeverk.Format", "0..1"),
            VARIANTFORMAT("no.fint.model.arkiv.kodeverk.Variantformat", "1"),
            OPPRETTETAV("no.fint.model.arkiv.noark.Arkivressurs", "1"),
            REFERANSEDOKUMENTFIL("no.fint.model.arkiv.noark.Dokumentfil", "0..1");
	
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


    private String filstorrelse;
    @Deprecated
    private String format;
    private String formatDetaljer;
    private String sjekksum;
    private String sjekksumAlgoritme;
    private Long versjonsnummer;
}
