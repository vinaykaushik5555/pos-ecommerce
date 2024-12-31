package com.ps.productservice.event.strategy;

import com.ps.productservice.event.ProductUpdatedEvent;
import com.ps.productservice.mapper.ProductMapper;
import com.ps.productservice.model.Product;
import com.ps.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("productUpdatedEventStrategy")
public class ProductUpdatedEventStrategy implements ProductEventStrategy<ProductUpdatedEvent> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void handle(ProductUpdatedEvent event) {
        Product product = productRepository.findById(event.getUpdateProductRequest().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productMapper.updateEntity(event.getUpdateProductRequest(), product);
        productRepository.save(product);
    }
}