package com.example.demo.repository;

import com.example.demo.entity.ecommerce.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
