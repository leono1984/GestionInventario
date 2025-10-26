package com.techsolutions.sistemainterno.dto;

import com.techsolutions.sistemainterno.entidades.DetalleVentaEntidad;
import java.util.List;

public class VentaRequest {
    private List<DetalleVentaEntidad> detalles;

    public List<DetalleVentaEntidad> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaEntidad> detalles) {
        this.detalles = detalles;
    }
}
