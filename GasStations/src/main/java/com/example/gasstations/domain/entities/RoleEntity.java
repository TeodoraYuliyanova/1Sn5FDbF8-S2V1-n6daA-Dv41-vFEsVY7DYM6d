package com.example.gasstations.domain.entities;

import com.example.gasstations.domain.entities.enums.RoleNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleNameEnum role;
    public RoleEntity(){
    }

    public Long getId() {
        return id;
    }

    public RoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public RoleEntity setRole(RoleNameEnum role) {
        this.role = role;
        return this;
    }

}
