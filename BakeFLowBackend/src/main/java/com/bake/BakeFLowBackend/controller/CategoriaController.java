package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Categoria")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    ResponseEntity<BaseResponse> obtenerCategorias() {
        return ResponseEntity.ok(BaseResponse.success("Categorias obtenidas con éxito", categoriaService.obtenerCategorias()));
    }
}
