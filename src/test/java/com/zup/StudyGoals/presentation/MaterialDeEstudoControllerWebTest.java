package com.zup.StudyGoals.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.StudyGoals.application.MaterialDeEstudoService;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.dto.MaterialDeEstudoDTO;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MaterialDeEstudoControllerWeb.class)
public class MaterialDeEstudoControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialDeEstudoService materialDeEstudoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        materialDeEstudoService = new MaterialDeEstudoService();
    }

    @Test
    void listarTodosOsMateriaisTest() throws Exception {
        mockMvc.perform(get("/api/materiais"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarMaterialPorIdTest() throws Exception {
        MaterialDeEstudoDTO dto = new MaterialDeEstudoDTO();
        when(materialDeEstudoService.buscarMaterialPorId(1L)).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/api/materiais/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void buscarMaterialPorIdNotFoundTest() throws Exception {
        when(materialDeEstudoService.buscarMaterialPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/materiais/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void criarMaterialTest() throws Exception {
        MaterialDeEstudoDTO dto = new MaterialDeEstudoDTO();
        String json = objectMapper.writeValueAsString(dto);

        // TODO erro getMetas() retorna nulo
        mockMvc.perform(post("/api/materiais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void alterarMaterialTest() throws Exception {
        MaterialDeEstudo material = new MaterialDeEstudo();
        when(materialDeEstudoService.alterarMaterial(anyLong(), any())).thenReturn(material);

        String json = objectMapper.writeValueAsString(material);

        mockMvc.perform(put("/api/materiais/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deletarMaterialTest() throws Exception {
        mockMvc.perform(delete("/api/materiais/1"))
                .andExpect(status().isOk());
    }
}

