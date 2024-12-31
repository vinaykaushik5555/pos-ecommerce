package com.ps.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressRequest {
    @NotBlank
    private String addressLine1;
    private String addressLine2;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String country;

    // Getters and Setters
}
