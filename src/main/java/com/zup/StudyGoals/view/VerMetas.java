package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
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

    ApiClient apiClient = new ApiClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public VerMetas() {

        this.setTitle("Ver todas as metas");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        tableModel = new DefaultTableModel();

        try {
            fazerRequisicaoGET();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        scrollPane.setBounds(10, 10, 760, 540);

        this.add(scrollPane);

        this.setVisible(true);
    }

    private void fazerRequisicaoGET() throws IOException {
        ResponseBody resposta = apiClient.getRequest("/metas");
        List<Meta> metas = objectMapper.readValue(resposta.string(),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Meta.class));

        preencherTabelaComDados(metas);
    }

    private void preencherTabelaComDados(List<Meta> metas) {
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
