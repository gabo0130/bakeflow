package com.bake.BakeFLowBackend.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MovimientoRequest {

    @NotNull(message = "La cantidad no puede ser nula")
    private Integer cantidad;
    private Integer costoTotal;

    private Integer costoUnitario;

    @NotNull(message = "El ID del producto no puede ser nulo")
    private Long productoId;

    @NotNull(message = "El ID del tipo de movimiento no puede ser nulo")
    private Long tipoMovimientoId;

}
