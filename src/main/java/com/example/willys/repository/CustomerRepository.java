package com.example.willys.repository;

import com.example.willys.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

List<Customer> findByNameContainingIgnoreCase(String name);

}
