package com.bake.BakeFLowBackend.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nombre;
    private String documento;
    private String usuario;
    private String password;
}
