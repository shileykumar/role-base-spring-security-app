package com.sunglowsys.service.impl;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.domain.Role;
import com.sunglowsys.domain.User;
import com.sunglowsys.repository.CustomerRepository;
import com.sunglowsys.repository.UserRepository;
import com.sunglowsys.service.CustomerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer save(Customer customer) {
        Customer result = customerRepository.save(customer);

        Role role = new Role();
        role.setId(3L);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        String password = passwordEncoder.encode(customer.getPassword());
        User user = new User(customer.getFirstName() + customer.getLastName(), customer.getEmail(), password, roles);
        userRepository.save(user);
        return result;
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
