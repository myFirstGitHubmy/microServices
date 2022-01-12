package com.services;

import com.domain.Role;
import com.repositories.RoleRepo;
import com.shared.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    private final DataUtil dataUtil = new DataUtil();

    public Role create(String name){
        return roleRepo.save(new Role(name, dataUtil.currentDate()));
    }

    public Role save(Role role){
        return roleRepo.save(role);
    }

    public Role getRoleById(Long id){
        return roleRepo.getRoleById(id);
    }

    public List<Role> getAll(){
        return roleRepo.findAll();
    }
}
