package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
}
