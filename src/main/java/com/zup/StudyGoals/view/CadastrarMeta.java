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

    public List<MaterialDeEstudo> materialDeEstudos = new ArrayList<>();

    public CadastrarMeta() {
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        salvarMetaButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Meta meta = new Meta(assunto.getText(),dataInicio.getText(),dataFinal.getText(),Integer.parseInt(minutos.getText()),
                            objetivo.getText(), materialDeEstudos
                    );

                    String jsonBody = objectMapper.writeValueAsString(meta);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
                    ResponseBody resposeBody = apiClient.postRequest(requestBody, "/metas");

                    JOptionPane.showMessageDialog(null, resposeBody.string(), "Meta cadastrada.",JOptionPane.INFORMATION_MESSAGE);
                }catch (IOException | NumberFormatException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar nova meta! ", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        adicionarMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdicionarMaterial adicionarMaterial = new AdicionarMaterial();
            }
        });

        setContentPane(cadastrarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setTitle("Cadastrar meta");
        setVisible(true);
    }
}
