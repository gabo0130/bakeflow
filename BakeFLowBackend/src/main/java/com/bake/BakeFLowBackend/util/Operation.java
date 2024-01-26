package com.bake.BakeFLowBackend.util;

import org.springframework.stereotype.Component;

@Component
public class Operation {

    public static final String SUMA = "SUMA";
    public static final String RESTA = "RESTA";

    public static final String SUM = "SUM";

    public static final String MINUS = "MINUS";

    public Boolean esSuma(String operacion) {
        return operacion.equals(SUMA)||operacion.equals(SUM);
    }

    public Boolean esResta(String operacion) {
        return operacion.equals(RESTA)||operacion.equals(MINUS);
    }

}
