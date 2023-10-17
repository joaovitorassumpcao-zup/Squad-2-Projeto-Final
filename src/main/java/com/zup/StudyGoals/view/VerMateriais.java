package com.zup.StudyGoals.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class VerMateriais extends JFrame{

    private DefaultTableModel tableModel;
    private JTable table;

    ApiClient apiClient;
    private ObjectMapper objectMapper;

    public VerMateriais() {

        this.setTitle("Ver todas os materiais");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        scrollPane.setBounds(10, 10, 760, 540);

        this.add(scrollPane);

        try {
            fazerRequisicaoGET();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        tableModel.fireTableDataChanged();

        this.setVisible(true);
    }

    private void fazerRequisicaoGET() throws IOException {
        String response = null;
        try {
            response = apiClient.getRequest("/materiais");
            List<MaterialDeEstudo> materialDeEstudos = objectMapper.readValue(response,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, MaterialDeEstudo.class));

            preencherTabelaComDados(materialDeEstudos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void preencherTabelaComDados(List<MaterialDeEstudo> materialDeEstudos) {
        tableModel.addColumn("ID");
        tableModel.addColumn("Título");
        tableModel.addColumn("Categoria");
        tableModel.addColumn("URL");
        tableModel.addColumn("Resumo");
        tableModel.addColumn("Data de início");
        tableModel.addColumn("Data de conclusão");

        tableModel.setRowCount(0);

        for (MaterialDeEstudo materialDeEstudo : materialDeEstudos) {
            Object[] rowData = {
                    materialDeEstudo.getId(),
                    materialDeEstudo.getTitulo(),
                    materialDeEstudo.getCategoria(),
                    materialDeEstudo.getUrl(),
                    materialDeEstudo.getResumo(),
                    materialDeEstudo.getDataInicio(),
                    materialDeEstudo.getDataConclusao()
            };

            tableModel.addRow(rowData);
        }
    }
}
