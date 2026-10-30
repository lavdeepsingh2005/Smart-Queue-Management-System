package com.example.smartqueue.service;

import com.example.smartqueue.model.*;
import com.example.smartqueue.repository.QueueTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {
    private final QueueTokenRepository tokenRepository;
    public TokenService(QueueTokenRepository tokenRepository) { this.tokenRepository = tokenRepository; }

    @Transactional
    public QueueToken createToken(Customer customer, ServiceType serviceType, Priority priority) {
        QueueToken token = new QueueToken();
        token.setCustomer(customer);
        token.setServiceType(serviceType);
        token.setPriority(priority);
        token.setStatus(TokenStatus.WAITING);
        token = tokenRepository.saveAndFlush(token);
        String number = priority.getCode() + "-" + serviceType.getCode() + "-" + String.format("%03d", token.getId());
        token.setTokenNumber(number);
        return tokenRepository.save(token);
    }
}
