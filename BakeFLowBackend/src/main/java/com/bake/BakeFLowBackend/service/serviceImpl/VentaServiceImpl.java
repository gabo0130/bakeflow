package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.dto.BetweenDate;
import com.bake.BakeFLowBackend.entity.Venta;
import com.bake.BakeFLowBackend.repository.VentaRepository;
import com.bake.BakeFLowBackend.service.VentaService;
import com.bake.BakeFLowBackend.util.ValiDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public Long crearVenta(Venta venta) {
        System.out.println(venta.toString());
        return ventaRepository.save(venta).getId();
    }

    @Override
    public Venta obtenerVenta(Long id) {
        return ventaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro la venta")
        );
    }

    @Override
    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public List<Venta> obtenerVentas(Date fechaInicio, Date fechaFin) {
        BetweenDate betweenDate = ValiDate.valideFechas(fechaInicio, fechaFin);
        return ventaRepository.findAllByFechaBetween(betweenDate.getFechaInicio(), betweenDate.getFechaFin());
    }
}
