package com.bake.BakeFLowBackend.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoVendidoResponse {

    Integer cantidad;

    Double precioVentaPromedio;

    Double totalVenta;

    Double totalCosto;
}
