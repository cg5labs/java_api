package com.cg5labs.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg5labs.api.*;

@RestController
public class ApiController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}