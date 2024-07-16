package com.ps.productservice.event;

import com.ps.productservice.mapper.ProductMapper;
import com.ps.productservice.model.Product;
import com.ps.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("productCreatedEventStrategy")
public class ProductCreatedEventStrategy implements ProductEventStrategy {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void handle(ProductCreatedEvent event) {
        Product product = productMapper.toEntity(event);
        productRepository.save(product);
    }

    @Override
    public void handle(ProductUpdatedEvent event) {
        // No operation for this strategy
    }
}
