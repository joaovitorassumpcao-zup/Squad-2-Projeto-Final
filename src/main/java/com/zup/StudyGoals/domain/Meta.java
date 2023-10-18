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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialDeEstudo> materiaisDeEstudo = new ArrayList<>();

    public Meta (String assunto, String dataInicio, String dataFinal, int metaMinutosDia, String objetivo,
                 List<MaterialDeEstudo> materialDeEstudos){
        this.assunto = assunto;
        this.dataDeInicio = LocalDateTime.parse(dataInicio);
        this.dataFinal = LocalDateTime.parse(dataFinal);
        this.metaMinutosDia = metaMinutosDia;
        this.objetivo = objetivo;
        this.materiaisDeEstudo = materialDeEstudos;
    }

    public void adiciona(MaterialDeEstudo materialDeEstudo) {
        this.materiaisDeEstudo.add(materialDeEstudo);
    }

    public void atualizarMateriaisDeEstudo(List<MaterialDeEstudo> novosMateriais) {
        this.materiaisDeEstudo.clear();
        this.materiaisDeEstudo.addAll(novosMateriais);
    }

    public List<MaterialDeEstudo> getMateriaisDeEstudo() {
        return this.materiaisDeEstudo;
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

    public void setMateriaisDeEstudo(List<MaterialDeEstudo> novosMateriais) {
        this.materiaisDeEstudo.clear();
        if (novosMateriais != null) {
            this.materiaisDeEstudo.addAll(novosMateriais);
        }
    }

}
