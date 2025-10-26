package com.techsolutions.sistemainterno.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import com.techsolutions.sistemainterno.entidades.DetalleVentaEntidad;

public interface DetalleVentaRepositorio extends JpaRepository<DetalleVentaEntidad, Long> {}
