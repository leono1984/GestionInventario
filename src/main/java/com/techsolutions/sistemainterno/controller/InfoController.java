package com.techsolutions.sistemainterno.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping
    public Map<String, Object> getInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("empresa", "TechSolutions S.A.");
        response.put("servicios", Arrays.asList("RRHH", "Ventas"));
        response.put("version", "1.0.0");
        return response;
    }
}
