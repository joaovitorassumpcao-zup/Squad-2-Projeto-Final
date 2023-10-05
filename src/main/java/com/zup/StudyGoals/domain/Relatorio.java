package com.zup.StudyGoals.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_RELATORIOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Meta metas;
}
