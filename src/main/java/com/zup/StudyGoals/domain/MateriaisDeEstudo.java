package com.zup.StudyGoals.domain;

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
    private Long id_materiais;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String resumo;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataConclusao;

    @ManyToOne
    @JoinColumn(name = "meta_materiais_id")
    private Metas metas;

}
