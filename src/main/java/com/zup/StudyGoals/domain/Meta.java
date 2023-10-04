package com.zup.StudyGoals.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_METAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String assunto;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime dataDeInicio;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime dataFinal;

    @Column(nullable = false)
    private int metaMinutosDia;

    @Column(nullable = false)
    private String objetivo;

    @OneToMany (mappedBy = "metas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialDeEstudo> materiaisDeEstudo = new ArrayList<>();


}
