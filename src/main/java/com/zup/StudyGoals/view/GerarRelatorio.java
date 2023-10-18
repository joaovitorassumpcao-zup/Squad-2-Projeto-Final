package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.domain.Relatorio;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;
import okhttp3.ResponseBody;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.validation.constraints.NotNull;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerarRelatorio extends JFrame{
    private JPanel relatorio;
    private JComboBox metasOpcao;
    private JButton gerarRelatorioButton;

    private Map<String, Meta> assuntoMap = new HashMap<>();

    private ApiClient apiClient;
    private ObjectMapper objectMapper;

    public GerarRelatorio() {

        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        setContentPane(relatorio);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 100);
        setTitle("Relatório");

        //Busca as metas para o comboBox
        String response = null;
        try {
            response = apiClient.getRequest("/metas");
            List<Meta> metas = objectMapper.readValue(
                    response, TypeFactory.defaultInstance()
                            .constructCollectionType(List.class, Meta.class)
            );
            preencherComboBox(metas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ToDo exceção AWT-EventQueue-0
        gerarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String assunto = (String) metasOpcao.getSelectedItem();
                Meta metaRelatorio = assuntoMap.get(assunto);
                assert metaRelatorio != null;
                String idMeta = metaRelatorio.getId().toString();
                try {
                    ResponseBody responseBody = apiClient.postRequest("/relatorios", "idMeta", idMeta.toString());

                    VerRelatorioGerado verRelatorioGerado = new VerRelatorioGerado();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }

    private void preencherComboBox(@NotNull List<Meta> metas) {
        for(Meta meta : metas) {
            metasOpcao.addItem(meta.getAssunto());
            assuntoMap.put(meta.getAssunto(), meta);
        }
    }
}
