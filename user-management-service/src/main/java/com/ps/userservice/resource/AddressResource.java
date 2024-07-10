package com.ps.userservice.resource;

import com.ps.userservice.dto.*;
import com.ps.userservice.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/addresses")
@Api(tags = "Addresses", description = "Operations related to user addresses")
public class AddressResource {

    @Autowired
    private AddressService addressService;

    @PostMapping
    @ApiOperation(value = "Add a new address for a user", response = AddressResponse.class)
    public ResponseEntity<AddressResponse> addUserAddress(
            @ApiParam(value = "ID of the user to add address to", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "Address details", required = true)
            @Valid @RequestBody AddressRequest request) {
        AddressResponse addressResponse = addressService.addUserAddress(userId, request);
        return ResponseEntity.status(201).body(addressResponse);
    }

    @GetMapping
    @ApiOperation(value = "Get addresses for a user", response = AddressResponse.class, responseContainer = "List")
    public ResponseEntity<List<AddressResponse>> getUserAddresses(
            @ApiParam(value = "ID of the user to retrieve addresses for", required = true)
            @PathVariable Long userId) {
        List<AddressResponse> addressResponses = addressService.getUserAddresses(userId);
        return ResponseEntity.ok(addressResponses);
    }

    @PutMapping
    @ApiOperation(value = "Update an address for a user", response = AddressResponse.class)
    public ResponseEntity<AddressResponse> updateUserAddress(
            @ApiParam(value = "ID of the user to update address for", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "ID of the address to update", required = true)
            @RequestParam Long addressId,
            @ApiParam(value = "Updated address details", required = true)
            @Valid @RequestBody AddressRequest request) {
        AddressResponse addressResponse = addressService.updateUserAddress(userId, addressId, request);
        return ResponseEntity.ok(addressResponse);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete an address for a user")
    public ResponseEntity<Void> deleteUserAddress(
            @ApiParam(value = "ID of the user to delete address for", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "ID of the address to delete", required = true)
            @RequestParam Long addressId) {
        addressService.deleteUserAddress(userId, addressId);
        return ResponseEntity.noContent().build();
    }
}
