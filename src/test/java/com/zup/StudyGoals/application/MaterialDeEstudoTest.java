package com.zup.StudyGoals.application;

import com.zup.StudyGoals.application.MaterialDeEstudoService;
import com.zup.StudyGoals.data.MaterialDeEstudoRepository;
import com.zup.StudyGoals.data.MetaRepository;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MaterialDeEstudoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class MaterialDeEstudoTest {

    @InjectMocks
    private MaterialDeEstudoService service;

    @Mock
    private MaterialDeEstudoRepository materialDeEstudoRepository;

    @Mock
    private MetaRepository metaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarMateriaisTest() {
        MaterialDeEstudo material = new MaterialDeEstudo();
        when(materialDeEstudoRepository.findAll()).thenReturn(Arrays.asList(material));

        List<MaterialDeEstudoDTO> result = service.listarMateriais();
        assertEquals(1, result.size());
        assertEquals(material.getTitulo(), result.get(0).getTitulo());
    }

    @Test
    void buscarMaterialPorIdTest() {
        MaterialDeEstudo material = new MaterialDeEstudo();
        when(materialDeEstudoRepository.findById(1L)).thenReturn(Optional.of(material));

        Optional<MaterialDeEstudoDTO> result = service.buscarMaterialPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(material.getTitulo(), result.get().getTitulo());
    }


    @Test
    void adicionarMetaParaMaterialTest() {
        Meta meta = new Meta();
        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));

        assertEquals(meta, service.adiconarMetaParaMaterial(1L));
    }

    @Test
    void adicionarMetaParaMaterialInvalidIdTest() {
        assertThrows(IllegalArgumentException.class, () -> service.adiconarMetaParaMaterial(null));
    }

    @Test
    void cadastrarMaterialTest() {
        MaterialDeEstudo material = new MaterialDeEstudo();
        Meta meta = new Meta();
        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));

        service.cadastrarMaterial(material, 1L);

        verify(materialDeEstudoRepository).save(material);
    }

    @Disabled
    @Test
    void alterarMaterialTest() {
        MaterialDeEstudo materialExistente = new MaterialDeEstudo();
        MaterialDeEstudo materialAlterado = new MaterialDeEstudo();
        materialAlterado.setTitulo("Novo Título");
        when(materialDeEstudoRepository.findById(1L)).thenReturn(Optional.of(materialExistente));

        MaterialDeEstudo resultado = service.alterarMaterial(1L, materialAlterado);

        assertEquals("Novo Título", resultado.getTitulo());
    }

    @Test
    void deletarMaterialTest() {
        service.deletarMaterial(1L);

        verify(materialDeEstudoRepository).deleteById(1L);
    }
}