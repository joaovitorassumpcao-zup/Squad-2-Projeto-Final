package com.zup.StudyGoals.application;

import com.zup.StudyGoals.data.MetaRepository;
import com.zup.StudyGoals.data.RelatorioRepository;
import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.dto.RelatorioDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class RelatorioTest {

    @InjectMocks
    private RelatorioService relatorioService;

    @InjectMocks
    private MetaService metaService;

    @Mock
    private RelatorioRepository relatorioRepository;

    @Mock
    private MetaRepository metaRepository;

    private Relatorio relatorio;

    Relatorio relatorio1 = new Relatorio(1L, LocalDateTime.parse("2023-10-20T08:00:00"), 1.0,
            1.0,1,"WORKSHOP(2)",2,true,1L);

    @Test
    void testListarRelatorios() throws Exception {

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

    @Test
    void testBuscarRelatorioPorId() throws Exception {

        when(relatorioRepository.findById(1L)).thenReturn(Optional.of(relatorio1));

        Optional<RelatorioDTO> resultado = relatorioService.buscarRelatorioPorId(1L);
        verify(relatorioRepository, Mockito.times(1)).findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(relatorio1.getHoraRegistro(), resultado.get().getHoraRegistro());
        assertEquals(relatorio1.getTempoTotal(), resultado.get().getTempoTotal());
        assertEquals(relatorio1.getMediaTempo(), resultado.get().getMediaTempo());
        assertEquals(relatorio1.getTotalResumos(), resultado.get().getTotalResumos());
        assertEquals(relatorio1.getCategoriaMaisConsumida(), resultado.get().getCategoriaMaisConsumida());
        assertEquals(relatorio1.getDiasParaConcluir(), resultado.get().getDiasParaConcluir());
        assertEquals(relatorio1.isMetaConcluida(), resultado.get().isMetaConcluida());
        assertEquals(relatorio1.getMetaId(), resultado.get().getMetaId());

    }

    @Test
    void testNaoEncontraRelatorioPorId() throws Exception {

        Long id = 2L;

        when(relatorioRepository.findById(id)).thenReturn(Optional.empty());
        Optional<RelatorioDTO> resultado = relatorioService.buscarRelatorioPorId(id);

        verify(relatorioRepository, Mockito.times(1)).findById(id);
        assertFalse(resultado.isPresent());

    }

    @Test
    void testCadastrarRelatorio() throws Exception {
        when(relatorioRepository.save(any(Relatorio.class))).thenReturn(relatorio1);
        relatorioService.cadastrarRelatorio(relatorio1);

        verify(relatorioRepository, Mockito.times(1)).save(any(Relatorio.class));
    }

    @Test
    void testDeletarRelatorio() throws Exception {
        Long id = 1L;
        relatorioService.deletarRelatorio(id);
        verify(relatorioRepository, Mockito.times(1)).deleteById(id);
    }

    Long idMeta = 1L;

    @Test
    void testMetaFoiConcluida() {
        LocalDateTime dataAntesDeAgora = LocalDateTime.now().minusHours(1);

        Meta meta = new Meta();
        meta.setDataFinal(dataAntesDeAgora);

        Mockito.when(metaRepository.findById(idMeta)).thenReturn(Optional.of(meta));

        boolean resultado = relatorioService.metaFoiConcluida(idMeta);

        Assertions.assertTrue(resultado);

        Mockito.verify(metaRepository, Mockito.times(1)).findById(idMeta);
    }

    @Test
    void testMetaNãoConcluida() {
        LocalDateTime dataDepoisDeAgora = LocalDateTime.now().plusHours(1);

        Meta meta = new Meta();
        meta.setDataFinal(dataDepoisDeAgora);

        Mockito.when(metaRepository.findById(idMeta)).thenReturn(Optional.of(meta));

        boolean resultado = relatorioService.metaFoiConcluida(idMeta);

        Assertions.assertFalse(resultado);

        Mockito.verify(metaRepository, Mockito.times(1)).findById(idMeta);
    }

    @Test
    void testCalcularDiasParaMeta() {
        LocalDateTime dataFinal = LocalDateTime.now().plusDays(3);

        Meta meta = new Meta();
        meta.setDataFinal(dataFinal);

        Mockito.when(metaRepository.findById(idMeta)).thenReturn(Optional.of(meta));

        LocalDateTime agora = LocalDateTime.now();

        int diasRestantes = relatorioService.calcularDiasParaMeta(idMeta);

        Duration duracao = Duration.between(agora, dataFinal);
        int diasEsperados = (int) duracao.toDays();

        Assertions.assertEquals(diasEsperados, diasRestantes);

        Mockito.verify(metaRepository, Mockito.times(1)).findById(idMeta);
    }

    @Test
    void testCalcularTempoTotalDedicado() throws Exception{
        LocalDateTime dataInicio1 = LocalDateTime.now();
        LocalDateTime dataConclusao1 = dataInicio1.plusMinutes(30);
        LocalDateTime dataInicio2 = LocalDateTime.now();
        LocalDateTime dataConclusao2 = dataInicio2.plusMinutes(60);

        MaterialDeEstudo materialDeEstudo1 = new MaterialDeEstudo();
        materialDeEstudo1.setDataInicio(dataInicio1);
        materialDeEstudo1.setDataConclusao(dataConclusao1);

        MaterialDeEstudo materialDeEstudo2 = new MaterialDeEstudo();
        materialDeEstudo2.setDataInicio(dataInicio2);
        materialDeEstudo2.setDataConclusao(dataConclusao2);

        Meta meta = new Meta();
        meta.setMateriaisDeEstudo(Arrays.asList(materialDeEstudo1,materialDeEstudo2));

        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));

        double resultadoTempoTotal = relatorioService.calcularTempoTotalDedicado(1L);

        verify(metaRepository).findById(1L);

        double resultadoEsperado = 30 + 60;

        assertEquals(resultadoEsperado,resultadoTempoTotal);
    }

    @Test
    void testCalcularMediaTempoDiaria() throws Exception{
        
        Meta meta = new Meta();
        meta.setId(1L);

        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));

        when(relatorioService.calcularTempoTotalDedicado(1L)).thenReturn(3000.0);

        double resultadoMedia = relatorioService.calcularMediaTempoDiaria(1L);

        verify(metaRepository, Mockito.times(1)).findById(1L);
        verify(relatorioService, Mockito.times(1)).calcularTempoTotalDedicado(1L);

        assertEquals(3000.0,resultadoMedia);

    }


    @Test
    void testCalcularResumosFeitos() throws Exception{
        Meta meta = new Meta();
        meta.setId(1L);

        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));

        int resumos = relatorioService.calcularResumosFeitos(1L);

        verify(metaRepository).findById(1L);

        assertEquals(0,resumos);

    }

}
