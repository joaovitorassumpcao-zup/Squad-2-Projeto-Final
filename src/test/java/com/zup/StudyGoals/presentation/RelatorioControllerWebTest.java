package com.zup.StudyGoals.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.StudyGoals.application.RelatorioService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(RelatorioControllerWebTest.class)
class RelatorioControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RelatorioService relatorioService;


    @Test
    void listarRelatorios() throws Exception{
    }

    @Test
    void cadastrarRelatorio() throws Exception{
    }
}