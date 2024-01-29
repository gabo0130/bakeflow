package com.bake.BakeFLowBackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class MovimientosRequest {

    List<MovimientoRequest> movimientos;

    private Long tipoMovimientoId;

    public boolean isValid() {
        if(movimientos == null || movimientos.isEmpty())
            return false;

        for(MovimientoRequest movimiento : movimientos) {
            if (movimiento.getTipoMovimientoId() == null)
                movimiento.setTipoMovimientoId(tipoMovimientoId);

            if (movimiento.getCostoUnitario() == null && movimiento.getCostoTotal() == null)
                return false;
        }
        return true;
    }
}
