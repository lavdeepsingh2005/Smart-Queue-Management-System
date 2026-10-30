package com.example.smartqueue.service;

import com.example.smartqueue.model.*;
import com.example.smartqueue.repository.QueueTokenRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class WaitingTimeService {
    private final QueueTokenRepository tokenRepository;
    public WaitingTimeService(QueueTokenRepository tokenRepository) { this.tokenRepository = tokenRepository; }

    public List<QueueToken> getWaitingQueue() {
        return tokenRepository.findByStatusOrderByCreatedAtAsc(TokenStatus.WAITING).stream()
                .sorted(Comparator.comparingInt((QueueToken t) -> t.getPriority().getRank())
                        .thenComparing(QueueToken::getCreatedAt).thenComparing(QueueToken::getId))
                .toList();
    }

    public int getPosition(QueueToken token) {
        if (token.getStatus() == TokenStatus.SERVING) return 0;
        if (token.getStatus() != TokenStatus.WAITING) return -1;
        List<QueueToken> queue = getWaitingQueue();
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).getId().equals(token.getId())) return i + 1;
        }
        return -1;
    }

    public int getEstimatedMinutes(QueueToken token) {
        int position = getPosition(token);
        if (position <= 0) return 0;
        List<QueueToken> queue = getWaitingQueue();
        int total = 0;
        for (int i = 0; i < queue.size(); i++) {
            QueueToken current = queue.get(i);
            if (current.getId().equals(token.getId())) break;
            total += current.getServiceType().getAverageMinutes();
        }
        List<QueueToken> serving = tokenRepository.findByStatusIn(List.of(TokenStatus.SERVING));
        for (QueueToken current : serving) total += Math.max(1, current.getServiceType().getAverageMinutes() / 2);
        return total;
    }
}
