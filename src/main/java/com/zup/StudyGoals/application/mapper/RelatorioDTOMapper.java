package com.zup.StudyGoals.application.mapper;

import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.dto.RelatorioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RelatorioDTOMapper {
    RelatorioDTOMapper INSTANCE = Mappers.getMapper(RelatorioDTOMapper.class);
    RelatorioDTO relatorioParaDTO(Relatorio relatorio);
    Relatorio DTOParaRelatorio(RelatorioDTO relatorioDTO);
}
