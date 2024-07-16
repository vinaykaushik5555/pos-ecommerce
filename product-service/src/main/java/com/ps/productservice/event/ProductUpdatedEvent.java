package com.ps.productservice.event;

import com.ps.productservice.dto.UpdateProductRequest;
import com.ps.productservice.enums.EventType;
import lombok.Data;

@Data
public class ProductUpdatedEvent extends ProductEvent {
    private UpdateProductRequest updateProductRequest;

    public ProductUpdatedEvent() {
        this.setEventType(EventType.PRODUCT_UPDATED);
    }
}