package dto;

import domain.Categoria;

import java.time.LocalDateTime;

public class MateriaisDeEstudoDTO {

    Long id;

    String titulo;

    Categoria categoria;

    String url;

    String resumo;

    LocalDateTime dataInicio;

    LocalDateTime dataConclusao;

}
