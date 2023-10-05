package com.zup.StudyGoals.dto;


import com.zup.StudyGoals.domain.MaterialDeEstudo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MetaDTO {

    Long id;

    String assunto;

    LocalDateTime dataDeInicio;

    LocalDateTime dataFinal;

    int metaMinutosDia;

    String objetivo;

    List<MaterialDeEstudo> materiaisDeEstudoList = new ArrayList<>();

}
