package com.ps.productservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ProductResponse {
    private String id;
    private String sku;
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private InventoryDTO inventory;
    private List<VariantDTO> variants;
    private List<ImageDTO> images;
    private Date createdAt;
    private Date updatedAt;
    private Map<String, String> additionalParams;
}
