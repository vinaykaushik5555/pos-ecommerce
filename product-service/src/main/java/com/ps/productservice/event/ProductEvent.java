package com.ps.productservice.event;

import com.ps.productservice.enums.EventType;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class ProductEvent implements Serializable {
    private String correlationId;
    private EventType eventType;
}