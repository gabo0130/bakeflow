package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.entity.TipoMovimiento;
import com.bake.BakeFLowBackend.repository.TipoMovimientoRepository;
import com.bake.BakeFLowBackend.service.TipoMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMovimientoServiceImpl implements TipoMovimientoService {

    @Autowired
    private TipoMovimientoRepository tipoMovimientoRepository;

    @Override
    public List<TipoMovimiento> obtenerTipoMovimientos() {
        return tipoMovimientoRepository.findAll();
    }

    public TipoMovimiento obtenerTipoMovimiento(Long id) {
        return tipoMovimientoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro el tipo de movimiento")
        );
    }
}
