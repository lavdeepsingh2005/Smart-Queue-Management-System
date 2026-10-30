package com.example.smartqueue.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "service_types", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class ServiceType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 80)
    private String name;
    @NotBlank @Size(min = 2, max = 6)
    private String code;
    @Min(1)
    private int averageMinutes = 10;
    private boolean active = true;

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code == null ? null : code.toUpperCase().trim(); }
    public int getAverageMinutes() { return averageMinutes; }
    public void setAverageMinutes(int averageMinutes) { this.averageMinutes = averageMinutes; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
