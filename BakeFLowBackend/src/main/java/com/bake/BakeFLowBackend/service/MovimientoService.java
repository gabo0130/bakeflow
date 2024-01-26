package com.bake.BakeFLowBackend.service;

import com.bake.BakeFLowBackend.dto.request.MovimientoRequest;
import com.bake.BakeFLowBackend.entity.Movimiento;

public interface MovimientoService {
    void registrarMovimiento(MovimientoRequest movimiento);

    void registrarMovimiento(Movimiento movimiento);
}
