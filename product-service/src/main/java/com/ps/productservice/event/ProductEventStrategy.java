package com.ps.productservice.event;


public interface ProductEventStrategy<T extends ProductEvent> {
    void handle(T event);
}