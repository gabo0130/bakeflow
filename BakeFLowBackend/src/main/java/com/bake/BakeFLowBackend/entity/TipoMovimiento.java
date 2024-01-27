package com.bake.BakeFLowBackend.entity;


import com.bake.BakeFLowBackend.util.Operation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TipoMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Boolean esVenta;

    @Column(nullable = false)
    @JsonIgnore
    private String operacion;

    public Boolean esSuma() {
    	return this.operacion.equals(Operation.SUMA);
    }

    public Boolean esVenta() {
    	return this.esVenta;
    }


}
