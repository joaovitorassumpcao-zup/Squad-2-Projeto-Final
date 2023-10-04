package com.zup.StudyGoals.application.mapper;

import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MetaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MetaDTOMapper {
    MetaDTOMapper INSTANCE = Mappers.getMapper(MetaDTOMapper.class);
    MetaDTO metaParaDTO(Meta meta);
    Meta DTOParaMeta(MetaDTO metaDTO);
}
