package com.zup.StudyGoals.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private JComboBox metasOpcao;
    private JButton verMateriaisButton;
    private JPanel verMateriais;
    private JTable materiais;
    private DefaultTableModel tableModel;

    ApiClient apiClient;
    private ObjectMapper objectMapper;

    String opcaoSelecionada;

    public VerMateriais() {
        setContentPane(verMateriais);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setTitle("Ver materiais");

        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        tableModel = new DefaultTableModel();
        materiais = new JTable(tableModel);

        try {
            fazerRequisicaoGET();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        verMateriaisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcaoSelecionada = (String) metasOpcao.getSelectedItem();
                try {
                    fazerRequisicaoGETEspecifica();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                tableModel.fireTableDataChanged();
            }
        });

        setVisible(true);
    }

    private void fazerRequisicaoGET() throws IOException {
        String response = null;
        try {
            response = apiClient.getRequest("/metas");
            List<Meta> metas = objectMapper.readValue(response,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, Meta.class));

            preencherOpcoes(metas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void preencherOpcoes(List<Meta> metas) {
        for (Meta meta : metas) {
            metasOpcao.addItem(meta.getId());
        }
    }

    private void fazerRequisicaoGETEspecifica() throws IOException {
        String response = null;
        try {
            response = apiClient.getRequest("/metas/{" + opcaoSelecionada +"}");
            List<Meta> metas = objectMapper.readValue(response,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, Meta.class));

            preencherTabelaComDados(metas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void preencherTabelaComDados(List<Meta> metas) {
        tableModel.addColumn("ID");
        tableModel.addColumn("Título");
        tableModel.addColumn("Categoria");
        tableModel.addColumn("url");
        tableModel.addColumn("Resumo");
        tableModel.addColumn("Data de início");
        tableModel.addColumn("Data de conclusao");

        tableModel.setRowCount(0);

        for (Meta meta : metas) {
            Object[] rowData = {
                    meta.getMateriaisDeEstudo()
            };

            tableModel.addRow(rowData);
        }
    }


}
