package com.example.willys.controller;

import com.example.willys.dto.CreateCustomerDto;
import com.example.willys.dto.UpdateCustomerDto;
import com.example.willys.entity.Customer;
import com.example.willys.entity.Product;
import com.example.willys.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*    - - - - - - - - - - - - - - - - - - -   */

    @PostMapping("/customer/create")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto) {
        Customer customer = customerService.createCustomer(createCustomerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer/search/{search}")
    public ResponseEntity<List<Customer>> searchCustomer(@PathVariable String search){
        List<Customer> customers = customerService.searchByName(search);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/customer/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @Valid @RequestBody UpdateCustomerDto updateCustomerDto) {
        Customer customer = customerService.updateCustomer(id, updateCustomerDto);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String id) {
         customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
