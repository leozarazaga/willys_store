package com.example.willys.repository;

import com.example.willys.entity.Customer;
import com.example.willys.entity.Order;
import com.example.willys.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByCustomerIdAndProductId(UUID customerId, UUID productId);
    Optional<Order> findByCustomerAndProduct(Customer customer, Product product);
    List<Order> findByCustomerId(UUID customerId);
    List<Order> findByProductId(UUID productId);

    @Transactional
    void deleteByCustomerIdAndProductId(UUID customerId, UUID productId);
}
