package com.example.smartqueue;

import com.example.smartqueue.model.*;
import com.example.smartqueue.repository.CustomerRepository;
import com.example.smartqueue.repository.ServiceTypeRepository;
import com.example.smartqueue.repository.QueueTokenRepository;
import com.example.smartqueue.service.WaitingTimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WaitingTimeServiceTest {
    @Autowired CustomerRepository customers;
    @Autowired ServiceTypeRepository services;
    @Autowired QueueTokenRepository tokens;
    @Autowired WaitingTimeService waitingTime;

    @Test void firstWaitingTokenHasFirstPositionAndNoEarlierWait() {
        Customer customer = new Customer(); customer.setName("Test"); customer = customers.save(customer);
        ServiceType service = new ServiceType(); service.setName("Test Service"); service.setCode("TS" + Math.abs(System.nanoTime()) % 1000); service.setAverageMinutes(12); service = services.save(service);
        QueueToken token = new QueueToken(); token.setCustomer(customer); token.setServiceType(service); token.setPriority(Priority.REGULAR); token.setStatus(TokenStatus.WAITING); token = tokens.saveAndFlush(token); token.setTokenNumber("REG-" + service.getCode() + "-" + token.getId()); token = tokens.save(token);
        assertEquals(1, waitingTime.getPosition(token));
        assertEquals(0, waitingTime.getEstimatedMinutes(token));
    }
}
