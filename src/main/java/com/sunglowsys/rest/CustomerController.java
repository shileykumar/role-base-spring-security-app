package com.sunglowsys.rest;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * create customer
     * @param customer
     * @return
     */
    @PostMapping("/create/customers")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer result = customerService.save(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    /**
     * update customer
     * @param customer
     * @return
     */
    @PutMapping("/update/customers")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer result = customerService.update(customer);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * get customers
     * @return
     */
    @GetMapping("/get/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> result = customerService.findAll();
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * get customer by id
     * @param id
     * @return
     */
    @GetMapping("/get/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer result = customerService.findById(id);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * delete customer
     * @param id
     * @return
     */
    @DeleteMapping("/delete/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
