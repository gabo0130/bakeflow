package com.bake.BakeFLowBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class InformeVentasXCostosResponse {


    Map<String, ProductoVendidoResponse > productosVendidos;

    Double totalVentas;

    Double totalCostos;

    Double totalGanancias;
}
