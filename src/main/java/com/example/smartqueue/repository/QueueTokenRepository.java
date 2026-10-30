package com.example.smartqueue.repository;
import com.example.smartqueue.model.QueueToken;
import com.example.smartqueue.model.TokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
public interface QueueTokenRepository extends JpaRepository<QueueToken, Long> {
    List<QueueToken> findByStatusIn(List<TokenStatus> statuses);
    List<QueueToken> findByStatusOrderByCreatedAtAsc(TokenStatus status);
    Optional<QueueToken> findByTokenNumberIgnoreCase(String tokenNumber);
    long countByStatus(TokenStatus status);
    long countByStatusAndCreatedAtBetween(TokenStatus status, LocalDateTime start, LocalDateTime end);
}
