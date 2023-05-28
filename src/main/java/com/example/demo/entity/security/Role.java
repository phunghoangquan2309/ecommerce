package com.example.demo.entity.security;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@Builder
public class Role extends BaseEntity<Long> {

    public static enum ROLE{
        CUSTOMER, SELLER, ADMIN
    }

    public static Role newInstance(Role.ROLE role) {
        return new Role(role);
    }

    public static Role newInstanceRoleCustomer() {
        return new Role(ROLE.CUSTOMER);
    }

    public static Role newInstanceRoleSeller() {
        return new Role(ROLE.SELLER);
    }

    public static Role newInstanceRoleAdmin() {
        return new Role(ROLE.ADMIN);
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ROLE name;

    public Role(ROLE name) {
        this.name = name;
    }
}

