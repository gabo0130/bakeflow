package com.bake.BakeFLowBackend.converter;


import com.bake.BakeFLowBackend.dto.ProductoDTO;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import org.mapstruct.*;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoConverter {


    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "categoria", target = "categoria"),
            @Mapping(source = "movimientos", target = "existencias", qualifiedByName ="CalcularExistencias")
    })
    ProductoDTO toProductoDTO(Producto producto);


    @Named("CalcularExistencias")
    default int calcularExistencias(Set<Movimiento> movimientos) {
        AtomicReference<Integer> existenciasCalculadas = new AtomicReference<>(0);
        movimientos.forEach(movimiento -> {
            if (movimiento.getTipoMovimiento().esSuma()) {
                existenciasCalculadas.updateAndGet(v -> v + movimiento.getCantidad());
            } else {
                existenciasCalculadas.updateAndGet(v -> v - movimiento.getCantidad());
            }
        });
        return existenciasCalculadas.get();
    }
}
