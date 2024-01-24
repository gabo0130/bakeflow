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
        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripcion 1");
        producto.setPrecio(100.0);
        Movimiento movimiento = new Movimiento();
        movimiento.setCantidad(10);
        TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setNombre("Entrada");
        tipoMovimiento.setOperacion(Operation.SUMA);
        tipoMovimiento.setEsVenta(false);
        movimiento.setTipoMovimiento(tipoMovimiento);
        Set<Movimiento> movimientos = new HashSet<>();
        movimientos.add(movimiento);
        producto.setMovimientos(movimientos);


        ProductoDTO productoDTO = productoConverter.toProductoDTO(producto);

        System.out.println(productoDTO);


        producto.setMovimientos(null);
        System.out.println(producto);
    }
}
