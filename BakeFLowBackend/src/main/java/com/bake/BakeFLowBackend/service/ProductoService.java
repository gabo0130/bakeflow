package com.bake.BakeFLowBackend.service;

import com.bake.BakeFLowBackend.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    Long registrarProducto(ProductoDTO productoDTO);

    List<ProductoDTO> obtenerProductos(String nombre, String descripcion, Long categoriaId, Double precio);

    ProductoDTO getProducto(Long id);

    Long actualizarProcuto(Long id, ProductoDTO productoDTO);

    void eliminarProducto(Long id);
}
