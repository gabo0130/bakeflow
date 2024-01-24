package com.bake.BakeFLowBackend.service;

import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import org.apache.coyote.BadRequestException;

public interface UsuarioService {
    void registrarUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO getUsuario(Long id) ;
}
