package no.fint.model.administrasjon.personal;

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
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.felles.kompleksedatatyper.Periode;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Fravar  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            FRAVARSGRUNN("no.fint.model.administrasjon.kodeverk.Fravarsgrunn", "0..1"),
            FRAVARSTYPE("no.fint.model.administrasjon.kodeverk.Fravarstype", "1"),
            ARBEIDSFORHOLD("no.fint.model.administrasjon.personal.Arbeidsforhold", "1..*"),
            FORTSETTELSE("no.fint.model.administrasjon.personal.Fravar", "0..1"),
            GODKJENNER("no.fint.model.administrasjon.personal.Personalressurs", "0..1"),
            FORTSETTER("no.fint.model.administrasjon.personal.Fravar", "0..1");
	
        private final String typeName;
        private final String multiplicity;

        private Relasjonsnavn(String typeName, String multiplicity) {
            this.typeName = typeName;
            this.multiplicity = multiplicity;
        }
    }


	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
		identifikators.put("kildesystemId", this.kildesystemId);
		identifikators.put("systemId", this.systemId);
    
    	return identifikators;
	}


    private @Valid Date godkjent;
    private @Valid Identifikator kildesystemId;
    @NotNull
    private @Valid Periode periode;
    @NotNull
    private Long prosent;
    private @Valid Identifikator systemId;
}
