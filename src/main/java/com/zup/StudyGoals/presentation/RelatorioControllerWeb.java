package com.zup.StudyGoals.presentation;

import com.zup.StudyGoals.application.RelatorioService;
import com.zup.StudyGoals.dto.RelatorioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/relatorios")
public class RelatorioControllerWeb {

    @Autowired
    RelatorioService relatorioService;

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
    public ResponseEntity<?> cadastrarRelatorio(RelatorioDTO relatorioDTO) {
        relatorioService.cadastrarRelatorio(relatorioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarRelatorio(@PathVariable Long id, RelatorioDTO relatorioDTO) {
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

}
