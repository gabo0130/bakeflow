package com.bake.BakeFLowBackend.dto;


import lombok.Data;

@Data
public class ProductoDTO {

    String nombre;

    String descripcion;

    Integer precio;

    int existencias;

    Long categoriaId;

    Integer cantidadInicial;

    Integer costoUnitario;

    Integer costoTotal;




}
