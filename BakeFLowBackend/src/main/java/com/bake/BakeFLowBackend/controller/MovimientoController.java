package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.request.MovimientoRequest;
import com.bake.BakeFLowBackend.dto.request.MovimientosRequest;
import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/Movimiento")
@CrossOrigin(origins = "http://localhost:5173")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<BaseResponse> registrarMovimiento(@RequestBody @Valid MovimientoRequest movimientoRequest,
                                                            Errors errors) {
        /*if (movimientoRequest.getCostoUnitario() == null && movimientoRequest.getCostoTotal() == null) {
            errors.rejectValue("costoUnitario || costoTotal", "costoUnitario || costoTotal", "Debe ingresar el costo unitario o el costo total");
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(BaseResponse.error("Error al registrar el movimiento", errors.getAllErrors()));
        };*/

        movimientoService.registrarMovimiento(movimientoRequest);
        return ResponseEntity.ok(BaseResponse.success("Movimiento registrado con éxito",null));
    }

    @PostMapping("/registrarMovimientos")
    public ResponseEntity<BaseResponse> registrarMovimientos(@RequestBody @Valid MovimientosRequest movimientoRequest,
                                                            Errors errors) {

        if(!movimientoRequest.isValid()){
            errors.rejectValue("movimientos", "movimientos", "Movimientos inválidos");
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(BaseResponse.error("Error al registrar el movimiento", errors.getAllErrors()));
        };

        movimientoService.registrarMovimiento(movimientoRequest);
        return ResponseEntity.ok(BaseResponse.success("Movimiento registrado con éxito",null));
    }


    @GetMapping("/reporteMovimientos")
    public ResponseEntity<BaseResponse> reporteMovimientos(@RequestParam (required = false) Long productoId,
                                                           @RequestParam (required = false)Boolean esVenta,
                                                           @RequestParam (required = false)Date fechaInicio,
                                                           @RequestParam (required = false) Date fechaFin) {
        return ResponseEntity.ok(BaseResponse.success("Movimientos obtenidos con éxito", movimientoService.informeMovimientosEntreFechas(productoId, esVenta, fechaInicio, fechaFin)));
    }

    @GetMapping("/reporteVentas")
    public ResponseEntity<BaseResponse> reporteVentas(@RequestParam (required = false) Long productoId,
                                                           @RequestParam(required = false) Date fechaInicio,
                                                           @RequestParam (required = false)Date fechaFin) {
        return ResponseEntity.ok(BaseResponse.success("Movimientos obtenidos con éxito", movimientoService.informeVentasTotales(productoId, fechaInicio, fechaFin)));
    }

    @GetMapping("/reporteVentasXcosto")
    public ResponseEntity<BaseResponse> reporteVentasXcosto(@RequestParam (required = false) Long productoId) {
        return ResponseEntity.ok(BaseResponse.success("Movimientos obtenidos con éxito", movimientoService.informeVentasTotalesXcosto(productoId)));
    }


}
