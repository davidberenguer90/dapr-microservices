package dev.dberenguer.dapr.product.configuration;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDaprConfiguration {

    @Bean
    public DaprClient productDaprClient() {
        return new DaprClientBuilder().build();
    }

}
