package com.zup.StudyGoals.application.mapper;

import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.dto.MaterialDeEstudoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialDeEstudoDTOMapper {
    MaterialDeEstudoDTOMapper INSTANCE = Mappers.getMapper(MaterialDeEstudoDTOMapper.class);
//    MaterialDeEstudoDTO materialDeEstudoParaDTO(MaterialDeEstudo materialDeEstudo);
//    @Mapping(target = "id", ignore = true)
//    MaterialDeEstudo DTOParaMaterialDeEstudo(MaterialDeEstudoDTO materialDeEstudoDTO);
}
