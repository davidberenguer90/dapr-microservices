package dev.dberenguer.dapr.order.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class OrderItemDto {
    ProductDto product;
    Integer quantity;
}