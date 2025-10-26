package com.techsolutions.sistemainterno.service;

import com.techsolutions.sistemainterno.dto.DetalleVenta;
import com.techsolutions.sistemainterno.entidades.DetalleVentaEntidad;
import com.techsolutions.sistemainterno.entidades.Venta;
import com.techsolutions.sistemainterno.repositorio.DetalleVentaRepositorio;
import com.techsolutions.sistemainterno.repositorio.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepositorio ventaRepositorio;

    @Autowired
    private DetalleVentaRepositorio detalleVentaRepositorio;

    /**
     * Calcula el detalle de una venta sin guardarla todavía
     */
    public DetalleVenta calcularDetalle(double precioUnitario, int cantidad, int stock, double descuento) {
        if (precioUnitario <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a 0");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (cantidad > stock) {
            throw new IllegalArgumentException("Stock insuficiente, disponible: " + stock);
        }

        double subtotal = precioUnitario * cantidad;
        double total = subtotal - descuento;

        return new DetalleVenta(subtotal, descuento, total);
    }

    /**
     * Guarda una venta completa con sus detalles
     */
    public Venta registrarVenta(List<DetalleVentaEntidad> detalles) {
        if (detalles == null || detalles.isEmpty()) {
            throw new IllegalArgumentException("Debe registrar al menos un detalle de venta.");
        }

        // Calculamos el total sumando todos los detalles
        double total = detalles.stream()
                .mapToDouble(d -> d.getPrecioUnitario() * d.getCantidad())
                .sum();

        // Creamos la venta principal
        Venta venta = new Venta();
        venta.setFecha(LocalDate.now());
        venta.setTotal(total);

        // Asociamos los detalles con la venta
        for (DetalleVentaEntidad det : detalles) {
            det.setVenta(venta);
        }

        venta.setDetalles(detalles);

        // Guardamos en base de datos (en cascada se guardan los detalles)
        return ventaRepositorio.save(venta);
    }

    /**
     * Lista todas las ventas
     */
    public List<Venta> listarVentas() {
        return ventaRepositorio.findAll();
    }

    /**
     * Busca una venta específica por ID
     */
    public Optional<Venta> buscarVenta(Long id) {
        return ventaRepositorio.findById(id);
    }

    /**
     * Elimina una venta
     */
    public void eliminarVenta(Long id) {
        if (!ventaRepositorio.existsById(id)) {
            throw new IllegalArgumentException("No se encontró la venta con ID: " + id);
        }
        ventaRepositorio.deleteById(id);
    }
}
