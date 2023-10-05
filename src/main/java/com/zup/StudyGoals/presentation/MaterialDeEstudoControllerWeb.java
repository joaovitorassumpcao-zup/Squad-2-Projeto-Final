package com.zup.StudyGoals.presentation;

import com.zup.StudyGoals.application.MaterialDeEstudoService;
import com.zup.StudyGoals.application.mapper.MaterialDeEstudoDTOMapper;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.dto.MaterialDeEstudoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/api/materiais")
public class MaterialDeEstudoControllerWeb {

    @Autowired
    MaterialDeEstudoService materialDeEstudoService;

    //ENDPOINTS GET
    //get all
    @GetMapping
    public List<MaterialDeEstudoDTO> listarTodosOsMateriais(){
        return materialDeEstudoService.listarMateriais();
    }

    // TODO
    // get by id

    //ENDPOINT POST
    @PostMapping
    public ResponseEntity<?> criarMaterial (@RequestBody MaterialDeEstudoDTO materialDeEstudoDTO){

        MaterialDeEstudo novoMaterial = MaterialDeEstudoDTOMapper.INSTANCE.DTOParaMaterialDeEstudo(materialDeEstudoDTO);
        MaterialDeEstudoDTO novoMaterialDTO = materialDeEstudoService.cadastrarMaterial(new MaterialDeEstudoDTO(novoMaterial));

        return ResponseEntity.ok().body("Novo material de estudo cadastrado com sucesso! " + novoMaterialDTO);

    }

    // TODO
    //ENDPOINT PUT

    //TODO
    //ENDPOINT DELETE

}
