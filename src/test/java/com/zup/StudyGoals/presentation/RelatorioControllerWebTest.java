package com.zup.StudyGoals.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.StudyGoals.application.MetaService;
import com.zup.StudyGoals.application.RelatorioService;
import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.dto.RelatorioDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RelatorioControllerWeb.class)

class RelatorioControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RelatorioService relatorioService;

    @MockBean
    private MetaService metaService;

    @Test
    void listarRelatorios() throws Exception{

        Long idMeta = 1L;
        Relatorio relatorio = new Relatorio(1L, LocalDateTime.parse("2023-10-20T08:00:00"), 3000.0, 1000.0, 3, "VIDEO", 10, false, idMeta);
        RelatorioDTO relatorioDTO = new RelatorioDTO(relatorio);
        List<RelatorioDTO> listaRelatorios = new ArrayList<>();
        listaRelatorios.add(relatorioDTO);

        when(relatorioService.listarRelatorios()).thenReturn(listaRelatorios);

        String jsonRelatorio = objectMapper.writeValueAsString(listaRelatorios);

        mockMvc.perform(get("/api/relatorios")
                .content(String.valueOf(MediaType.APPLICATION_JSON))
                .content(jsonRelatorio))
                .andExpect(status().isOk());
    }

    @Test
    void cadastrarRelatorio() throws Exception{
        Long idMeta = 1L;
        Relatorio relatorio = new Relatorio(1L, LocalDateTime.parse("2023-10-20T08:00:00"), 3000.0, 1000.0, 3, "VIDEO", 10, false, idMeta);

        String jsonRelatorio = objectMapper.writeValueAsString(relatorio);

        mockMvc.perform(post("/api/relatorios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRelatorio)
                .param("idMeta", idMeta.toString()))
                .andExpect(status().isOk());

    }

    @Test
    void deletarRelatorio() throws Exception{
        Long id = 1L;
        mockMvc.perform(delete("/api/relatorios/{id}",id))
                .andExpect(status().isNoContent());
    }
}