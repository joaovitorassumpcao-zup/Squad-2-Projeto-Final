package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeletarRelatorio extends JFrame{
    private JPanel deletaRelatorio;
    private JComboBox comboBox1;
    private JButton deletarRelatorioButton;

    private ApiClient apiClient;
    private ObjectMapper objectMapper;

    private Map<String, Relatorio> relatorioMap = new HashMap<>();

    public DeletarRelatorio() {
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        setContentPane(deletaRelatorio);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 150);
        setTitle("Deletar relatório");

        // Buscar todos os relatórios para preencher o combobox
        String response = null;
        try {
            response = apiClient.getRequest("/relatorios");
            List<Relatorio> relatorios = objectMapper
                    .readValue(
                            response,
                            TypeFactory.defaultInstance()
                                    .constructCollectionType(List.class, Relatorio.class)
                    );
            preecherComboBox(relatorios);
        } catch (IOException e) {
            e.printStackTrace();
        }

        deletarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String relatorioSelecionado = (String) comboBox1.getSelectedItem();
                Relatorio relatorioASerDeletado = relatorioMap.get(relatorioSelecionado);
                assert relatorioASerDeletado != null;
                String relatorioId = relatorioASerDeletado.getId().toString();
                try {
                    apiClient.deleteRequest("/relatorios/" + relatorioId);
                    JOptionPane.showMessageDialog(null, "Relatório deletado com sucesso! ", "Relatório deletado",JOptionPane.INFORMATION_MESSAGE);
                }catch (IOException exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao deletar a relatório! ", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private void preecherComboBox(@NotNull List<Relatorio> relatorios) {
        for(Relatorio relatorio : relatorios){
            comboBox1.addItem(relatorio.getHoraRegistro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
            relatorioMap.put(relatorio.getHoraRegistro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), relatorio);
        }
    }
}
