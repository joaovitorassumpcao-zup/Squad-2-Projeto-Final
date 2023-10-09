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

    //O post deve ser feito usando o JSON gerado pelo método relatorioTemporario caso o usuário deseje salvar o relatório no banco de dados
    @PostMapping
    public ResponseEntity<?> cadastrarRelatorio(@RequestBody RelatorioDTO relatorioDTO) {
        relatorioService.cadastrarRelatorio(relatorioDTO);
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

    //Método que gera um relatório temporário para a visualização dos status da meta
    @GetMapping("/relatoriotemp/{id} ")
    public ResponseEntity<RelatorioDTO> relatorioTemporario(@PathVariable Long id) {
        double tempoTotal = relatorioService.calcularTempoTotalDedicado(id);
        double mediaTempo = relatorioService.calcularMediaTempoDiaria(id);
        int totalResumos = relatorioService.calcularResumosFeitos(id);
        String categoriaMaisConsumida = relatorioService.calcularCategoriasMaisConsumidas(id);
        int diasParaConcluir = relatorioService.calcularDiasParaMeta(id);
        boolean metaConcluida = relatorioService.metaFoiConcluida(id);

        RelatorioDTO relatorioDTO = new RelatorioDTO(tempoTotal, mediaTempo, totalResumos, categoriaMaisConsumida,
                diasParaConcluir, metaConcluida);

        return ResponseEntity.ok(relatorioDTO);

    }

}
