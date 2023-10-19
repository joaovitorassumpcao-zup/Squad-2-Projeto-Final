package com.zup.StudyGoals.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.StudyGoals.application.MetaService;
import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MetaDTO;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MetaControllerWeb.class)
class MetaControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MetaService metaService;

    private Meta meta;

    @Before
    public void setUp() {
        metaService = new MetaService();
        meta = new Meta(1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00") , 30, "Melhorar gramática", null);
    }

    @Test
    void listarTodasAsMetas() throws Exception{

        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));
        List<MaterialDeEstudo> listaMaterial = new ArrayList<>();
        listaMaterial.add(materialDeEstudo);

        Meta meta = new Meta(1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-30T08:00:00") , 30, "Melhorar gramática", listaMaterial);

        List<MetaDTO> listaMockDTO = new ArrayList<>();
        listaMockDTO.add(new MetaDTO(meta));

        String jsonResultado= "[{\"assunto\":\"Inglês\",\"dataDeInicio\":\"2023-10-20 08:00:00\",\"dataFinal\":\"2023-10-30 08:00:00\",\"metaMinutosDia\":30,\"objetivo\":\"Melhorar gramática\",\"materiaisDeEstudo\":[{\"id\":1,\"titulo\":\"Verbo to be\",\"categoria\":\"VIDEO\",\"url\":\"https://www.youtube.com\",\"resumo\":\"Lorem ipsum\",\"dataInicio\":\"2023-10-20 08:00:00\",\"dataConclusao\":\"2023-10-20 08:00:00\",\"metas\":null}]}]";

        when(metaService.listarMetas()).thenReturn(listaMockDTO);

        mockMvc.perform(get("/api/metas")
                .content(String.valueOf(MediaType.APPLICATION_JSON))
                .content(jsonResultado))
                .andExpect(status().isOk());
    }

    @Test
    void testeBuscarMetaPorId() throws Exception{

        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));
        List<MaterialDeEstudo> listaMaterial = new ArrayList<>();
        listaMaterial.add(materialDeEstudo);

        Meta meta = new Meta(1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-30T08:00:00") , 30, "Melhorar gramática", listaMaterial);

        String jsonResultado = objectMapper.writeValueAsString(meta);

        when(metaService.buscarMetaPorId(meta.getId())).thenReturn(Optional.of(new MetaDTO(meta)));

        mockMvc.perform(get("/api/metas/{id}",1)
                        .content(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(jsonResultado))
                .andExpect(status().isOk());
    }

    @Test
    void testeAdicionarNovaMeta() throws Exception{
        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));
        List<MaterialDeEstudo> listaMaterial = new ArrayList<>();
        listaMaterial.add(materialDeEstudo);

        Meta meta = new Meta(1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-30T08:00:00") , 30, "Melhorar gramática", listaMaterial);

        String json = objectMapper.writeValueAsString(meta);
        
        mockMvc.perform(post("/api/metas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("Nova meta criada com sucesso! "));
    }

    @Test
    void testeAlterarMeta() throws Exception{

            MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(1L, "Verbo to be", Categoria.VIDEO, "https://www.youtube.com", "Lorem ipsum",LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-20T08:00:00"));
            List<MaterialDeEstudo> listaMaterial = new ArrayList<>();
            listaMaterial.add(materialDeEstudo);

            Meta meta = new Meta(1L, "Inglês", LocalDateTime.parse("2023-10-20T08:00:00"), LocalDateTime.parse("2023-10-30T08:00:00") , 30, "Melhorar gramática", listaMaterial);

            String json = objectMapper.writeValueAsString(meta);

            when(metaService.editarMeta(eq(1L),any(Meta.class))).thenReturn(meta);

            mockMvc.perform(put("/api/metas/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Meta alterada com sucesso! "));


    }

    @Test
    void testeDeletarMeta() throws Exception{
        Long id = 1L;
        mockMvc.perform(delete("/api/metas/{id}",id))
                .andExpect(status().isOk());
    }
}