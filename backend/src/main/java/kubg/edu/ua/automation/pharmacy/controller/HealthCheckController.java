package kubg.edu.ua.automation.pharmacy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health-check")
public class HealthCheckController {

    @GetMapping
    public boolean check() {
        return true;
    }
}
