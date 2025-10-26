package com.techsolutions.sistemainterno.dto;

public class BonoDetalle {
    private double salarioMensual;
    private int evaluacion;
    private double bono;

    public BonoDetalle(double salarioMensual, int evaluacion, double bono) {
        this.salarioMensual = salarioMensual;
        this.evaluacion = evaluacion;
        this.bono = bono;
    }

    public double getSalarioMensual() { return salarioMensual; }
    public int getEvaluacion() { return evaluacion; }
    public double getBono() { return bono; }
}
