package com.ps.userservice.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressResponse {
    private String id;
    private String userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}