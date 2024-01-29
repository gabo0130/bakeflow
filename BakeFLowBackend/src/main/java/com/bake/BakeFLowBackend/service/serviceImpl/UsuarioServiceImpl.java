package com.bake.BakeFLowBackend.service.serviceImpl;

import com.bake.BakeFLowBackend.converter.UsuarioConverter;
import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.entity.Usuario;
import com.bake.BakeFLowBackend.repository.UsuarioRepository;
import com.bake.BakeFLowBackend.security.Sha256PasswordEncoder;
import com.bake.BakeFLowBackend.service.UsuarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;




    @Override
    public Long registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findByUsuario(usuarioDTO.getUsuario());
        if (usuario != null) {
            throw new IllegalArgumentException("Usuario ya existe");
        }
        return usuarioRepository.save(usuarioConverter.toUsuario(usuarioDTO)).getId();
    }

    @Override
    public UsuarioDTO getUsuario(Long id)  {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Usuario no encontrado");
        });
        return usuarioConverter.toUsuarioDTO(usuario);
    }


    public Usuario getUsuario(String usuario)  {
        Usuario user = usuarioRepository.findByUsuario(usuario);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return user;
    }

    @Override
    public Long actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Usuario no encontrado");
        });
        usuario = usuarioConverter.toUsuario(usuarioDTO);
        return usuarioRepository.save(usuario).getId();
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Usuario no encontrado");
        });
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioConverter.toUsuarioDTOs(usuarioRepository.findAll());
    }
}
