package com.example.smartqueue.controller;
import com.example.smartqueue.service.QueueService;
import com.example.smartqueue.model.TokenStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    private final QueueService queueService;
    public HomeController(QueueService queueService) { this.queueService = queueService; }
    @GetMapping("/") public String home(Model model) {
        model.addAttribute("waitingCount", queueService.count(TokenStatus.WAITING));
        model.addAttribute("servingCount", queueService.count(TokenStatus.SERVING));
        return "index";
    }
}
