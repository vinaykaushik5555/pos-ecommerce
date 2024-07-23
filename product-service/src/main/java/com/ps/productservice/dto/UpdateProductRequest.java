package com.ps.productservice.dto;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UpdateProductRequest {
    private String id; // Ensure this field exists for updating
    private String name;
    private String sku;
    private String description;
    private Double price;
    private String categoryId; // Ensure this field exists
    private InventoryDTO inventory;
    private List<VariantDTO> variants;
    private List<ImageDTO> images;
    private Map<String, String> additionalParams;
}