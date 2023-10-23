package no.fintlabs.library.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ConsumerConfig {

    @Value(ConfigConstants.DOMAIN)
    private String domain;

    @Value(ConfigConstants.PACKAGE)
    private String packageName;

    @Value(ConfigConstants.ORG_ID)
    private String orgId;

}
