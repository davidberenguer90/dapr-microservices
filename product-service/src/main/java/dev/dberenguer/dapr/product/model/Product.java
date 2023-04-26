package dev.dberenguer.dapr.product.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
public class Product {
    private UUID id;
    private String code;
    private String name;
    private Double price;
}