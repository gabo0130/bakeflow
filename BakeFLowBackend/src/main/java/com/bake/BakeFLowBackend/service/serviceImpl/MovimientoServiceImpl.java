package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.repository.MovimientoRepository;
import com.bake.BakeFLowBackend.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Override
    public void registrarMovimiento(Movimiento movimiento) {
        movimientoRepository.save(movimiento);
    }
}
