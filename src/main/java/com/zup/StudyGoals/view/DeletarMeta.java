package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class DeletarMeta extends JFrame{
    private JButton deletarMetaButton;
    private ApiClient apiClient;
    private ObjectMapper objectMapper;
    private JPanel deletarMeta;
    private JComboBox metas;

    public DeletarMeta() {
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String response = null;
        try {
            response = apiClient.getRequest("/metas");
            List<Meta> listaTodasAsMetas = objectMapper
                    .readValue(
                            response,
                            TypeFactory.defaultInstance()
                                    .constructCollectionType(List.class, Meta.class)
                    );
            preecherComboBox(listaTodasAsMetas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        deletarMetaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Meta metaASerDeletada = (Meta) metas.getSelectedItem();
                assert metaASerDeletada != null;
                String metaId = metaASerDeletada.getId().toString();
                try {
                    apiClient.deleteRequest("/metas/" + metaId);
                    JOptionPane.showMessageDialog(null, "Meta deletada com sucesso! ", "Meta deletada ",JOptionPane.INFORMATION_MESSAGE);
                }catch (IOException exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao deletar a meta! ", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }

        });



        setContentPane(deletarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 150);
        setTitle("Deletar meta");
        setVisible(true);
    }
    private void preecherComboBox(@NotNull List<Meta> listaMetas) {
        listaMetas.forEach(meta -> metas.addItem(meta.getAssunto()));
    }
}
