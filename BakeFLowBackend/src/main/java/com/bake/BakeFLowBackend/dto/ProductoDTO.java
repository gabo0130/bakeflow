package com.bake.BakeFLowBackend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDTO {

    Long id;

    String nombre;

    String descripcion;

    Double precio;

    int existencias;

    Long categoriaId;

    Integer cantidadInicial;

    Double costoUnitario;

    Double costoTotal;

}
