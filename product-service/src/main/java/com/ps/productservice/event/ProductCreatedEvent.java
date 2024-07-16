package com.ps.productservice.event;


import com.ps.productservice.dto.CreateProductRequest;
import com.ps.productservice.enums.EventType;
import lombok.Data;

@Data
public class ProductCreatedEvent extends ProductEvent {
    private CreateProductRequest createProductRequest;
    public ProductCreatedEvent() {
        this.setEventType(EventType.PRODUCT_CREATED);
    }
}