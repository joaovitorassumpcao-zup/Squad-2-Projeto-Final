package com.zup.StudyGoals.application;

import com.zup.StudyGoals.data.RelatorioRepository;
import com.zup.StudyGoals.domain.Relatorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    RelatorioRepository relatorioRepository;

    public List<Relatorio> listarRelatorios() {
        return relatorioRepository.findAll();
    }

    public Optional<Relatorio> buscarRelatorioPorId(Long id) {
        return relatorioRepository.findById(id);
    }

    public Relatorio cadastrarRelatorio(Relatorio relatorio) throws Exception {
        Optional<Relatorio> relatorioOptional = relatorioRepository
                .findById(relatorio.getId());
        if (relatorioOptional.isPresent()) throw new Exception("Relatório já existe!");
        else return relatorioRepository.save(relatorio);
    }

    public Relatorio editarRelatorio(Long id, Relatorio relatorioAlterado) throws Exception {
        Optional<Relatorio> relatorioOptional = relatorioRepository.findById(id);

        if (relatorioOptional.isPresent()){
            Relatorio relatorio = relatorioOptional.get();

            BeanUtils.copyProperties(relatorioAlterado, relatorio, "id");

            return relatorioRepository.save(relatorio);
        }
        else throw new Exception("Relatório não existe!");
    }

    public void excluirRelatorioPorId(Long id) {
        relatorioRepository.deleteById(id);
    }
}
