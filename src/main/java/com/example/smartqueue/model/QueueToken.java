package com.example.smartqueue.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "queue_tokens", uniqueConstraints = @UniqueConstraint(columnNames = "tokenNumber"))
public class QueueToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String tokenNumber;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ServiceType serviceType;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Priority priority;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private TokenStatus status = TokenStatus.WAITING;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate public void updateTimestamp() { updatedAt = LocalDateTime.now(); }
    public Long getId() { return id; }
    public String getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public ServiceType getServiceType() { return serviceType; }
    public void setServiceType(ServiceType serviceType) { this.serviceType = serviceType; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public TokenStatus getStatus() { return status; }
    public void setStatus(TokenStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
