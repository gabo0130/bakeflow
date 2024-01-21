package com.bake.BakeFLowBackend.repository;

import com.bake.BakeFLowBackend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findOneByUsuario(String username);
}
