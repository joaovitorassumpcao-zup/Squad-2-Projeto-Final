package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.Meta;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.apache.coyote.Request;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zup.StudyGoals.presentation.apiclient.ApiClient;

public class VerMetas extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;

    ApiClient apiClient;
    private ObjectMapper objectMapper;

    public VerMetas() {

        this.setTitle("Ver todas as metas");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        scrollPane.setBounds(10, 10, 960, 540);

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
            response = apiClient.getRequest("/metas");
            List<Meta> metas = objectMapper.readValue(response,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, Meta.class));

            preencherTabelaComDados(metas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void preencherTabelaComDados(List<Meta> metas) {
        tableModel.addColumn("ID");
        tableModel.addColumn("Assunto");
        tableModel.addColumn("Data de iníco");
        tableModel.addColumn("Data final");
        tableModel.addColumn("Meta de minutos por dia");
        tableModel.addColumn("Objetivo");
        tableModel.addColumn("Materiais de estudo");

        tableModel.setRowCount(0);

        for (Meta meta : metas) {
            Object[] rowData = {meta.getId(),
                    meta.getAssunto(),
                    meta.getDataDeInicio(),
                    meta.getDataFinal(),
                    meta.getMetaMinutosDia(),
                    meta.getObjetivo(),
                    meta.getMateriaisDeEstudo()};

            tableModel.addRow(rowData);
        }
    }
}
