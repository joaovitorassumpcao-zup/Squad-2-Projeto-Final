package com.zup.StudyGoals.presentation;

import com.zup.StudyGoals.application.MetaService;
import com.zup.StudyGoals.application.RelatorioService;
import com.zup.StudyGoals.domain.Relatorio;
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
    public List<RelatorioDTO> listarRelatorios() {
        return relatorioService.listarRelatorios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRelatorioPorId(@PathVariable Long id) {
        Optional<RelatorioDTO> relatorioDTOOptional =
                relatorioService.buscarRelatorioPorId(id);

        if (relatorioDTOOptional.isPresent()) {
            return
                    ResponseEntity.ok(relatorioDTOOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Relatório não encontrado.");
        }
    }

    //Gera o relatório atual da meta selecionada ( /api/relatorios?id=1 ) e salva no banco de dados
    @PostMapping
    public ResponseEntity<?> cadastrarRelatorio(@RequestParam Long id) {

        double tempoTotal = relatorioService.calcularTempoTotalDedicado(id);
        double mediaTempo = relatorioService.calcularMediaTempoDiaria(id);
        int totalResumos = relatorioService.calcularResumosFeitos(id);
        String categoriaMaisConsumida = relatorioService.calcularCategoriasMaisConsumidas(id);
        int diasParaConcluir = relatorioService.calcularDiasParaMeta(id);
        boolean metaConcluida = relatorioService.metaFoiConcluida(id);

        Relatorio novoRelatorio = new Relatorio();

        novoRelatorio.setTempoTotal(tempoTotal);
        novoRelatorio.setMediaTempo(mediaTempo);
        novoRelatorio.setTotalResumos(totalResumos);
        novoRelatorio.setCategoriaMaisConsumida(categoriaMaisConsumida);
        novoRelatorio.setDiasParaConcluir(diasParaConcluir);
        novoRelatorio.setMetaConcluida(metaConcluida);
        novoRelatorio.setMetaId(id);


        relatorioService.cadastrarRelatorio(novoRelatorio);

        RelatorioDTO relatorioDTO = new RelatorioDTO(tempoTotal, mediaTempo, totalResumos, categoriaMaisConsumida,
                diasParaConcluir, metaConcluida);

        return ResponseEntity.ok(relatorioDTO);
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

}
