package com.ps.userservice.service;

// AddressService.java

import com.ps.userservice.dto.AddressRequest;
import com.ps.userservice.dto.AddressResponse;
import com.ps.userservice.entity.Address;
import com.ps.userservice.entity.User;
import com.ps.userservice.mapper.AddressMapper;
import com.ps.userservice.repository.AddressRepository;
import com.ps.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Transactional
    public AddressResponse addUserAddress(Long userId, AddressRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressMapper.toAddress(request);
        address.setUser(user);
        address.setCreatedAt(LocalDateTime.now());
        address.setUpdatedAt(LocalDateTime.now());

        address = addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    public List<AddressResponse> getUserAddresses(Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream().map(addressMapper::toAddressResponse).collect(Collectors.toList());
    }

    @Transactional
    public AddressResponse updateUserAddress(Long userId, Long addressId, AddressRequest request) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("Address does not belong to user");
        }

        addressMapper.updateAddressFromRequest(request, address);
        address.setUpdatedAt(LocalDateTime.now());

        address = addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    @Transactional
    public void deleteUserAddress(Long userId, Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("Address does not belong to user");
        }

        addressRepository.delete(address);
    }
}