package com.example.smartqueue.controller;

import com.example.smartqueue.model.*;
import com.example.smartqueue.repository.ServiceTypeRepository;
import com.example.smartqueue.service.QueueService;
import com.example.smartqueue.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    private final ServiceTypeRepository serviceRepository;
    private final RegistrationService registrationService;
    private final QueueService queueService;
    public CustomerController(ServiceTypeRepository serviceRepository, RegistrationService registrationService, QueueService queueService) {
        this.serviceRepository = serviceRepository; this.registrationService = registrationService; this.queueService = queueService;
    }
    @GetMapping("/register") public String registerForm(Model model) {
        if (!model.containsAttribute("customer")) model.addAttribute("customer", new Customer());
        model.addAttribute("services", serviceRepository.findByActiveTrueOrderByNameAsc()); model.addAttribute("priorities", Priority.values());
        return "register";
    }
    @PostMapping("/register") public String register(@Valid @ModelAttribute("customer") Customer customer, BindingResult result,
            @RequestParam Long serviceId, @RequestParam Priority priority, Model model) {
        ServiceType service = serviceRepository.findById(serviceId).filter(ServiceType::isActive).orElse(null);
        if (service == null) result.reject("service.invalid", "Please select an available service.");
        if (result.hasErrors()) { model.addAttribute("services", serviceRepository.findByActiveTrueOrderByNameAsc()); model.addAttribute("priorities", Priority.values()); return "register"; }
        QueueToken token = registrationService.register(customer, service, priority);
        return "redirect:/token/" + token.getTokenNumber();
    }
    @GetMapping("/token/{number}") public String confirmation(@PathVariable String number, Model model) {
        QueueToken token = queueService.findByNumber(number); model.addAttribute("token", token);
        model.addAttribute("position", queueService.getPosition(token)); model.addAttribute("estimatedMinutes", queueService.getEstimatedMinutes(token)); return "token-confirmation";
    }
    @GetMapping("/track") public String trackForm() { return "track-token"; }
    @PostMapping("/track") public String track(@RequestParam String tokenNumber, RedirectAttributes redirect) {
        try { return "redirect:/token/" + queueService.findByNumber(tokenNumber).getTokenNumber(); }
        catch (IllegalArgumentException ex) { redirect.addFlashAttribute("errorMessage", "We could not find that token. Check the number and try again."); return "redirect:/track"; }
    }
    @GetMapping("/display") public String display(Model model) {
        model.addAttribute("queue", queueService.getWaitingQueue()); model.addAttribute("serving", queueService.getAllTokens().stream().filter(t -> t.getStatus() == TokenStatus.SERVING).toList());
        return "display";
    }
}
