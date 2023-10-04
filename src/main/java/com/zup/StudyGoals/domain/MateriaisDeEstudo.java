package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MATERIAISDEESTUDO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MateriaisDeEstudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String titulo;

    @Column(nullable = false)
    Categoria categoria;

    @Column(nullable = false)
    String url;

    @Column(nullable = false)
    String resumo;

    @Column(nullable = false)
    LocalDateTime dataInicio;

    @Column(nullable = false)
    LocalDateTime dataConclusao;

}
