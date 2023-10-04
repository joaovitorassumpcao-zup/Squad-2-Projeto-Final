package com.zup.StudyGoals.dto;


import com.zup.StudyGoals.domain.MateriaisDeEstudo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MetasDTO {

    Long id;

    String assunto;

    LocalDateTime dataDeInicio;

    LocalDateTime dataFinal;

    int metaMinutosDia;

    String objetivo;

    List<MateriaisDeEstudo> materiaisDeEstudoList = new ArrayList<>();

}
