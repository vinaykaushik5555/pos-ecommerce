package com.ps.userservice.resource;

import com.ps.userservice.dto.*;
import com.ps.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@Api(tags = "Users", description = "Operations related to user management")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "Register a new user", response = UserResponse.class)
    public ResponseEntity<UserResponse> registerUser(
            @ApiParam(value = "User registration details", required = true)
            @Valid @RequestBody RegisterUserRequest request) {
        UserResponse userResponse = userService.registerUser(request);
        return ResponseEntity.status(201).body(userResponse);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Authenticate a user", response = LoginResponse.class)
    public ResponseEntity<LoginResponse> loginUser(
            @ApiParam(value = "User login details", required = true)
            @Valid @RequestBody LoginRequest request) {
        LoginResponse loginResponse = userService.loginUser(request);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get user profile", response = UserResponse.class)
    public ResponseEntity<UserResponse> getUserProfile(
            @ApiParam(value = "ID of the user to retrieve", required = true)
            @PathVariable Long userId) {
        UserResponse userResponse = userService.getUserProfile(userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String email) {
        UserResponse userResponse = userService.getUserProfile(email);
        return ResponseEntity.ok(userResponse);
    }


    @PutMapping("/{userId}")
    @ApiOperation(value = "Update user profile", response = UserResponse.class)
    public ResponseEntity<UserResponse> updateUserProfile(
            @ApiParam(value = "ID of the user to update", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "Updated user profile details", required = true)
            @Valid @RequestBody UpdateUserProfileRequest request) {
        UserResponse userResponse = userService.updateUserProfile(userId, request);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{userId}/roles")
    @ApiOperation(value = "Assign roles to a user", response = UserResponse.class)
    public ResponseEntity<UserResponse> assignUserRoles(
            @ApiParam(value = "ID of the user to assign roles to", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "User roles details", required = true)
            @Valid @RequestBody AssignRolesRequest request) {
        UserResponse userResponse = userService.assignUserRoles(userId, request);
        return ResponseEntity.ok(userResponse);
    }
}
