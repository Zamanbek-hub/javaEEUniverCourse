package com.example.demo.services.impl;

import com.example.demo.entities.Roles;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Roles getRole(Long id){
        return roleRepository.getOne(id);
    }

    @Override
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Roles getRolesById(Long id) {
        return roleRepository.getOne(id);
    }

    public Roles getRolesByName(String name){
        return roleRepository.getRolesByName(name);
    }
}
