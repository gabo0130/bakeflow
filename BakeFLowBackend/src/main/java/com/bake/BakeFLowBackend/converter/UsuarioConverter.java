package com.bake.BakeFLowBackend.converter;


import com.bake.BakeFLowBackend.dto.UsuarioDTO;
import com.bake.BakeFLowBackend.entity.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioConverter {

    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "documento", target = "documento"),
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "password", target = "password")
    })
    UsuarioDTO toUsuarioDTO(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(UsuarioDTO usuarioDTO);
}
