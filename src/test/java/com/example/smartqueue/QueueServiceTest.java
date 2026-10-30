package com.example.smartqueue;

import com.example.smartqueue.model.*;
import com.example.smartqueue.repository.CustomerRepository;
import com.example.smartqueue.repository.ServiceTypeRepository;
import com.example.smartqueue.repository.QueueTokenRepository;
import com.example.smartqueue.service.QueueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QueueServiceTest {
    @Autowired CustomerRepository customerRepository;
    @Autowired ServiceTypeRepository serviceRepository;
    @Autowired QueueTokenRepository tokenRepository;
    @Autowired QueueService queueService;

    @Test void higherPriorityCustomerIsAheadOfRegularCustomer() {
        QueueToken regular = createToken(Priority.REGULAR, "REG");
        QueueToken vip = createToken(Priority.EMERGENCY, "VIP");
        assertEquals(vip.getId(), queueService.getWaitingQueue().get(0).getId());
        assertEquals(regular.getId(), queueService.getWaitingQueue().get(1).getId());
    }
    @Test void samePriorityCustomersKeepArrivalOrder() {
        QueueToken first = createToken(Priority.REGULAR, "REG");
        QueueToken second = createToken(Priority.REGULAR, "REG");
        assertEquals(first.getId(), queueService.getWaitingQueue().get(0).getId());
        assertEquals(second.getId(), queueService.getWaitingQueue().get(1).getId());
    }
    @Test void tokenCannotBeCompletedBeforeItIsServing() {
        QueueToken waiting = createToken(Priority.REGULAR, "REG");
        assertThrows(IllegalStateException.class, () -> queueService.changeStatus(waiting.getId(), TokenStatus.COMPLETED));
    }
    private QueueToken createToken(Priority priority, String code) {
        Customer customer = new Customer(); customer.setName("Test Customer"); customer = customerRepository.save(customer);
        ServiceType service = new ServiceType(); service.setName("Test Service"); service.setCode(code + String.valueOf(Math.abs(System.nanoTime()) % 1000)); service.setAverageMinutes(10); service = serviceRepository.save(service);
        QueueToken token = new QueueToken(); token.setCustomer(customer); token.setServiceType(service); token.setPriority(priority); token.setStatus(TokenStatus.WAITING);
        token = tokenRepository.saveAndFlush(token); token.setTokenNumber(priority.getCode() + "-" + service.getCode() + "-" + token.getId()); return tokenRepository.save(token);
    }
}