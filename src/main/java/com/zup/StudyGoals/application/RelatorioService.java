package com.zup.StudyGoals.application;

import com.zup.StudyGoals.application.mapper.RelatorioDTOMapper;
import com.zup.StudyGoals.data.RelatorioRepository;
import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.dto.MetaDTO;
import com.zup.StudyGoals.dto.RelatorioDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    RelatorioRepository relatorioRepository;

    public List<RelatorioDTO> listarRelatorios() {
        return relatorioRepository
                .findAll()
                .stream()
                .map(RelatorioDTOMapper.INSTANCE::relatorioParaDTO)
                .collect(Collectors.toList());
    }

    public Optional<RelatorioDTO> buscarRelatorioPorId(Long id) {
        return relatorioRepository.findById(id)
                .map(RelatorioDTOMapper.INSTANCE::relatorioParaDTO);
    }

    public RelatorioDTO cadastrarRelatorio(RelatorioDTO relatorioDTO) {
        //Optional<Relatorio> relatorioOptional = relatorioRepository
        //        .findById(relatorioDTO.getId());
        //if (relatorioOptional.isPresent()) return new Error();
        //else {
            relatorioRepository
                    .save(RelatorioDTOMapper.INSTANCE.DTOParaRelatorio(relatorioDTO));
            return relatorioDTO;
        //}
    }

    public Optional<RelatorioDTO> alterarRelatorio(Long id, RelatorioDTO relatorioDTO) {
        Optional<Relatorio> relatorioOptional = relatorioRepository.findById(id);

        if (relatorioOptional.isPresent()) {
            Relatorio relatorio = relatorioOptional.get();

            BeanUtils.copyProperties(relatorioDTO, relatorio, "id");
            relatorioRepository.save(relatorio);

            return Optional.of(RelatorioDTOMapper.INSTANCE.relatorioParaDTO(relatorio));
        }
        else return Optional.empty();
    }

    public void deletarRelatorio(Long id) {
        relatorioRepository.deleteById(id);
    }

    public Boolean metaFoiConcluida(MetaDTO metaDTO) {
        if (metaDTO.getDataFinal().isBefore(LocalDateTime.now())) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    public Long calcularDiasParaMeta(MetaDTO metaDTO) {
        Duration duracao = Duration
                .between(LocalDateTime.now(), metaDTO.getDataFinal());
        return duracao.toDays();
    }
}
