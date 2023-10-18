package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CadastrarMeta extends JFrame{
    private JTextField assunto;
    private JTextField dataInicio;
    private JTextField dataFinal;
    private JTextField minutos;
    private JTextField objetivo;
    private JButton salvarMetaButton;
    private JPanel cadastrarMeta;
    private JButton adicionarMaterialButton;
    private ApiClient apiClient;
    private ObjectMapper objectMapper;

    public List<MaterialDeEstudo> materiaisDeEstudo;

    public CadastrarMeta() {
        this.materiaisDeEstudo = new ArrayList<>();
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        salvarMetaButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meta meta = new Meta();
                meta.setAssunto(assunto.getText());
                meta.setDataDeInicio(LocalDateTime.parse(dataInicio.getText()));
                meta.setDataFinal(LocalDateTime.parse(dataFinal.getText()));
                meta.setMetaMinutosDia(Integer.parseInt(minutos.getText()));
                meta.setMateriaisDeEstudo(materiaisDeEstudo);
                meta.setObjetivo(objetivo.getText());

                try{
                    System.out.println("META ANTES DE SERIALIZAR: " + meta);
                    String jsonBody = objectMapper.writeValueAsString(meta);
                    System.out.println("META DEPOIS DE SERIALIZAR: " + jsonBody);
                    System.out.println("JSON A SER ENVIADO: " + jsonBody);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
                    apiClient.postRequest(requestBody, "/metas");
                    JOptionPane.showMessageDialog(null,"Meta cadastrada com sucesso! ");
                }catch (IOException | NumberFormatException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar nova meta! ", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        adicionarMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdicionarMaterial adicionarMaterial = new AdicionarMaterial(CadastrarMeta.this);
                adicionarMaterial.setVisible(true);
            }
        });

        setContentPane(cadastrarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setTitle("Cadastrar meta");
        setVisible(true);
    }

    public void adicionarMaterial (MaterialDeEstudo materialDeEstudo){

        if(materialDeEstudo != null) {
            this.materiaisDeEstudo.add(materialDeEstudo);
            System.out.println("Material adicionado: " + materialDeEstudo);
        }else {
            System.out.println("MATERIAL NULO");
        }
    }

}
