package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.converter.UsuarioConverter;
import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.entity.Usuario;
import com.bake.BakeFLowBackend.repository.UsuarioRepository;
import com.bake.BakeFLowBackend.service.UsuarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;


    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO) {

    }

    @Override
    public UsuarioDTO getUsuario(Long id)  {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Usuario no encontrado");
        });
        return usuarioConverter.toUsuarioDTO(usuario);
    }
}
