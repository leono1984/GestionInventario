package com.techsolutions.sistemainterno.service;

import org.springframework.stereotype.Service;
import com.techsolutions.sistemainterno.dto.BonoDetalle;
import java.util.ArrayList;
import java.util.List;

@Service
public class BonoService {

    private List<BonoDetalle> historial = new ArrayList<>();

    public double calcularBono(double salarioMensual, int evaluacion) {
        if (salarioMensual <= 0) {
            throw new IllegalArgumentException("El salario debe ser mayor a 0");
        }
        if (evaluacion < 1 || evaluacion > 5) {
            throw new IllegalArgumentException("La evaluación debe estar entre 1 y 5");
        }

        double salarioAnual = salarioMensual * 12;
        double porcentaje;

        switch (evaluacion) {
            case 5: porcentaje = 0.20; break;
            case 4: porcentaje = 0.10; break;
            case 3: porcentaje = 0.05; break;
            default: porcentaje = 0.0; break;
        }

        double bono = salarioAnual * porcentaje;
        historial.add(new BonoDetalle(salarioMensual, evaluacion, bono));
        return bono;
    }

    public List<BonoDetalle> obtenerHistorial() {
        return historial;
    }
}
