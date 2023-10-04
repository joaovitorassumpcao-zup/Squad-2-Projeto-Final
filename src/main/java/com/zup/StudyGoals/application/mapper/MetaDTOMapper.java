package com.zup.StudyGoals.application.mapper;

import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MetaDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MetaDTOMapper {
    MetaDTO metaParaDTO(Meta meta);
    Meta DTOParaMeta(MetaDTO metaDTO);
}
