package com.zup.StudyGoals.application;

import com.zup.StudyGoals.application.mapper.MetaDTOMapper;
import com.zup.StudyGoals.data.MetaRepository;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MetaService {

    @Autowired
    MetaRepository metaRepository;

    // método para listar todas as metas
    public List<MetaDTO> listarMetas(){

        List<Meta> metas = metaRepository.findAll();
        List<MetaDTO> metasDTOS = new ArrayList<>();

        for (Meta meta : metas){
            metasDTOS.add(new MetaDTO(meta));
        }
        return metasDTOS;
    }

    // método para buscar meta por id
    public Optional<MetaDTO> buscarMetaPorId(Long id){
        Optional<Meta> meta = metaRepository.findById(id);

        if(meta.isPresent()) return Optional.of(new MetaDTO(meta.get()));

        return Optional.empty();
    }

    // método para inserir uma nova meta
    public MetaDTO cadastrarMeta(MetaDTO metaDTO){
        Meta novaMeta = MetaDTOMapper.INSTANCE.DTOParaMeta(metaDTO);
        metaRepository.save(novaMeta);
        return new MetaDTO(novaMeta);
    }

    // método para editar metas
    public Meta editarMeta(Long id, Meta metas){
        Meta meta = metaRepository.findById(id).get();

        if(meta != null){
            meta.setAssunto(metas.getAssunto());
        }
        if(meta != null){
            meta.setDataDeInicio(metas.getDataDeInicio());
        }
        if(meta != null){
            meta.setDataFinal(metas.getDataFinal());
        }
        if(meta != null){
            meta.setMetaMinutosDia(metas.getMetaMinutosDia());
        }
        if(meta != null){
            meta.setObjetivo(metas.getObjetivo());
        }

        return metaRepository.save(meta);
    }

    //método para deletar meta
    public void deletarMeta(Long id){
        metaRepository.deleteById(id);
    }
}
