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
            @Mapping(source = "categoriaId", target = "categoria.id")
    })
    Producto toProducto(ProductoDTO productoDTO);

    @InheritInverseConfiguration
    @Mapping(source = "movimientos", target = "existencias", qualifiedByName ="CalcularExistencias")
    @Mapping(source="id", target="id")
    ProductoDTO toProductoDTO(Producto producto);

    List<ProductoDTO> toProductoDTOs(List<Producto> productos);


    @Named("CalcularExistencias")
    default int calcularExistencias(List<Movimiento> movimientos) {
        AtomicReference<Integer> existenciasCalculadas = new AtomicReference<>(0);
        if(movimientos != null){
            movimientos.forEach(movimiento -> {
                if (movimiento.getTipoMovimiento().esSuma()) {
                    existenciasCalculadas.updateAndGet(v -> v + movimiento.getCantidad());
                } else {
                    existenciasCalculadas.updateAndGet(v -> v - movimiento.getCantidad());
                }
            });
        }
        return existenciasCalculadas.get();
    }
}
