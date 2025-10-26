package com.techsolutions.sistemainterno.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techsolutions.sistemainterno.entidades.Venta;

public interface VentaRepositorio extends JpaRepository<Venta, Long> {}
