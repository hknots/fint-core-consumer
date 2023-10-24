package no.fintlabs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.fint.model.FintComplexDatatypeObject;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Fravarsprosent implements FintComplexDatatypeObject {
    @NotNull
    private Integer fravarstimer;
    @NotNull
    private Integer prosent;
    @NotNull
    private Integer undervisningstimer;
}
