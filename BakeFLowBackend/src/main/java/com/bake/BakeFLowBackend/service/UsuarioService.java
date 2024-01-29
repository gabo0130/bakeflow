package com.bake.BakeFLowBackend.service;

import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UsuarioService {
    Long registrarUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO getUsuario(Long id) ;

    Long actualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);

    List<UsuarioDTO >obtenerUsuarios();


}
