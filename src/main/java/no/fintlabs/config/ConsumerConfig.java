package no.fintlabs.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
public class ConsumerConfig {

    @Value("${fint.consumer.domain}")
    private String domainName;

    @Value("${fint.consumer.package}")
    private String packageName;

}
