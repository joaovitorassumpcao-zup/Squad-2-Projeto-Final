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
import java.util.List;

public class GerarRelatorio extends JFrame{
    private JPanel relatorio;
    private JComboBox metasOpcao;
    private JButton gerarRelatorioButton;
    private JTable relatorioTable;

    private DefaultTableModel tableModel;

    private ApiClient apiClient;
    private ObjectMapper objectMapper;

    public GerarRelatorio() {

        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        tableModel = new DefaultTableModel();
        relatorioTable = new JTable(tableModel);

        setContentPane(relatorio);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
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
                String response1 = null;

                Meta meta = (Meta) metasOpcao.getSelectedItem();
                assert meta != null;
                String idMeta = meta.getId().toString();
                try {
                    ResponseBody responseBody = apiClient.postRequest(null, "/relatorios?idMeta=" + idMeta);

                    response1 = apiClient.getRequest("/relatorios");
                    List<Relatorio> relatorios = objectMapper.readValue(response1,
                            TypeFactory.defaultInstance().constructCollectionType(List.class, Relatorio.class));

                    tableModel.addColumn("ID");
                    tableModel.addColumn("ID da Meta");
                    tableModel.addColumn("Hora do Registro");
                    tableModel.addColumn("Tempo total (minutos)");
                    tableModel.addColumn("Média de tempo (minutos)");
                    tableModel.addColumn("Total de resumos");
                    tableModel.addColumn("Categoria mais consumida");
                    tableModel.addColumn("Dias para concluir");
                    tableModel.addColumn("Meta foi concluída?");


                    for (int i = 0; i > relatorios.size(); i++) {
                        if (relatorios.get(i).getId() == i + 1) {
                            Object[] rowData = {relatorios.get(i).getId(),
                                    relatorios.get(i).getMetaId(),
                                    relatorios.get(i).getHoraRegistro(),
                                    relatorios.get(i).getTempoTotal(),
                                    relatorios.get(i).getMediaTempo(),
                                    relatorios.get(i).getTotalResumos(),
                                    relatorios.get(i).getCategoriaMaisConsumida(),
                                    relatorios.get(i).getDiasParaConcluir(),
                                    relatorios.get(i).isMetaConcluida()};

                            tableModel.addRow(rowData);
                        }
                    }

                    tableModel.fireTableDataChanged();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }

    private void preencherComboBox(@NotNull List<Meta> metas) {
        metas.forEach(meta -> metasOpcao.addItem(meta.getId() + " - " + meta.getAssunto()));
    }
}
