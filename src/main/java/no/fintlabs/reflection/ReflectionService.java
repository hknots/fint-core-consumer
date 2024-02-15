package no.fintlabs.reflection;

import no.fintlabs.config.ConsumerConfig;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;


@Service
public class ReflectionService {

    private final ConsumerConfig props;

    public ReflectionService(ConsumerConfig props) {
        this.props = props;
        Reflections reflection = new Reflections(getFullPackageName());
    }

    private String getFullPackageName() {
        return String.format("no.fint.model.%s.%s", props.getDomainName(), props.getPackageName());
    }

}
