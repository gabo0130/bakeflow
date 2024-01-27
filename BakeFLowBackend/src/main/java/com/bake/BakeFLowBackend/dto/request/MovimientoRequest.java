package com.bake.BakeFLowBackend.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MovimientoRequest {

    @NotNull(message = "La cantidad no puede ser nula")
    private Integer cantidad;
    private Double costoTotal;

    private Double costoUnitario;

    @NotNull(message = "El ID del producto no puede ser nulo")
    private Long productoId;

    private Long tipoMovimientoId;

}
