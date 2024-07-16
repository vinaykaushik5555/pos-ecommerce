package com.ps.productservice.service;

import com.ps.productservice.config.KafkaProperties;
import com.ps.productservice.dto.CreateProductRequest;
import com.ps.productservice.dto.ProductResponse;
import com.ps.productservice.dto.UpdateProductRequest;
import com.ps.productservice.event.ProductCreatedEvent;
import com.ps.productservice.event.ProductUpdatedEvent;
import com.ps.productservice.mapper.ProductMapper;
import com.ps.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaProperties kafkaProperties;

    public Page<ProductResponse> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    public ProductResponse getProduct(String productId) {
        return productRepository.findById(productId)
                .map(productMapper::toDto)
                .orElse(null);
    }

    public String createProduct(CreateProductRequest request) {
        String correlationId = UUID.randomUUID().toString();
        ProductCreatedEvent event = productMapper.toEvent(request, correlationId);
        kafkaTemplate.send(kafkaProperties.getProduct(), event);
        return correlationId;
    }

    public String updateProduct(String productId, UpdateProductRequest request) {
        return productRepository.findById(productId)
                .map(existingProduct -> {
                    productMapper.updateEntity(request, existingProduct);
                    existingProduct.setUpdatedAt(new Date());
                    productRepository.save(existingProduct);
                    String correlationId = UUID.randomUUID().toString();
                    ProductUpdatedEvent event = productMapper.toEvent(request, correlationId);
                    kafkaTemplate.send(kafkaProperties.getProduct(), event);
                    return correlationId;
                })
                .orElse(null);
    }

    public void deleteProduct(String productId) {
        productRepository.findById(productId)
                .ifPresent(productRepository::delete);
    }
}