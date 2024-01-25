package com.bake.BakeFLowBackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private String nombre;
    private String documento;
    private String usuario;
    private String password;
    private Long tipoDocumentoId;
}
