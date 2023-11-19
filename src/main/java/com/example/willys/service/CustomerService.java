package com.example.willys.service;

import com.example.willys.dto.CreateCustomerDto;
import com.example.willys.dto.UpdateCustomerDto;
import com.example.willys.entity.Customer;
import com.example.willys.exception.CustomerNameNotFoundException;
import com.example.willys.exception.CustomerNotFoundException;
import com.example.willys.exception.CustomerDoesNotExistException;
import com.example.willys.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /*    - - - - - - - - - - - - -    */

    public Customer createCustomer(CreateCustomerDto dto) {
        Customer customer = new Customer(dto.getName(), dto.getWillysPlus());
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        UUID uuid = UUID.fromString(id);
        return customerRepository.findById(uuid).orElseThrow(() -> new CustomerNotFoundException(id));
    }


    public List<Customer> searchByName(String search) {
        List<Customer> findByName = customerRepository.findByNameContainingIgnoreCase(search);

        if (findByName.isEmpty()) {
            throw new CustomerNameNotFoundException(search);
        }

        return findByName.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public Customer updateCustomer(String id, UpdateCustomerDto dto) {
        UUID uuid = UUID.fromString(id);

        Customer customer = customerRepository.findById(uuid).orElseThrow(() -> new CustomerNotFoundException(id));

        if (dto.getName().isPresent()) {
            customer.setName(dto.getName().get());
        }

        if (dto.getWillysPlus().isPresent()) {
            customer.setWillysPlus(dto.getWillysPlus().get());
        }
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        UUID uuid = UUID.fromString(id);

        Customer customer = customerRepository.findById(uuid).orElseThrow(() -> new CustomerDoesNotExistException(id));
        customerRepository.delete(customer);
    }

}
