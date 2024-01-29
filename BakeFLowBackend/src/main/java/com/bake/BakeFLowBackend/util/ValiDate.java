package com.bake.BakeFLowBackend.util;

import com.bake.BakeFLowBackend.dto.BetweenDate;

import java.util.Date;

public class ValiDate {
    public static BetweenDate valideFechas(Date fechaInicio, Date fechaFin) {
        BetweenDate betweenDate = new BetweenDate();
        if (fechaInicio == null)
            fechaInicio = new Date(0);
        if (fechaFin == null)
            fechaFin = new Date();

        if (fechaInicio.after(fechaFin))
            throw new IllegalArgumentException("La fecha de inicio no puede ser mayor a la fecha de fin");

        fechaInicio.setHours(0);
        fechaInicio.setMinutes(0);
        fechaInicio.setSeconds(0);
        fechaFin.setHours(23);
        fechaFin.setMinutes(59);
        fechaFin.setSeconds(59);
        betweenDate.setFechaInicio(fechaInicio);
        betweenDate.setFechaFin(fechaFin);
        return betweenDate;
    }
}
