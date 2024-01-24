package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.converter.ProductoConverter;
import com.bake.BakeFLowBackend.dto.ProductoDTO;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import com.bake.BakeFLowBackend.entity.TipoMovimiento;
import com.bake.BakeFLowBackend.service.ProductoService;
import com.bake.BakeFLowBackend.util.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoConverter productoConverter;

    @Override
    public void crearProducto() {
    }
}
