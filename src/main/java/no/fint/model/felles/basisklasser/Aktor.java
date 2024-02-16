package no.fint.model.felles.basisklasser;

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
import no.fint.model.felles.kompleksedatatyper.Kontaktinformasjon;
import no.fint.model.felles.kompleksedatatyper.Adresse;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Aktor  implements FintAbstractObject {

	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
    
    	return identifikators;
	}


    private @Valid Kontaktinformasjon kontaktinformasjon;
    private @Valid Adresse postadresse;
}
