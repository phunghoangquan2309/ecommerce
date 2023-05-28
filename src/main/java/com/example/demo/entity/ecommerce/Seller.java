package com.example.demo.entity.ecommerce;

import com.example.demo.entity.base.BaseEntity;
import com.example.demo.entity.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table
@AllArgsConstructor
public class Seller extends BaseEntity<Long> {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
