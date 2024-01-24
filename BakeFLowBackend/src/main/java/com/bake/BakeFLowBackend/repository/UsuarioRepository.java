package com.bake.BakeFLowBackend.repository;

import com.bake.BakeFLowBackend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findOneByUsuario(String username);

    Usuario findByUsuario(String username);
}
