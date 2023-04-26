package dev.dberenguer.dapr.product.converter;

import dev.dberenguer.dapr.product.dto.ProductDto;
import dev.dberenguer.dapr.product.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToDtoConverter implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(final Product product) {
        return ProductDto.builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

}
