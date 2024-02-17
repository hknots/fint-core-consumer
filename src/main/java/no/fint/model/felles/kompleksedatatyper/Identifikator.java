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
import no.fint.model.felles.kompleksedatatyper.Periode;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Identifikator  implements FintComplexDatatypeObject, FintIdentifikator {
	
	@JsonIgnore
	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
    
    	return identifikators;
	}


    private @Valid Periode gyldighetsperiode;
    @NotBlank
    private String identifikatorverdi;
}
