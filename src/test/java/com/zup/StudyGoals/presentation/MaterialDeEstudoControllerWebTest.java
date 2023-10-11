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
}

