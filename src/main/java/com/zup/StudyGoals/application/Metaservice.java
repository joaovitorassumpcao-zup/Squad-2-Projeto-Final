package com.zup.StudyGoals.application;

import com.zup.StudyGoals.data.MetaRepository;
import com.zup.StudyGoals.domain.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Metaservice {

    @Autowired
    MetaRepository metasRepository;

    // método para listar todas as metas
    public List<Meta> listarMetas(){
        return metasRepository.findAll();
    }

    // método para buscar meta por id
    public Optional<Meta> buscarMetaPorId(Long id){
        // ajustar método quando DTOs estiverem finalizados
        Optional<Meta> meta = metasRepository.findById(id);
        if(meta.isEmpty()) return Optional.empty();

        return Optional.of(meta).get();
    }

    // método para inserir uma nova meta
    public Meta cadastrarMeta(Meta metas){
        // ajustar método quando DTOs estiverem finalizados
        return metasRepository.save(metas);
    }

    // método para editar metas
    public Meta editarMeta(Long id, Meta metas){
        Meta meta = metasRepository.findById(id).get();

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

        return metasRepository.save(meta);
    }

    //método para deletar meta
    public void deletarMeta(Long id){
        metasRepository.deleteById(id);
    }
}
