package com.ps.productservice.event.consumer;

import com.ps.productservice.event.ProductEvent;
import com.ps.productservice.event.strategy.ProductEventContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class ProductEventConsumer {

    @Autowired
    private ProductEventContext productEventContext;

    @KafkaListener(topics = "${kafka.topic.product}", groupId = "product-group")
    public void consumeProductEvent(ProductEvent event) {
        productEventContext.handleEvent(event);
    }
}
