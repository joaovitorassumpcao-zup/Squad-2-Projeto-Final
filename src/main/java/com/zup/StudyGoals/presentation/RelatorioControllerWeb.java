package com.zup.StudyGoals.presentation;

import com.zup.StudyGoals.application.MetaService;
import com.zup.StudyGoals.application.RelatorioService;
import com.zup.StudyGoals.dto.MetaDTO;
import com.zup.StudyGoals.dto.RelatorioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/relatorios")
public class RelatorioControllerWeb {

    @Autowired
    RelatorioService relatorioService;

    @Autowired
    MetaService metaService;

    @GetMapping
    public ResponseEntity<List<RelatorioDTO>> listarRelatorios() {
        return ResponseEntity.ok(relatorioService.listarRelatorios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRelatorioPorId(@PathVariable Long id) {
        Optional<RelatorioDTO> relatorioDTOOptional =
                relatorioService.buscarRelatorioPorId(id);

        if (relatorioDTOOptional.isPresent()) return
                ResponseEntity.ok(relatorioDTOOptional.get());

        else return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Relatorio não encontrado.");
    }

    @PostMapping
    public ResponseEntity<?> cadastrarRelatorio(@PathVariable Long idMeta, @RequestBody RelatorioDTO relatorioDTO) {
        relatorioService.cadastrarRelatorio(idMeta, relatorioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarRelatorio(@PathVariable Long id, @RequestBody RelatorioDTO relatorioDTO) {
        Optional<RelatorioDTO> optionalRelatorioDTO = relatorioService
                .alterarRelatorio(id, relatorioDTO);
        if (optionalRelatorioDTO.isPresent()) return ResponseEntity.ok(optionalRelatorioDTO.get());
        else return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Relatorio não encontrado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarRelatorio(@PathVariable Long id) {
        relatorioService.deletarRelatorio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/metaconcluida/{id}")
    public ResponseEntity<?> metaFoiConcluida(@PathVariable Long id){
        Optional<MetaDTO> metaDTOOptional =
                metaService.buscarMetaPorId(id);

        if (metaDTOOptional.isPresent()) {

            MetaDTO metaDTO = metaDTOOptional.orElseThrow(() -> new NoSuchElementException("Optional está vazio"));
            return ResponseEntity.ok(relatorioService.metaFoiConcluida(metaDTO));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Meta não encontrada.");
        }
    }

}
