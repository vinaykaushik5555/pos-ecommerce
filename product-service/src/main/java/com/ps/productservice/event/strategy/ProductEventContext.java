package com.ps.productservice.event.strategy;

import com.ps.productservice.enums.EventType;
import com.ps.productservice.event.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ProductEventContext {

    private final Map<EventType, ProductEventStrategy<? extends ProductEvent>> strategies;

    @Autowired
    public ProductEventContext(@Qualifier("productCreatedEventStrategy") ProductCreatedEventStrategy productCreatedEventStrategy,
                               @Qualifier("productUpdatedEventStrategy") ProductUpdatedEventStrategy productUpdatedEventStrategy) {
        strategies = new EnumMap<>(EventType.class);
        strategies.put(EventType.PRODUCT_CREATED, productCreatedEventStrategy);
        strategies.put(EventType.PRODUCT_UPDATED, productUpdatedEventStrategy);
    }

    public void handleEvent(ProductEvent event) {
        ProductEventStrategy<ProductEvent> strategy = (ProductEventStrategy<ProductEvent>) strategies.get(event.getEventType());
        strategy.handle(event);
    }
}