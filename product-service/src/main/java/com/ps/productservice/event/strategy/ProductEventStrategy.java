package com.ps.productservice.event.strategy;

import com.ps.productservice.event.ProductEvent;

public interface ProductEventStrategy<T extends ProductEvent> {
    void handle(T event);
}