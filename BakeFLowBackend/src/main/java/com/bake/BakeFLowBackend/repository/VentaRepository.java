package com.bake.BakeFLowBackend.repository;

import com.bake.BakeFLowBackend.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findAllByFechaBetween(Date fechaInicio, Date fechaFin);
}
