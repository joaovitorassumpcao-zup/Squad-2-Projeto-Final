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
    private Long id_relatorios;

    @Column(nullable = false)
    private double tempoTotal;

    @Column(nullable = false)
    private double mediaTempo;

    @Column(nullable = false)
    private int totalResumos;

    @Column(nullable = false)
    private String categoriaMaisConsumida;

    @Column(nullable = false)
    private int diasParaConcluir;

    @Column(nullable = false)
    private boolean metaConcluida;

    @ManyToOne
    @JoinColumn(name = "meta_id")
    private Metas metas;
}
