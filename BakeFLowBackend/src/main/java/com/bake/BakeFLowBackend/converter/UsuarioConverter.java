package com.bake.BakeFLowBackend.converter;


import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.entity.Usuario;
import com.bake.BakeFLowBackend.security.Sha256PasswordEncoder;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioConverter {


    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "documento", target = "documento"),
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "password", target = "password", qualifiedByName = "encodePassword"),
            @Mapping(source = "tipoDocumentoId", target = "tipoDocumento.id")
    })
    Usuario toUsuario(UsuarioDTO usuarioDTO);


    @InheritInverseConfiguration
    @Mapping(target = "password", ignore = true)
    UsuarioDTO toUsuarioDTO(Usuario usuario);

    List<UsuarioDTO> toUsuarioDTOs(List<Usuario> usuarios);

    @Named("encodePassword")
    default String encodePassword(String password) {
        Sha256PasswordEncoder INSTANCE = new Sha256PasswordEncoder();
        return INSTANCE.encode(password);
    }

}
