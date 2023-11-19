package com.example.willys.service;

import com.example.willys.entity.Customer;
import com.example.willys.entity.Order;
import com.example.willys.entity.Product;
import com.example.willys.exception.CustomerNotFoundException;
import com.example.willys.exception.OrderAlreadyExistsException;
import com.example.willys.exception.OrderNotFoundException;
import com.example.willys.exception.ProductNotFoundException;
import com.example.willys.repository.CustomerRepository;
import com.example.willys.repository.OrderRepository;
import com.example.willys.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    /*    - - - - - - - - - - - - -    */

    public Order createOrder(Order order, String customerId, String productId) {
        UUID customerUUID = UUID.fromString(customerId);
        UUID productUUID = UUID.fromString(productId);

        Customer customer = customerRepository.findById(customerUUID).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Product product = productRepository.findById(productUUID).orElseThrow(() -> new ProductNotFoundException(productId));

        Optional<Order> existingOrder = orderRepository.findByCustomerAndProduct(customer, product);
        if(existingOrder.isPresent()){
            throw new OrderAlreadyExistsException(customerId, productId);
        }

        order.setCustomer(customer);
        order.setProduct(product);
        return orderRepository.save(order);
    }

    public Order getOrder(String customerId, String productId) {
        UUID customerUUID = UUID.fromString(customerId);
        UUID productUUID = UUID.fromString(productId);

        Optional<Order> order = orderRepository.findByCustomerIdAndProductId(customerUUID, productUUID);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new OrderNotFoundException(customerId, productId);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(int quantity, String customerId, String productId) {
        UUID customerUUID = UUID.fromString(customerId);
        UUID productUUID = UUID.fromString(productId);

        Optional<Order> order = orderRepository.findByCustomerIdAndProductId(customerUUID, productUUID);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setQuantity(quantity);
            return orderRepository.save(existingOrder);
        } else {
            throw new OrderNotFoundException(customerId, productId);
        }
    }

    public void deleteOrderByCustomerAndProduct(String customerId, String productId) {
        UUID customerUUID = UUID.fromString(customerId);
        UUID productUUID = UUID.fromString(productId);
        orderRepository.deleteByCustomerIdAndProductId(customerUUID, productUUID);
    }

    public void deleteOrder(String orderId) {
        UUID orderUUID = UUID.fromString(orderId);
        orderRepository.deleteById(orderUUID);
    }


    public List<Order> getCustomerOrders(String id){
        UUID uuid = UUID.fromString(id);
        return orderRepository.findByCustomerId(uuid);
    }

    public List<Order> getProductOrders(String id){
        UUID uuid = UUID.fromString(id);
        return orderRepository.findByProductId(uuid);
    }

}
