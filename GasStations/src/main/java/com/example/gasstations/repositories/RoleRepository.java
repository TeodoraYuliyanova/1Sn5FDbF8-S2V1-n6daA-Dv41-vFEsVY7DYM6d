package com.example.gasstations.repositories;

import com.example.gasstations.domain.entities.RoleEntity;
import com.example.gasstations.domain.entities.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findByRole(RoleNameEnum role);
}
