package com.example.gasstations.services;

import com.example.gasstations.domain.entities.RoleEntity;
import com.example.gasstations.domain.entities.enums.RoleNameEnum;
import com.example.gasstations.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Order(0)
public class RoleService implements DataBaseInitService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.dbInit();
    }

    @Override
    public void dbInit() {
        if (isDbInit()){
            List<RoleEntity> roles = new ArrayList<>();

            roles.add(new RoleEntity().setRole(RoleNameEnum.USER));
            roles.add(new RoleEntity().setRole(RoleNameEnum.ADMIN));

            this.roleRepository.saveAllAndFlush(roles);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.roleRepository.count() == 0;
    }

}
