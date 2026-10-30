package com.example.smartqueue.repository;
import com.example.smartqueue.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    List<ServiceType> findByActiveTrueOrderByNameAsc();
    Optional<ServiceType> findByCodeIgnoreCase(String code);
}
