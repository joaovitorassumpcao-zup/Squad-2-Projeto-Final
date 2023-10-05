package com.zup.StudyGoals.application;

import com.zup.StudyGoals.data.MaterialDeEstudoRepository;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDeEstudoService {

    //ToDo atualizar com os DTOs

    @Autowired
    MaterialDeEstudoRepository materiaisDeEstudoRepository;

    //Listar Materiais
    public List<MaterialDeEstudo> listarMateriais() {
        return materiaisDeEstudoRepository.findAll();
    }

    //Buscar Material Por Id
    public Optional<MaterialDeEstudo> buscarMaterialPorId(Long id) {
        Optional<MaterialDeEstudo> materiaisDeEstudo = materiaisDeEstudoRepository.findById(id);
        return materiaisDeEstudo;
    }

    //Cadastrar Material
    public MaterialDeEstudo cadastrarMaterial(MaterialDeEstudo materiaisDeEstudo) {
        return materiaisDeEstudoRepository.save(materiaisDeEstudo);
    }

    //Alterar Material
    public MaterialDeEstudo alterarMaterial (Long id, MaterialDeEstudo materiaisDeEstudo) {
        MaterialDeEstudo materiaisDeEstudo1 = materiaisDeEstudoRepository.findById(id).get();

        if (materiaisDeEstudo.getTitulo() != null) {
            materiaisDeEstudo1.setTitulo(materiaisDeEstudo.getTitulo());
        }

        if (materiaisDeEstudo.getCategoria() != null) {
            materiaisDeEstudo1.setCategoria(materiaisDeEstudo.getCategoria());
        }

        if (materiaisDeEstudo.getUrl() != null) {
            materiaisDeEstudo1.setUrl(materiaisDeEstudo.getUrl());
        }

        if (materiaisDeEstudo.getResumo() != null) {
            materiaisDeEstudo1.setResumo(materiaisDeEstudo.getResumo());
        }

        if (materiaisDeEstudo.getDataInicio() != null) {
            materiaisDeEstudo1.setDataInicio(materiaisDeEstudo.getDataInicio());
        }

        if (materiaisDeEstudo.getDataConclusao() != null) {
            materiaisDeEstudo1.setDataConclusao(materiaisDeEstudo.getDataConclusao());
        }

        if (materiaisDeEstudo.getMetas() != null) {
            materiaisDeEstudo1.setMetas(materiaisDeEstudo.getMetas());
        }

        return materiaisDeEstudoRepository.save(materiaisDeEstudo1);

    }

    //Deletar Material
    public void deletarMaterial(Long id) {
        materiaisDeEstudoRepository.deleteById(id);
    }


}
