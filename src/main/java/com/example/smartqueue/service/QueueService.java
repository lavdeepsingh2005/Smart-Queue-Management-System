package com.example.smartqueue.service;

import com.example.smartqueue.model.*;
import com.example.smartqueue.repository.QueueTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class QueueService {
    private final QueueTokenRepository tokenRepository;
    private final WaitingTimeService waitingTimeService;
    public QueueService(QueueTokenRepository tokenRepository, WaitingTimeService waitingTimeService) {
        this.tokenRepository = tokenRepository; this.waitingTimeService = waitingTimeService;
    }
    public List<QueueToken> getWaitingQueue() { return waitingTimeService.getWaitingQueue(); }
    public List<QueueToken> getAllTokens() { return tokenRepository.findAll().stream().sorted((a,b) -> b.getCreatedAt().compareTo(a.getCreatedAt())).toList(); }
    public QueueToken findByNumber(String number) { return tokenRepository.findByTokenNumberIgnoreCase(number.trim()).orElseThrow(() -> new IllegalArgumentException("Token was not found.")); }
    @Transactional
    public QueueToken callNext() {
        List<QueueToken> queue = getWaitingQueue();
        if (queue.isEmpty()) throw new IllegalStateException("There are no waiting customers.");
        if (!tokenRepository.findByStatusIn(List.of(TokenStatus.SERVING)).isEmpty()) throw new IllegalStateException("Complete the currently serving token before calling another customer.");
        QueueToken next = queue.get(0); next.setStatus(TokenStatus.SERVING); return tokenRepository.save(next);
    }
    @Transactional
    public QueueToken changeStatus(Long id, TokenStatus newStatus) {
        QueueToken token = tokenRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Token was not found."));
        TokenStatus old = token.getStatus();
        boolean allowed = (old == TokenStatus.WAITING && List.of(TokenStatus.SKIPPED, TokenStatus.CANCELLED).contains(newStatus))
                || (old == TokenStatus.SERVING && List.of(TokenStatus.COMPLETED, TokenStatus.SKIPPED).contains(newStatus));
        if (!allowed) throw new IllegalStateException("That status change is not allowed.");
        token.setStatus(newStatus); return tokenRepository.save(token);
    }
    public int getPosition(QueueToken token) { return waitingTimeService.getPosition(token); }
    public int getEstimatedMinutes(QueueToken token) { return waitingTimeService.getEstimatedMinutes(token); }
    public long count(TokenStatus status) { return tokenRepository.countByStatus(status); }
}
