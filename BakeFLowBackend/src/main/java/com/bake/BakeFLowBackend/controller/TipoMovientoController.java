package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.service.TipoMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TipoMovimiento")
public class TipoMovientoController {

    @Autowired
    private TipoMovimientoService tipoMovimientoService;

    @GetMapping
    ResponseEntity<BaseResponse> obtenerProductos() {
        return ResponseEntity.ok(BaseResponse.success("Tipo de movimientos obtenidos con éxito", tipoMovimientoService.obtenerTipoMovimientos()));
    }

}
