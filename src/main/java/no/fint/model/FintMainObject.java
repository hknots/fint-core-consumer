package no.fint.model;

import java.util.Map;

public interface FintMainObject extends FintObject {

    Map<String, FintIdentifikator> getIdentifikators();

}
