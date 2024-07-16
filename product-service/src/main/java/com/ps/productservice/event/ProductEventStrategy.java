package com.ps.productservice.event;


public interface ProductEventStrategy {
    void handle(ProductCreatedEvent event);
    void handle(ProductUpdatedEvent event);
}
