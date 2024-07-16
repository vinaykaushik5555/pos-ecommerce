package com.ps.productservice.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreateProductRequest {
    private String sku;
    private String name;
    private String description;
    private Double price;
    private String categoryId; // Ensure this field exists
    private InventoryDTO inventory;
    private List<VariantDTO> variants;
    private List<ImageDTO> images;
    private Map<String, String> additionalParams;
}