package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_RELATORIOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relatorios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    double tempoTotal;

    @Column(nullable = false)
    double mediaTempo;

    @Column(nullable = false)
    int totalResumos;

    @Column(nullable = false)
    String categoriaMaisConsumida;

    @Column(nullable = false)
    int diasParaConcluir;

    @Column(nullable = false)
    boolean metaConcluida;

}
