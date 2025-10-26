package com.techsolutions.sistemainterno.dto;

public class DetalleVenta {
    private double subtotal;
    private double descuento;
    private double total;

    public DetalleVenta(double subtotal, double descuento, double total) {
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
    }

    public double getSubtotal() { return subtotal; }
    public double getDescuento() { return descuento; }
    public double getTotal() { return total; }
}
