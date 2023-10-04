package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_METAS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Metas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String assunto;

    @Column(nullable = false)
    LocalDateTime dataDeInicio;

    @Column(nullable = false)
    LocalDateTime dataFinal;

    @Column(nullable = false)
    int metaMinutosDia;

    @Column(nullable = false)
    String objetivo;

    //Estabelecer uma FK(Foreign Key) pra criação de uma tabela de ligação
    List<MateriaisDeEstudo> materiaisDeEstudoList = new ArrayList<>();

}
