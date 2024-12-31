package com.ps.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AssignRolesRequest {
    @NotNull
    private List<String> roles;

}