package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.entity.Categoria;
import com.bake.BakeFLowBackend.repository.CategoriaRepository;
import com.bake.BakeFLowBackend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }
}
