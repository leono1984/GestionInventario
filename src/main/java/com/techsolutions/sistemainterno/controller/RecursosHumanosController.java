package com.techsolutions.sistemainterno.controller;

import com.techsolutions.sistemainterno.service.BonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rh")
public class RecursosHumanosController {

    @Autowired
    private BonoService bonoService;

    @GetMapping("/bono")
    public ResponseEntity<?> calcularBono(
            @RequestParam double salarioMensual,
            @RequestParam int evaluacion) {

        try {
            double bono = bonoService.calcularBono(salarioMensual, evaluacion);
            return ResponseEntity.ok("El bono anual es: " + bono);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/historial")
    public ResponseEntity<?> obtenerHistorial() {
        return ResponseEntity.ok(bonoService.obtenerHistorial());
    }
}

