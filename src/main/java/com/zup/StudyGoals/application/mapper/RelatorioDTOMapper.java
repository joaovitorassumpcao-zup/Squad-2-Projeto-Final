package com.zup.StudyGoals.application.mapper;

import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.dto.RelatorioDTO;
import org.mapstruct.Mapper;

@Mapper
public interface RelatorioDTOMapper {
    RelatorioDTO relatorioParaDTO(Relatorio relatorio);
    Relatorio DTOParaRelatorio(RelatorioDTO relatorioDTO);
}
