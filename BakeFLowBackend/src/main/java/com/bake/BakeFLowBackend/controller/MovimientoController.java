package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public void crearMovimiento() {

    }

    @GetMapping
    public void obtenerMovimiento() {

    }


}
