package com.bake.BakeFLowBackend.entity;

import com.bake.BakeFLowBackend.util.Operation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double costoUnitario;

    @Column(nullable = false)
    private Double costoTotal;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonIgnoreProperties("movimientos")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_movimiento_id", nullable = false)
    private TipoMovimiento tipoMovimiento;


    @PrePersist
    public void onCreate() {
        fecha = new Date();
    }


    public Boolean esVenta() {
        return tipoMovimiento.esVenta();
    }

    public Boolean esPerdida() {
        Operation operation = new Operation();
        return !tipoMovimiento.esVenta()&&operation.esResta(tipoMovimiento.getOperacion());
    }

    public Boolean esCompra() {
        Operation operation = new Operation();
        return operation.esSuma(tipoMovimiento.getOperacion());
    }

}
