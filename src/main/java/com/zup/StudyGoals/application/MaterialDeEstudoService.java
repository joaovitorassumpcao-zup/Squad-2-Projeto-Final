package com.zup.StudyGoals.application;

import com.zup.StudyGoals.application.mapper.MaterialDeEstudoDTOMapper;
import com.zup.StudyGoals.data.MaterialDeEstudoRepository;
import com.zup.StudyGoals.data.MetaRepository;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MaterialDeEstudoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialDeEstudoService {

    @Autowired
    MaterialDeEstudoRepository materialDeEstudoRepository;

    @Autowired
    MetaRepository metaRepository;

    //Listar Materiais
    public List<MaterialDeEstudoDTO> listarMateriais() {
        List<MaterialDeEstudo> materiais = materialDeEstudoRepository.findAll();
        List<MaterialDeEstudoDTO> materiaisDTO = new ArrayList<>();

        for (MaterialDeEstudo material : materiais) {
            materiaisDTO.add(new MaterialDeEstudoDTO(material));
        }

        return materiaisDTO;
    }

    //Buscar Material Por Id
    public Optional<MaterialDeEstudoDTO> buscarMaterialPorId(Long id) {

        Optional<MaterialDeEstudo> materialDeEstudo = materialDeEstudoRepository.findById(id);
        if(materialDeEstudo.isPresent()) return Optional.of(new MaterialDeEstudoDTO(materialDeEstudo.get()));

        return Optional.empty();
    }

    public Meta adiconarMetaParaMaterial (Long idMeta) {
        if (idMeta != null) {
            Optional<Meta> meta = metaRepository.findById(idMeta);

            if (meta.isPresent()) {
                return meta.get();
            }
        }
        throw new IllegalArgumentException();
    }

    //Cadastrar Material
    //Cadastrar Material
    @Transactional
    public void cadastrarMaterial(MaterialDeEstudo materialDeEstudo, Long idMeta) {

        Meta meta = adiconarMetaParaMaterial(idMeta);
        materialDeEstudo.setMetas(meta);

        materialDeEstudoRepository.save(materialDeEstudo);
    }

    //Alterar Material
    public MaterialDeEstudo alterarMaterial (Long id, MaterialDeEstudo materiaisDeEstudo) {
        MaterialDeEstudo materialDeEstudo1 = materialDeEstudoRepository.findById(id).get();

        if (materiaisDeEstudo.getTitulo() != null) {
            materialDeEstudo1.setTitulo(materiaisDeEstudo.getTitulo());
        }

        if (materiaisDeEstudo.getCategoria() != null) {
            materialDeEstudo1.setCategoria(materiaisDeEstudo.getCategoria());
        }

        if (materiaisDeEstudo.getUrl() != null) {
            materialDeEstudo1.setUrl(materiaisDeEstudo.getUrl());
        }

        if (materiaisDeEstudo.getResumo() != null) {
            materialDeEstudo1.setResumo(materiaisDeEstudo.getResumo());
        }

        if (materiaisDeEstudo.getDataInicio() != null) {
            materialDeEstudo1.setDataInicio(materiaisDeEstudo.getDataInicio());
        }

        if (materiaisDeEstudo.getDataConclusao() != null) {
            materialDeEstudo1.setDataConclusao(materiaisDeEstudo.getDataConclusao());
        }

        return materialDeEstudoRepository.save(materialDeEstudo1);

    }

    //Deletar Material
    public void deletarMaterial(Long id) {
        materialDeEstudoRepository.deleteById(id);
    }


}
