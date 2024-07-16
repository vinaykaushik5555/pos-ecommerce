package com.ps.productservice.event;

import com.ps.productservice.enums.EventType;
import lombok.Data;

@Data
public abstract class ProductEvent {
    private String correlationId;
    private EventType eventType;
}