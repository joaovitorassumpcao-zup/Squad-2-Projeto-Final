package com.zup.StudyGoals.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "TB_METAS")
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "metas", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MaterialDeEstudo> materiaisDeEstudo = new ArrayList<>();

    public void adiciona(MaterialDeEstudo materialDeEstudo) {
        this.materiaisDeEstudo.add(materialDeEstudo);
        materialDeEstudo.setMetas(this);
    }
    public List<MaterialDeEstudo> getMateriaisDeEstudo() {
        List<MaterialDeEstudo> listaSegura = Collections.unmodifiableList(this.materiaisDeEstudo);
        return listaSegura;
    }

    public Long getId() {
        return id;
    }

    public String getAssunto() {
        return assunto;
    }

    public LocalDateTime getDataDeInicio() {
        return dataDeInicio;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public int getMetaMinutosDia() {
        return metaMinutosDia;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void setDataDeInicio(LocalDateTime dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setMetaMinutosDia(int metaMinutosDia) {
        this.metaMinutosDia = metaMinutosDia;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void setMateriaisDeEstudo(List<MaterialDeEstudo> materiaisDeEstudo) {
        this.materiaisDeEstudo = materiaisDeEstudo;
    }
}
