package com.ps.productservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String sku;
    private String name;
    private String description;
    private Double price;
   // @DBRef
  //  @Indexed
  //  private Category category;
    private Inventory inventory;
    private List<Variant> variants;
    private List<Image> images;
    private Date createdAt;
    private Date updatedAt;
    private Map<String, String> additionalParams;
}
