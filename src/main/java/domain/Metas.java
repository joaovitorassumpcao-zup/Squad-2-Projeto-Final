package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Metas {

    Long id;

    String assunto;

    LocalDateTime dataDeInicio;

    LocalDateTime dataFinal;

    int metaMinutosDia;

    String objetivo;

    //Estabelecer uma FK(Foreign Key) pra criação de uma tabela de ligação
    List<MateriaisDeEstudo> materiaisDeEstudoList = new ArrayList<>();

}
