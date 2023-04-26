package dev.dberenguer.dapr.order.configuration;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
public class OrderDaprConfiguration {

    @Value("${dberenguer.dapr.pub-sub.name}")
    private String daprPubSubName;

    @Value("${dberenguer.dapr.pub-sub.topic.name}")
    private String daprPubSubTopicName;

    @Value("${dberenguer.dapr.secret-store.name}")
    private String secretStoreName;

    @Bean
    public String daprPubSubNameBean() {
        return this.daprPubSubName;
    }

    @Bean
    public String daprPubSubTopicNameBean() {
        return this.daprPubSubTopicName;
    }

    @Bean
    public DaprClient orderDaprClient() {
        return new DaprClientBuilder().build();
    }

    @Bean
    public String secretDaprClient(final DaprClient daprClient) {
        final Map<String, String> secret = daprClient.getSecret(this.secretStoreName, "mySecret").block();
        log.info("Fetched Secret: {}", secret);
        return secret.toString();
    }

}
