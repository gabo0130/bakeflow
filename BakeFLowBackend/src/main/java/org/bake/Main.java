package org.bake;

import com.bake.BakeFLowBackend.converter.ProductoConverter;
import com.bake.BakeFLowBackend.dto.ProductoDTO;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import com.bake.BakeFLowBackend.entity.TipoDocumento;
import com.bake.BakeFLowBackend.entity.TipoMovimiento;
import com.bake.BakeFLowBackend.util.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class Main {

    @Autowired
    ProductoConverter productoConverter;

    public static void main(String[] args) {
        System.out.println("Hello world!");


    }
}