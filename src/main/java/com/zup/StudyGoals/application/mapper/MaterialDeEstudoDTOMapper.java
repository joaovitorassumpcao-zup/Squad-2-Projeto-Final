package com.zup.StudyGoals.application.mapper;

import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.dto.MaterialDeEstudoDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MaterialDeEstudoDTOMapper {
    MaterialDeEstudoDTO materialDeEstudoParaDTO(MaterialDeEstudo materialDeEstudo);
    MaterialDeEstudo DTOParaMaterialDeEstudo(MaterialDeEstudoDTO materialDeEstudoDTO);
}
