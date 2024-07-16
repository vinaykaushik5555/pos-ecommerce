package com.ps.productservice.model;

import lombok.Data;
import java.util.Date;

@Data
public class Inventory {
    private int quantity;
    private Date updatedAt;
}
