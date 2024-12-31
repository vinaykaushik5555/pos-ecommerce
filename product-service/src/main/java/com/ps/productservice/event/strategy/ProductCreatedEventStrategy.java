package com.ps.productservice.event.strategy;

import com.ps.productservice.event.ProductCreatedEvent;
import com.ps.productservice.mapper.ProductMapper;
import com.ps.productservice.model.Product;
import com.ps.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("productCreatedEventStrategy")
public class ProductCreatedEventStrategy implements ProductEventStrategy<ProductCreatedEvent> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void handle(ProductCreatedEvent event) {
        Product product = productMapper.toEntity(event.getCreateProductRequest());
        productRepository.save(product);
    }
}