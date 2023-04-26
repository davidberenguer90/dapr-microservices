package dev.dberenguer.dapr.checkout.configuration;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutDaprConfiguration {

    @Bean
    public DaprClient checkoutDaprClient() {
        return new DaprClientBuilder().build();
    }

}
