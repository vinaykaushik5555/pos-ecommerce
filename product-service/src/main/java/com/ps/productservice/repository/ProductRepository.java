package com.ps.productservice.repository;


import com.ps.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findBySku(String sku);

}
