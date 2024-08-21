package com.example.microservices.dtos;

import lombok.Data;

import java.util.List;
@Data
public class UserDto {
    private String name;
    private String email;
    private List<RoleDto> roles;
}
