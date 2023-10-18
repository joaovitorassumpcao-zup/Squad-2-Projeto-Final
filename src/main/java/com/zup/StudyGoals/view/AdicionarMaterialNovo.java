package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdicionarMaterialNovo extends JFrame {
    private JPanel adicionarMaterial;
    private JButton adicionarMaterialButton;
    private JTextField titulo;
    private JComboBox categoria;
    private JTextField url;
    private JEditorPane resumo;
    private JTextField dataInicio;
    private JTextField dataConclusao;
    private EditarMeta editarMeta;
    private ApiClient apiClient;
    private ObjectMapper objectMapper;

    public AdicionarMaterialNovo(EditarMeta editarMeta) {
        this.editarMeta = editarMeta;
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        objectMapper.registerModule(module);


        setContentPane(adicionarMaterial);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);
        setTitle("Adicionar material");
        setVisible(true);


        categoria.addItem(Categoria.ARTIGO);
        categoria.addItem(Categoria.VIDEO);
        categoria.addItem(Categoria.LIVRO);
        categoria.addItem(Categoria.WORKSHOP);
        categoria.addItem(Categoria.AUDIO);

        adicionarMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaterialDeEstudo materialDeEstudo = adicionarMaterialDeEstudo();
                try{
                    String jsonBody = objectMapper.writeValueAsString(materialDeEstudo);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
                    apiClient.postRequest(requestBody, "/materiais");
                    dispose();
                }catch (IOException | NumberFormatException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar material! ", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private MaterialDeEstudo adicionarMaterialDeEstudo(){
        String tituloMaterial = titulo.getText();
        Categoria categoriaMaterial = (Categoria) categoria.getSelectedItem();
        String urlMaterial = url.getText();
        String resumoMaterial = resumo.getText();
        String inicioMaterial = dataInicio.getText();
        String conclusaoMaterial = dataConclusao.getText();

        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(tituloMaterial,categoriaMaterial,urlMaterial,resumoMaterial, inicioMaterial,conclusaoMaterial);

        editarMeta.adicionarMaterial(materialDeEstudo);

        titulo.setText("");
        categoria.setSelectedIndex(1);
        url.setText("");
        resumo.setText("");
        dataInicio.setText("");
        dataConclusao.setText("");

        JOptionPane.showMessageDialog(this,"Material de estudo cadastrado com sucesso! ");

        return materialDeEstudo;
    }

}

