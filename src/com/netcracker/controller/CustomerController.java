package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Customer;
import com.netcracker.model.Status;
import com.netcracker.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found for this id::" + id));

        return ResponseEntity.ok().body(customer);
    }


    @PostMapping
    public Status saveCustomer(@Valid @RequestBody Customer customer) {
        customerRepository.save(customer);
        Status status = new Status("The Customer was added successfully");
        return status;
    }


    @DeleteMapping
    public Status deleteAllCustomers()
            throws ResourceNotFoundException
    {
        customerRepository.deleteAll();
        Status status = new Status("The all Customers was deleted successfully");
        return status;
    }

    @DeleteMapping("/{id}")
    public Status deleteCustomer(@PathVariable(value="id") Integer id)
            throws ResourceNotFoundException
    {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer not found for this id::"+id));
        customerRepository.delete(customer);
        Status status = new Status("The Customer was deleted successfully");
        return status;
    }

}