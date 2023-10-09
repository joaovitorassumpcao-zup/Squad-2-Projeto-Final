package com.zup.StudyGoals.application;

import com.zup.StudyGoals.data.MetaRepository;
import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MetaDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class MetaServiceTest {

    @InjectMocks
    private MetaService metaService;

    @Mock
    private MetaRepository metaRepository;

    @Test
    void TesteListarMetas() throws Exception{

        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"), null);
        List<MaterialDeEstudo> listaMaterial = new ArrayList<>();
        listaMaterial.add(materialDeEstudo);

        List<Meta> listaMocks = new ArrayList<>();
        listaMocks.add(new Meta(1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00") , 30, "Melhorar gramática", listaMaterial));

        when(metaRepository.findAll()).thenReturn(listaMocks);

        List<MetaDTO> listaResultado = metaService.listarMetas();
        verify(metaRepository).findAll();

        assertNotNull(listaResultado);
        assertEquals(listaMocks.size(),listaResultado.size());
        assertEquals(listaMocks.get(0).getAssunto(), listaResultado.get(0).getAssunto());
        assertEquals(listaMocks.get(0).getDataDeInicio(), listaResultado.get(0).getDataDeInicio());
        assertEquals(listaMocks.get(0).getDataFinal(), listaResultado.get(0).getDataFinal());
        assertEquals(listaMocks.get(0).getMetaMinutosDia(), listaResultado.get(0).getMetaMinutosDia());
        assertEquals(listaMocks.get(0).getObjetivo(), listaResultado.get(0).getObjetivo());
    }

    @Test
    void buscarMetaPorId() throws Exception{
    }

    @Test
    void adicionarNovaMetaComMateriais() throws Exception{
    }

    @Test
    void editarMeta() throws Exception{
    }

    @Test
    void deletarMeta() throws Exception{
    }
}