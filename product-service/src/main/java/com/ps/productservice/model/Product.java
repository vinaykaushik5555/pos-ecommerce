package com.ps.productservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    @Indexed(unique = true)
    private String sku; // SKU field for unique product identification
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private Inventory inventory;
    private List<Variant> variants;
    private List<Image> images;
    private Date createdAt;
    private Date updatedAt;
    private Map<String, String> additionalParams;
}
