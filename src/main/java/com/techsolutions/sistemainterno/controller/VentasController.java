package com.techsolutions.sistemainterno.controller;

import com.techsolutions.sistemainterno.dto.VentaRequest;
import com.techsolutions.sistemainterno.entidades.DetalleVentaEntidad;
import com.techsolutions.sistemainterno.entidades.Venta;
import com.techsolutions.sistemainterno.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    private VentaService ventaService;

    // Endpoint para calcular el detalle de una orden (sin guardar)
    @GetMapping("/orden")
    public ResponseEntity<?> calcularOrden(
            @RequestParam double precioUnitario,
            @RequestParam int cantidad,
            @RequestParam int stock,
            @RequestParam double descuento) {

        try {
            return ResponseEntity.ok(ventaService.calcularDetalle(precioUnitario, cantidad, stock, descuento));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // ✅ Nuevo: registrar una venta completa
    @PostMapping("/nueva")
    public ResponseEntity<?> registrarVenta(@RequestBody VentaRequest ventaRequest) {
        try {
            Venta ventaGuardada = ventaService.registrarVenta(ventaRequest.getDetalles());
            return ResponseEntity.ok(ventaGuardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    // ✅ Nuevo: listar todas las ventas
    @GetMapping("/todas")
    public ResponseEntity<?> listarVentas() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    // ✅ Nuevo: buscar venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVenta(@PathVariable Long id) {
        return ventaService.buscarVenta(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Nuevo: eliminar venta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id) {
        try {
            ventaService.eliminarVenta(id);
            return ResponseEntity.ok("Venta eliminada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

