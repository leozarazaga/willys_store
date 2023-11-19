package com.example.willys.controller;

import com.example.willys.entity.Order;
import com.example.willys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /*    - - - - - - - - - - - - -    */

    @PostMapping("/order/customer/{customerId}/product/{productId}")
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @PathVariable String customerId, @PathVariable String productId) {
        Order result = orderService.createOrder(order, customerId, productId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/order/customer/{customerId}/product/{productId}")
    public ResponseEntity<Order> getOrder(@PathVariable String customerId, @PathVariable String productId) {
        Order order = orderService.getOrder(customerId, productId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/customer/{customerId}/product/{productId}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable String customerId, @PathVariable String productId) {
        Order updatedOrder = orderService.updateOrder(order.getQuantity(), customerId, productId);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/order/customer/{customerId}/product/{productId}")
    public ResponseEntity<HttpStatus> deleteOrderByCustomerAndProduct(@PathVariable String customerId, @PathVariable String productId) {
        orderService.deleteOrderByCustomerAndProduct(customerId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/order/delete/{orderId}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable String orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/order/customer/{customerId}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable String customerId){
        List<Order> order = orderService.getCustomerOrders(customerId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/product/{productId}")
    public ResponseEntity<List<Order>> getProductOrders(@PathVariable String productId){
        List<Order> order = orderService.getProductOrders(productId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
