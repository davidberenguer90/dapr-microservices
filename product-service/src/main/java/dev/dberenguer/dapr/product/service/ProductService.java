package dev.dberenguer.dapr.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dberenguer.dapr.product.dto.ProductDto;
import dev.dberenguer.dapr.product.model.Product;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private static final String CACHE_NAME_PRODUCT_ALL = "product:all";

    private final DaprClient daprClient;
    private final ObjectMapper objectMapper;
    private final ConversionService conversionService;

    @Value("${dberenguer.dapr.state-store.name}")
    private String daprStateStoreName;

    @PostConstruct
    public void init(){
        this.daprClient.deleteState(this.daprStateStoreName, CACHE_NAME_PRODUCT_ALL);

        final List<Product> products = List.of(
                Product.builder().id(UUID.randomUUID()).code("1").name("computer").price(999.99).build(),
                Product.builder().id(UUID.randomUUID()).code("2").name("mobile").price(499.90).build(),
                Product.builder().id(UUID.randomUUID()).code("3").name("keyboard").price(49.95).build()
        );

        this.daprClient.saveState(this.daprStateStoreName, CACHE_NAME_PRODUCT_ALL, products).block();
        log.info("Saved state products: {}", products);
    }

    public List<ProductDto> findAll() {
        final State<List> stateProducts = this.daprClient.getState(this.daprStateStoreName, CACHE_NAME_PRODUCT_ALL, List.class).block();
        final List<Product> products = stateProducts.getValue().stream()
                .map(stateProduct -> this.objectMapper.convertValue(stateProduct, Product.class))
                .toList();
        log.info("Getting state products: {}", products);

        return products.stream()
                .map(product -> this.conversionService.convert(product, ProductDto.class))
                .toList();
    }

}
