package com.ps.productservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.productservice.model.Category;
import com.ps.productservice.model.Product;
import com.ps.productservice.repository.CategoryRepository;
import com.ps.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadProducts();
    }

    private void loadCategories() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Category>> typeReference = new TypeReference<List<Category>>() {};
        InputStream inputStream = new ClassPathResource("categories.json").getInputStream();
        List<Category> categories = mapper.readValue(inputStream, typeReference);
        categoryRepository.saveAll(categories);
    }

    private void loadProducts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {};
        InputStream inputStream = new ClassPathResource("products.json").getInputStream();
        List<Product> products = mapper.readValue(inputStream, typeReference);
        productRepository.saveAll(products);
    }
}
