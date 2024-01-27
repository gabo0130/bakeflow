package com.bake.BakeFLowBackend.service;

import com.bake.BakeFLowBackend.dto.request.MovimientoRequest;
import com.bake.BakeFLowBackend.dto.request.MovimientosRequest;
import com.bake.BakeFLowBackend.dto.response.InformeVentasResponse;
import com.bake.BakeFLowBackend.dto.response.InformeVentasXCostosResponse;
import com.bake.BakeFLowBackend.entity.Movimiento;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovimientoService {
    void registrarMovimiento(MovimientoRequest movimiento);

    void registrarMovimiento(Movimiento movimiento);

    void registrarMovimiento(MovimientosRequest movimiento);


    List<Movimiento> informeMovimientosEntreFechas(@Param("productoId") Long productoId,
                                                   @Param("esVenta") Boolean esVenta,
                                                   @Param("fechaInicio") Date fechaInicio,
                                                   @Param("fechaFin") Date fechaFin);

    InformeVentasResponse informeVentasTotales(Long productoId, Date fechaInicio, Date fechaFin);

    InformeVentasXCostosResponse informeVentasTotalesXcosto(Long productoId);
}
