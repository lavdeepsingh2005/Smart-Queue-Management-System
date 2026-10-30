package com.example.smartqueue.config;

import com.example.smartqueue.model.ServiceType;
import com.example.smartqueue.repository.ServiceTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initializeServices(ServiceTypeRepository services) {
        return args -> {
            addService(services, "General Consultation", "GC", 15);
            addService(services, "Billing and Payments", "BP", 8);
            addService(services, "Document Verification", "DV", 10);
            addService(services, "Student Administration", "SA", 12);
            addService(services, "General Enquiry", "GE", 5);
        };
    }

    private void addService(ServiceTypeRepository repository, String name, String code, int minutes) {
        if (repository.findByCodeIgnoreCase(code).isEmpty()) {
            ServiceType service = new ServiceType();
            service.setName(name);
            service.setCode(code);
            service.setAverageMinutes(minutes);
            service.setActive(true);
            repository.save(service);
        }
    }
}
