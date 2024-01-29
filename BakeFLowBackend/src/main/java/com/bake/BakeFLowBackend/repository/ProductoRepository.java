package com.bake.BakeFLowBackend.repository;


import com.bake.BakeFLowBackend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p " +
            "WHERE (:nombre IS NULL OR LOWER(p.nombre) LIKE %:nombre%) " +
            "AND (:descripcion IS NULL OR LOWER(p.descripcion) LIKE %:descripcion%) " +
            "AND (:categoriaId IS NULL OR p.categoria.id = :categoriaId) " +
            "AND (:precio IS NULL OR p.precio = :precio)"+
            "AND (p.habilitado = true or p.habilitado is null)"
    )
    List<Producto> buscarProductosConFiltros(String nombre, String descripcion, Long categoriaId, Double precio);

}
