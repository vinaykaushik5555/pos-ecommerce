package com.ps.productservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InventoryDTO {
    private int quantity;
    private Date updatedAt;
}