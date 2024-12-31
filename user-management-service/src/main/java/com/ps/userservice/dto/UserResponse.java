package com.ps.userservice.dto;
// UserResponse.java

import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String profilePic;
    private Set<String> roles;

}
