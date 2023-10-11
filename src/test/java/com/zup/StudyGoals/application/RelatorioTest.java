package com.zup.StudyGoals.application;

import com.zup.StudyGoals.data.RelatorioRepository;
import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.dto.RelatorioDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class RelatorioTest {

    @InjectMocks
    private RelatorioService relatorioService;

    @Mock
    private RelatorioRepository relatorioRepository;

    @Test
    void testListarRelatorios() throws Exception {

        Relatorio relatorio1 = new Relatorio(1L, LocalDateTime.parse("2023-10-20T08:00:00"), 1.0,
                1.0,1,"WORKSHOP(2)",2,true,1L);

        List<Relatorio> relatorioList = new ArrayList<>();
        relatorioList.add(relatorio1);

        when(relatorioRepository.findAll()).thenReturn(relatorioList);

        List<RelatorioDTO> resultadoRelatorioList = relatorioService.listarRelatorios();
        verify(relatorioRepository).findAll();

        assertNotNull(resultadoRelatorioList);
        assertEquals(relatorioList.size(),resultadoRelatorioList.size());
        assertEquals(relatorioList.get(0).getHoraRegistro(), resultadoRelatorioList.get(0).getHoraRegistro());
        assertEquals(relatorioList.get(0).getTempoTotal(), resultadoRelatorioList.get(0).getTempoTotal());
        assertEquals(relatorioList.get(0).getMediaTempo(), resultadoRelatorioList.get(0).getMediaTempo());
        assertEquals(relatorioList.get(0).getTotalResumos(), resultadoRelatorioList.get(0).getTotalResumos());
        assertEquals(relatorioList.get(0).getCategoriaMaisConsumida(), resultadoRelatorioList.get(0).getCategoriaMaisConsumida());
        assertEquals(relatorioList.get(0).getDiasParaConcluir(), resultadoRelatorioList.get(0).getDiasParaConcluir());
        assertEquals(relatorioList.get(0).isMetaConcluida(), resultadoRelatorioList.get(0).isMetaConcluida());
        assertEquals(relatorioList.get(0).getMetaId(), resultadoRelatorioList.get(0).getMetaId());

    }

    void testBuscarRelatorioPorId() throws Exception {

    }

}
