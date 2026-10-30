package com.example.smartqueue.service;

import com.example.smartqueue.model.Customer;
import com.example.smartqueue.model.Priority;
import com.example.smartqueue.model.QueueToken;
import com.example.smartqueue.model.ServiceType;
import com.example.smartqueue.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final CustomerRepository customerRepository;
    private final TokenService tokenService;

    public RegistrationService(CustomerRepository customerRepository, TokenService tokenService) {
        this.customerRepository = customerRepository;
        this.tokenService = tokenService;
    }

    @Transactional
    public QueueToken register(Customer customer, ServiceType service, Priority priority) {
        Customer savedCustomer = customerRepository.save(customer);
        return tokenService.createToken(savedCustomer, service, priority);
    }
}
