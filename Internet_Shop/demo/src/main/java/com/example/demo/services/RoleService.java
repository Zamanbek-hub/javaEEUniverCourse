package com.example.demo.services;

import com.example.demo.entities.Roles;

import java.util.List;

public interface RoleService {
    Roles getRole(Long id);
    Roles getRolesByName(String name);
    Roles getRolesById(Long id);
    List<Roles> getAllRoles();

}
