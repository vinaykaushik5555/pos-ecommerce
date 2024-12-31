package com.ps.userservice.mapper;

import com.ps.userservice.dto.RegisterUserRequest;
import com.ps.userservice.dto.UpdateUserProfileRequest;
import com.ps.userservice.dto.UserResponse;
import com.ps.userservice.entity.Role;
import com.ps.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToStrings")
    UserResponse toUserResponse(User user);

    User toUser(RegisterUserRequest request);

    @Mapping(target = "roles", ignore = true)
    User updateUserFromRequest(UpdateUserProfileRequest request, @MappingTarget User user);

    @Named("rolesToStrings")
    default Set<String> rolesToStrings(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    @Named("stringsToRoles")
    default Set<Role> stringsToRoles(Set<String> roleNames) {
        return roleNames.stream().map(roleName -> {
            Role role = new Role();
            role.setName(roleName);
            return role;
        }).collect(Collectors.toSet());
    }
}