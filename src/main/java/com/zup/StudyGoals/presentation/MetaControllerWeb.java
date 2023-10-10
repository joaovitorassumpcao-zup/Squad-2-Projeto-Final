package com.zup.StudyGoals.presentation;

import com.zup.StudyGoals.application.MetaService;
import com.zup.StudyGoals.application.mapper.MetaDTOMapper;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.dto.MetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/metas")
public class MetaControllerWeb {

    @Autowired
    MetaService metaService;

    //ENDPOINTS GET
    //get all
    @GetMapping
    public List<MetaDTO> listarTodasAsMetas(){
        return metaService.listarMetas();
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMetaPorId(@PathVariable Long id){

        Optional<MetaDTO> meta = metaService.buscarMetaPorId(id);
        if(meta.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meta não encontrada. ");

        return ResponseEntity.ok().body(meta);
    }

    @PostMapping
    public ResponseEntity<?> adicionarNovaMeta(@RequestBody MetaDTO metaDTO) {

        Meta novaMeta = new Meta();
        novaMeta.setAssunto(metaDTO.getAssunto());
        novaMeta.setDataDeInicio(metaDTO.getDataDeInicio());
        novaMeta.setDataFinal(metaDTO.getDataFinal());
        novaMeta.setMetaMinutosDia(metaDTO.getMetaMinutosDia());
        novaMeta.setObjetivo(metaDTO.getObjetivo());

        List<MaterialDeEstudo> materiaisDeEstudo = metaDTO.getMateriaisDeEstudo();
        metaService.adicionarNovaMetaComMateriais(novaMeta, materiaisDeEstudo);

        return ResponseEntity.ok("Nova meta adicionada com sucesso! ");

    }

    //ENDPOINT PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarMeta (@PathVariable Long id, @RequestBody Meta meta){

        Meta metaAlterada = metaService.editarMeta(id, meta);
        if(meta != null) return ResponseEntity.ok().body("Meta alterada com sucesso! ");

        return ResponseEntity.notFound().build();
    }

    //ENDPOINT DELETE
    @DeleteMapping("/{id}")
    public void deletarMeta (@PathVariable Long id) {
        metaService.deletarMeta(id);
    }

}
