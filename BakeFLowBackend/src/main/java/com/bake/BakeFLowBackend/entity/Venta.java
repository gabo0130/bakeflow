package com.bake.BakeFLowBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String detalles;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Date fecha;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("venta")
    private List<VentaMovimiento> ventaMovimientos = new ArrayList<>();


    @PrePersist
    public void onCreate() {
        fecha = new Date();
    }

}
