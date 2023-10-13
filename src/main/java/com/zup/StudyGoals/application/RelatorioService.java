package com.zup.StudyGoals.application;

import com.zup.StudyGoals.application.mapper.RelatorioDTOMapper;
import com.zup.StudyGoals.data.MetaRepository;
import com.zup.StudyGoals.data.RelatorioRepository;
import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.dto.MaterialDeEstudoDTO;
import com.zup.StudyGoals.dto.RelatorioDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Service
public class RelatorioService {

    @Autowired
    RelatorioRepository relatorioRepository;

    @Autowired
    MetaRepository metaRepository;

    public List<RelatorioDTO> listarRelatorios() {

        List<Relatorio> relatorios = relatorioRepository.findAll();
        List<RelatorioDTO> relatorioDTOS = new ArrayList<>();

        for (Relatorio relatorio : relatorios) {
            relatorioDTOS.add(new RelatorioDTO(relatorio));
        }

        return relatorioDTOS;
    }

    public Optional<RelatorioDTO> buscarRelatorioPorId(Long id) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(id);

        if(relatorio.isPresent()) {
            return Optional.of(new RelatorioDTO(relatorio.get()));
        }

        return Optional.empty();
    }

    public void cadastrarRelatorio(Relatorio relatorio) {
        relatorioRepository.save(relatorio);
    }

    public void deletarRelatorio(Long id) {
        relatorioRepository.deleteById(id);
    }

    public Boolean metaFoiConcluida(Long idMeta) {

        Optional<Meta> optionalMeta = metaRepository.findById(idMeta);
        Meta meta = optionalMeta.orElseThrow(() -> new NoSuchElementException("Meta não encontrada"));

        if (meta.getDataFinal().isBefore(LocalDateTime.now())) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    public int calcularDiasParaMeta(Long idMeta) {

        Optional<Meta> optionalMeta = metaRepository.findById(idMeta);
        Meta meta = optionalMeta.orElseThrow(() -> new NoSuchElementException("Meta não encontrada"));

        Duration duracao = Duration
                .between(LocalDateTime.now(), meta.getDataFinal());

        int dias = (int) duracao.toDays();

        return dias;
    }

    public String calcularCategoriasMaisConsumidas(Long idMeta) {

        Optional<Meta> optionalMeta = metaRepository.findById(idMeta);
        Meta meta = optionalMeta.orElseThrow(() -> new NoSuchElementException("Meta não encontrada"));

        int artigos = 0;
        int videos = 0;
        int audios = 0;
        int workshops = 0;
        int livros = 0;

        for(MaterialDeEstudo material : meta.getMateriaisDeEstudo()){
            if (material.getCategoria().equals(Categoria.ARTIGO)){
                artigos += 1;
            }
            if (material.getCategoria().equals(Categoria.VIDEO)){
                videos += 1;
            }
            if (material.getCategoria().equals(Categoria.AUDIO)){
                audios += 1;
            }
            if (material.getCategoria().equals(Categoria.WORKSHOP)){
                workshops += 1;
            }
            if (material.getCategoria().equals(Categoria.LIVRO)){
                livros += 1;
            }
        }

        Map<String, Integer> categoriasValores = new HashMap<>();
        categoriasValores.put("ARTIGO", artigos);
        categoriasValores.put("VIDEO", videos);
        categoriasValores.put("AUDIO", audios);
        categoriasValores.put("WORKSHOP", workshops);
        categoriasValores.put("LIVRO", livros);

        List<Map.Entry<String, Integer>> categoriasOrdem = new ArrayList<>(categoriasValores.entrySet());

        categoriasOrdem.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        Map.Entry<String, Integer> primeiroPar = categoriasOrdem.get(0);

        String maisConsumida = primeiroPar.getKey() + " (" + primeiroPar.getValue() + ")";

        return maisConsumida;
    }

    public double calcularTempoTotalDedicado(Long idMeta) {

        Optional<Meta> optionalMeta = metaRepository.findById(idMeta);
        Meta meta = optionalMeta.orElseThrow(() -> new NoSuchElementException("Meta não encontrada"));


        double duracaoMinutos = 0.0;

        for(MaterialDeEstudo material : meta.getMateriaisDeEstudo()){
            Duration duration = Duration.between(material.getDataInicio(), material.getDataConclusao());
            duracaoMinutos += ((double) duration.getSeconds() / 60);
        }

        return duracaoMinutos;
    }

    public double calcularMediaTempoDiaria(Long idMeta) {
        double tempoTotal = calcularTempoTotalDedicado(idMeta);
        if (tempoTotal <= 1440) {
            return tempoTotal;
        }

        int dias = 0;

        while (tempoTotal > 1440) {
            dias += 1;
            tempoTotal -= 1440;
        }

        return tempoTotal / dias;

    }

    public int calcularResumosFeitos(Long idMeta) {

        Optional<Meta> optionalMeta = metaRepository.findById(idMeta);
        Meta meta = optionalMeta.orElseThrow(() -> new NoSuchElementException("Meta não encontrada"));

        return meta.getMateriaisDeEstudo().size();
    }
}
