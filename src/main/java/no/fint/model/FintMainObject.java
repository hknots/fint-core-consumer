package no.fint.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FintMainObject extends FintObject {

    Map<String, FintIdentifikator> getIdentifikators();

    default List<FintRelation> getRelations() {
        return new ArrayList<>();
    }

}
