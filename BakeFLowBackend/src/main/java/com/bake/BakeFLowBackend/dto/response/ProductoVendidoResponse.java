package com.bake.BakeFLowBackend.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoVendidoResponse {

    Integer cantidad;

    Integer cantidadPerdida;

    Double precioVentaPromedio;

    Double totalVenta;

    Double costoPromedio;

    Double totalCosto;

    Double ganancia;
}
