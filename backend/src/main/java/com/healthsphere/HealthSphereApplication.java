package com.healthsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:8082")
public class HealthSphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthSphereApplication.class, args);
    }

    @GetMapping("/api/health")
    public String health() {
        return "HealthSphere backend is running";
    }

    @GetMapping("/api/patients")
    public String patients() {
        return "HealthSphere patient service is running";
    }
}