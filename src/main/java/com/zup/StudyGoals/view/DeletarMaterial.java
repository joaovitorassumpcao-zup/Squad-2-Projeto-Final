package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class DeletarMaterial extends JDialog {
    private JPanel contentPane;
    private JButton deletarButton;
    private JComboBox materiaisCombo;
    private JButton buttonOK;
    private ApiClient apiClient;
    private ObjectMapper objectMapper;

    public DeletarMaterial() {
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Buscar todos os materiais para preencher o combobox
        String response = null;
        try {
            response = apiClient.getRequest("/materiais");
            List<MaterialDeEstudo> materiais = objectMapper
                .readValue(
                        response,
                        TypeFactory.defaultInstance()
                                .constructCollectionType(List.class, MaterialDeEstudo.class)
                );
            preecherComboBox(materiais);
        } catch (IOException e) {
            e.printStackTrace();
        }

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String materialId = String.valueOf(materiaisCombo.getSelectedIndex());
                try {
                    apiClient.deleteRequest("/materiais/" + materialId);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        DeletarMaterial dialog = new DeletarMaterial();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void preecherComboBox(@NotNull List<MaterialDeEstudo> materiais) {
        materiais.forEach(material -> materiaisCombo.addItem(material.getTitulo()));
    }

}
