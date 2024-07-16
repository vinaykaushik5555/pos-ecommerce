package com.ps.productservice.resource;

import com.ps.productservice.dto.CreateProductRequest;
import com.ps.productservice.dto.ProductResponse;
import com.ps.productservice.dto.UpdateProductRequest;
import com.ps.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductResponse> listProducts(@PageableDefault(size = 10) Pageable pageable) {
        return productService.listProducts(pageable);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String productId) {
        ProductResponse product = productService.getProduct(productId);
        return (product != null) ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request) {
        String correlationId = productService.createProduct(request);
        return ResponseEntity.ok(correlationId);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable String productId, @RequestBody UpdateProductRequest request) {
        String correlationId = productService.updateProduct(productId, request);
        return (correlationId != null) ? ResponseEntity.ok(correlationId) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}