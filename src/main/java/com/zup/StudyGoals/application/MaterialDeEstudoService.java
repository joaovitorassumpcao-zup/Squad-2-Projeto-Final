package com.zup.StudyGoals.application;

import com.zup.StudyGoals.application.mapper.MaterialDeEstudoDTOMapper;
import com.zup.StudyGoals.data.MaterialDeEstudoRepository;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.dto.MaterialDeEstudoDTO;
import com.zup.StudyGoals.dto.MetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialDeEstudoService {

    @Autowired
    MaterialDeEstudoRepository materialDeEstudoRepository;

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

    //Cadastrar Material
    public MaterialDeEstudoDTO cadastrarMaterial(MaterialDeEstudoDTO materialDeEstudoDTO) {
        MaterialDeEstudo novoMaterial = MaterialDeEstudoDTOMapper.INSTANCE.DTOParaMaterialDeEstudo(materialDeEstudoDTO);
        materialDeEstudoRepository.save(novoMaterial);
        return new MaterialDeEstudoDTO(novoMaterial);
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

        if (materiaisDeEstudo.getMetas() != null) {
            materialDeEstudo1.setMetas(materiaisDeEstudo.getMetas());
        }

        return materialDeEstudoRepository.save(materialDeEstudo1);

    }

    //Deletar Material
    public void deletarMaterial(Long id) {
        materialDeEstudoRepository.deleteById(id);
    }


}
