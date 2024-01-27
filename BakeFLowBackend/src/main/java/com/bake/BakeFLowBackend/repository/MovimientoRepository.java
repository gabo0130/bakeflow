package com.bake.BakeFLowBackend.repository;


import com.bake.BakeFLowBackend.entity.Movimiento;
import com.bake.BakeFLowBackend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT m FROM Movimiento m " +
            "WHERE (:productoId IS NULL OR m.producto.id = :productoId) " +
            "AND (:esVenta IS NULL OR m.tipoMovimiento.esVenta = :esVenta) " +
            "AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> informeMovimientosEntreFechas(@Param("productoId") Long productoId,
                                                   @Param("esVenta") Boolean esVenta,
                                                   @Param("fechaInicio") Date fechaInicio,
                                                   @Param("fechaFin") Date fechaFin);



}
