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
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Periode  implements FintComplexDatatypeObject {
	
	@JsonIgnore
	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
    
    	return identifikators;
	}


    private String beskrivelse;
    private @Valid Date slutt;
    @NotNull
    private @Valid Date start;
}
