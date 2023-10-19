package com.zup.StudyGoals.application;

import com.zup.StudyGoals.data.MaterialDeEstudoRepository;
import com.zup.StudyGoals.data.MetaRepository;
import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MetaDTO;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class MetaServiceTest {

    @InjectMocks
    private MetaService metaService;
    @Mock
    private MetaRepository metaRepository;
    @Mock
    private MaterialDeEstudoRepository materialDeEstudoRepository;

    private Meta meta;

    @Before
    public void setUp() {
        metaService = new MetaService();
        meta = new Meta(1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00") , 30, "Melhorar gramática", null);
    }

    @Test
    void TesteListarMetas() throws Exception{

        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));
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
        assertEquals(listaMocks.get(0).getMateriaisDeEstudo(), listaResultado.get(0).getMateriaisDeEstudo());
    }

    @Test
    void testeEncontraMetaPorId() throws Exception{

        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));
        List<MaterialDeEstudo> listaMaterial = new ArrayList<>();
        listaMaterial.add(materialDeEstudo);

        Meta meta =  new Meta( 1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00") , 30, "Melhorar gramática", listaMaterial);

        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));

        Optional<MetaDTO> resultado = metaService.buscarMetaPorId(1L);
        verify(metaRepository, times(1)).findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(meta.getAssunto(),resultado.get().getAssunto());
        assertEquals(meta.getDataDeInicio(), resultado.get().getDataDeInicio());
        assertEquals(meta.getDataFinal(),resultado.get().getDataFinal());
        assertEquals(meta.getMetaMinutosDia(),resultado.get().getMetaMinutosDia());
        assertEquals(meta.getObjetivo(),resultado.get().getObjetivo());
        assertEquals(meta.getMateriaisDeEstudo(),resultado.get().getMateriaisDeEstudo());

    }

    @Test
    void testeNaoEncotraMetaPorId (){

        Long id = 2L;

        when(metaRepository.findById(id)).thenReturn(Optional.empty());
        Optional<MetaDTO> resultado = metaService.buscarMetaPorId(id);

        verify(metaRepository, times(1)).findById(id);
        assertFalse(resultado.isPresent());
    }

    @Test
    void testeAdicionarNovaMetaComMateriais() throws Exception{

        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));
        List<MaterialDeEstudo> listaMaterial = new ArrayList<>();
        listaMaterial.add(materialDeEstudo);


        Meta meta =  new Meta(1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00") , 30, "Melhorar gramática", listaMaterial);

        when(metaRepository.save(any(Meta.class))).thenReturn(meta);
        metaService.adicionarNovaMetaComMateriais(meta,listaMaterial);

        verify(metaRepository, times(1)).save(any(Meta.class));
        verify(materialDeEstudoRepository, times(1)).save(any(MaterialDeEstudo.class));

        //assertTrue(materialDeEstudo.materialTemUmaMeta(meta));

    }

    @Test
    void testeEditarMeta() throws Exception{
        List<MaterialDeEstudo> listaMaterial = new ArrayList<>();
        MaterialDeEstudo materialDeEstudo1 = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));

        listaMaterial.add(materialDeEstudo1);

        Meta metaOriginal =  new Meta( 1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-31T08:00:00") , 30, "Melhorar gramática", listaMaterial);

        MaterialDeEstudo materialDeEstudo2 = new MaterialDeEstudo(2L, "Pronúncia dias da semana", Categoria.AUDIO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));
        listaMaterial.add(materialDeEstudo2);

        Meta metaAlterada =  new Meta( 1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-31T08:00:00") , 30, "Melhorar gramática", listaMaterial);

        when(metaRepository.findById(1L)).thenReturn(Optional.of(metaOriginal));
        when(metaRepository.save(any(Meta.class))).thenReturn(metaAlterada);

        Meta resultado = metaService.editarMeta(1L,metaAlterada);

        verify(metaRepository, times(1)).findById(1L);
        verify(metaRepository, times(1)).save(any(Meta.class));

        assertEquals(metaAlterada.getMateriaisDeEstudo(),resultado.getMateriaisDeEstudo());

    }

    @Test
    void testeDeletarMeta() throws Exception{
        Long id = 1L;
        metaService.deletarMeta(id);
        verify(metaRepository, times(1)).deleteById(id);
    }
}