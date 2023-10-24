package no.fintlabs.model;

import no.fint.model.FintObject;
import no.fint.model.felles.kompleksedatatyper.Identifikator;

import java.util.Map;

public interface FintMainObject extends FintObject {

    Map<String, Identifikator> getIdentifikators();

}
