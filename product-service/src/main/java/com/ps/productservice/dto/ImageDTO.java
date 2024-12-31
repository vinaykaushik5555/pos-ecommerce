package com.ps.productservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ImageDTO {
    private String url;
    private Date addedAt;
}