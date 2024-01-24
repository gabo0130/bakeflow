package com.bake.BakeFLowBackend.dto;


import lombok.Data;

@Data
public class ProductoDTO {

    String nombre;

    String descripcion;

    Double precio;

    int existencias;

    CategoriaDTO categoria;


}
