package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/Venta")
@CrossOrigin(origins = "http://localhost:5173")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<BaseResponse> reporteVentas(@RequestParam(required = false) Date fechaInicio,
                                                      @RequestParam (required = false)Date fechaFin) {
        return ResponseEntity.ok(BaseResponse.success("Ventas obtenidas ", ventaService.obtenerVentas(fechaInicio, fechaFin)));
    }

}
