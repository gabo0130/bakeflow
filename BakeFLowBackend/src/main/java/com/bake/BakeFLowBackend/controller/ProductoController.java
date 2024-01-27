package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.ProductoDTO;
import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Producto")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<BaseResponse> registrarProducto(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(BaseResponse.success("Producto registrado con éxito",
                productoService.registrarProducto(productoDTO)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> obtenerProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Double precio
    ) {
        return ResponseEntity.ok(BaseResponse.success("Productos obtenidos con éxito", productoService.obtenerProductos(nombre, descripcion, categoriaId, precio)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> obtenerProducto(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.success("Producto obtenido con éxito", productoService.getProducto(id)));
    }

    @PutMapping("/actualizarProducto/{id}")
    public ResponseEntity<BaseResponse> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(BaseResponse.success("Producto actualizado con éxito", productoService.actualizarProcuto(id,productoDTO)));
    }

    @DeleteMapping("/eliminarProducto/{id}")
    public ResponseEntity<BaseResponse> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok(BaseResponse.success("Producto eliminado con éxito", null));
    }

}
