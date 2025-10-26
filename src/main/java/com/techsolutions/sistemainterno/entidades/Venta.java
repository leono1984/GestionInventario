package com.techsolutions.sistemainterno.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "venta") // ✅ asegúrate de que coincide con tu tabla
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //ESTA ES UNA CLASE - ENTIDAD
    private LocalDate fecha = LocalDate.now(); // ✅ evita nulls

    private double total = 0.0; // ✅ valor inicial

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // ✅ previene lazy errors
    private List<DetalleVentaEntidad> detalles;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    //comentario
    public List<DetalleVentaEntidad> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVentaEntidad> detalles) { this.detalles = detalles; }
}
