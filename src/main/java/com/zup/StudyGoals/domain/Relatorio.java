package com.zup.StudyGoals.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime horaRegistro;

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

    @Column(nullable = false)
    private Long metaId;
}
