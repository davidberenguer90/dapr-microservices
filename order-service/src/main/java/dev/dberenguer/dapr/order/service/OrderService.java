package dev.dberenguer.dapr.order.service;

import dev.dberenguer.dapr.order.dto.OrderDto;
import io.dapr.client.domain.CloudEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    public Mono<ResponseEntity> processOrders(final CloudEvent<OrderDto> cloudEvent) {
        return Mono.fromSupplier(() -> {
            log.info("Subscriber order received: {}", cloudEvent.getData());
            return ResponseEntity.ok("SUCCESS");
        });
    }

}
