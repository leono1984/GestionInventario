package com.techsolutions.sistemainterno.controller;

import com.techsolutions.sistemainterno.entidades.DetalleVentaEntidad;
import com.techsolutions.sistemainterno.entidades.Venta;
import com.techsolutions.sistemainterno.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/ventas")
public class VentaVistaControlador {

    @Autowired
    private VentaService ventaService;

    // 🟩 Listar todas las ventas
    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "ventas"; // archivo ventas.html
    }

    // 🟩 Mostrar formulario de nueva venta
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        Venta venta = new Venta();
        venta.setDetalles(new ArrayList<>());
        venta.getDetalles().add(new DetalleVentaEntidad()); // inicializamos un detalle
        model.addAttribute("venta", venta);
        return "venta-form";
    }

    // 🟩 Guardar una venta con sus detalles
    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta) {
        // Validar que tenga detalles antes de guardar
        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar al menos un detalle de venta.");
        }

        // Aquí pasamos los detalles al service (como espera tu método)
        ventaService.registrarVenta(venta.getDetalles());

        return "redirect:/ventas";
    }

    // 🟩 Eliminar una venta
    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return "redirect:/ventas";
    }
}
