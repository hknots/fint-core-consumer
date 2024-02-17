package no.fint.model.okonomi.faktura;

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
import no.fint.model.FintMainObject;
import no.fint.model.FintIdentifikator;
import no.fint.model.felles.kompleksedatatyper.Adresse;
import java.util.Date;
import no.fint.model.felles.kompleksedatatyper.Identifikator;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Faktura  implements FintMainObject {
    @Getter
    public enum Relasjonsnavn {
            FAKTURAGRUNNLAG("no.fint.model.okonomi.faktura.Fakturagrunnlag", "1");
	
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
		identifikators.put("fakturanummer", this.fakturanummer);
    
    	return identifikators;
	}


    private @Valid Adresse adresse;
    @NotNull
    private Long belop;
    private Boolean betalt;
    @NotNull
    private Date dato;
    @NotNull
    private @Valid Identifikator fakturanummer;
    private Boolean fakturert;
    @NotNull
    private Date forfallsdato;
    private Boolean kreditert;
    @NotBlank
    private String mottaker;
    private Long restbelop;
}
