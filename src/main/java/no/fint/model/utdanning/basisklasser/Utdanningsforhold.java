package no.fint.model.utdanning.basisklasser;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.validation.Valid;
import javax.validation.constraints.*;
import no.fint.model.FintMultiplicity;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.FintAbstractObject;
import no.fint.model.FintIdentifikator;
import no.fint.model.FintRelation;
import no.fint.model.felles.kompleksedatatyper.Identifikator;

import static no.fint.model.FintMultiplicity.ONE_TO_ONE;
import static no.fint.model.FintMultiplicity.ONE_TO_MANY;
import static no.fint.model.FintMultiplicity.NONE_TO_ONE;
import static no.fint.model.FintMultiplicity.NONE_TO_MANY;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Utdanningsforhold  implements FintAbstractObject {
    @Getter
    public enum Relasjonsnavn implements FintRelation {
            MEDLEMSKAP("medlemskap", "no.fint.model.utdanning.elev.Medlemskap", NONE_TO_MANY);
	
		private final String name;
        private final String packageName;
        private final FintMultiplicity multiplicity;

        private Relasjonsnavn(String name, String packageName, FintMultiplicity multiplicity) {
			this.name = name;
            this.packageName = packageName;
            this.multiplicity = multiplicity;
        }
    }

	public Map<String, FintIdentifikator> getIdentifikators() {
    	Map<String, FintIdentifikator> identifikators = new HashMap<>();
		identifikators.put("systemId", this.systemId);
    
    	return identifikators;
	}
	@JsonIgnore
	private final List<FintRelation> relations = new ArrayList<>(List.of(Relasjonsnavn.values()));
    @NotBlank
    private String beskrivelse;
    @NotNull
    private @Valid Identifikator systemId;
}
