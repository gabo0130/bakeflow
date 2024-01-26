package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.request.MovimientoRequest;
import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<BaseResponse> registrarMovimiento(@RequestBody @Valid MovimientoRequest movimientoRequest,
                                                 BindingResult bindingResult) {
        if(movimientoRequest.getCostoUnitario()==null&&movimientoRequest.getCostoTotal()==null){
            bindingResult.rejectValue("costoUnitario", "costoUnitario", "Debe ingresar el costo unitario o el costo total");
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(BaseResponse.error("Error al registrar el movimiento", bindingResult.getAllErrors()));
        };

        movimientoService.registrarMovimiento(movimientoRequest);
        return ResponseEntity.ok(BaseResponse.success("Movimiento registrado con éxito",null));
    }

    @GetMapping
    public void obtenerMovimiento() {

    }


}
