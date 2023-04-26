package dev.dberenguer.dapr.order.controller;

import dev.dberenguer.dapr.order.dto.OrderDto;
import dev.dberenguer.dapr.order.service.OrderService;
import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @Topic(name = "#{@daprPubSubTopicNameBean}", pubsubName = "#{@daprPubSubNameBean}")
    @PostMapping(path = "/orders", consumes = MediaType.ALL_VALUE)
    public Mono<ResponseEntity> getOrders(@RequestBody(required = false) final CloudEvent<OrderDto> cloudEvent) {
        return this.orderService.processOrders(cloudEvent);
    }

}
