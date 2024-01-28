package com.bake.BakeFLowBackend.service;

import com.bake.BakeFLowBackend.entity.Venta;

import java.util.Date;
import java.util.List;

public interface VentaService {


    Long crearVenta(Venta venta);

    Venta obtenerVenta(Long id);

    List<Venta> obtenerVentas();

    List<Venta> obtenerVentas(Date fechaInicio, Date fechaFin);
}
