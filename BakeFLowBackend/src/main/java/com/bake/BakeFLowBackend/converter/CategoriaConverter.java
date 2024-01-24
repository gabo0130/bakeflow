package com.bake.BakeFLowBackend.converter;


import com.bake.BakeFLowBackend.dto.CategoriaDTO;
import com.bake.BakeFLowBackend.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaConverter {

    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion")
    })
    CategoriaDTO toCategoriaDTO(Categoria categoria);
}
