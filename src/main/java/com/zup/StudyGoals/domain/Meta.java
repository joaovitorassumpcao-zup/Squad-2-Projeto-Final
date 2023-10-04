package com.zup.StudyGoals.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_METAS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String assunto;

    @Column(nullable = false)
    private LocalDateTime dataDeInicio;

    @Column(nullable = false)
    private LocalDateTime dataFinal;

    @Column(nullable = false)
    private int metaMinutosDia;

    @Column(nullable = false)
    private String objetivo;

    @OneToMany (mappedBy = "metas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialDeEstudo> materiaisDeEstudo = new ArrayList<>();


}
