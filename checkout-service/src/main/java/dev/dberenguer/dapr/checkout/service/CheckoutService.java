package dev.dberenguer.dapr.checkout.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dberenguer.dapr.checkout.dto.OrderDto;
import dev.dberenguer.dapr.checkout.dto.OrderItemDto;
import dev.dberenguer.dapr.checkout.dto.ProductDto;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.HttpExtension;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
public class CheckoutService {

    private static final String API_PATH_PRODUCTS = "api/v1/products";

    private final DaprClient daprClient;
    private final ObjectMapper objectMapper;

    @Value("${dberenguer.dapr.services.product}")
    private String daprServiceProduct;
    @Value("${dberenguer.dapr.pub-sub.name}")
    private String daprPubSubName;
    @Value("${dberenguer.dapr.pub-sub.topic.name}")
    private String daprPubSubTopicName;

    public void checkout(){
        final List products = this.daprClient.invokeMethod(this.daprServiceProduct, API_PATH_PRODUCTS, null, HttpExtension.GET, List.class).block();
        final List<ProductDto> productsDto = products.stream()
                .map(product -> this.objectMapper.convertValue(product, ProductDto.class))
                .toList();
        log.info("Getting products from invoke service products: {}", productsDto);

        final OrderDto orderDto = this.createOrder(productsDto);
        this.daprClient.publishEvent(this.daprPubSubName, this.daprPubSubTopicName, orderDto).block();
        log.info("Published order: {}", orderDto);
    }

    private OrderDto createOrder(final List<ProductDto> productsDto) {
        final Random random = new Random();

        final ProductDto productDto1 = productsDto.get(random.ints(0,3).findFirst().getAsInt());
        final Integer quantity1 = random.ints(1,10).findFirst().getAsInt();
        final ProductDto productDto2 = productsDto.get(random.ints(0,3).findFirst().getAsInt());
        final Integer quantity2 = random.ints(1,10).findFirst().getAsInt();

        return OrderDto.builder()
                .id(UUID.randomUUID())
                .orderItems(
                        List.of(OrderItemDto.builder().product(productDto1).quantity(quantity1).build(),
                                OrderItemDto.builder().product(productDto2).quantity(quantity2).build())
                )
                .build();
    }

}
