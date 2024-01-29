package com.bake.BakeFLowBackend.dto.response;

import com.bake.BakeFLowBackend.entity.Movimiento;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class InformeVentasResponse {


    Map<String, ProductoVendidoResponse > productosVendidos;

    Integer totalProductosVendidos;

    Double totalIngresos;


}
