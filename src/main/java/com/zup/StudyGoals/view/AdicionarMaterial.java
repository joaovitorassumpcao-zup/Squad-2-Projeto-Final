package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.Categoria;
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

public class AdicionarMaterial extends JFrame{
    private JTextField titulo;
    private JComboBox categoria;
    private JTextField url;
    private JEditorPane resumo;
    private JTextField dataInicio;
    private JTextField dataConclusao;
    private JPanel adicionarMaterial;
    private JButton adicionarMaterialButton;

    private ApiClient apiClient;
    private ObjectMapper objectMapper;

    CadastrarMeta cadastrarMeta;

    public AdicionarMaterial() {

        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

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

        //ToDo Ao apertar o botão retorna uma exceção AWT-EventQueue-0

        adicionarMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    MaterialDeEstudo materialDeEstudos = new MaterialDeEstudo(titulo.getText(),
                            (Categoria) categoria.getSelectedItem(), url.getText(), resumo.getText(), dataInicio.getText(),
                            dataConclusao.getText()
                    );

                    String jsonBody = objectMapper.writeValueAsString(materialDeEstudos);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
                    ResponseBody resposeBody = apiClient.postRequest(requestBody, "/materiais");

                    cadastrarMeta.materialDeEstudos.add(materialDeEstudos);

                    JOptionPane.showMessageDialog(null, resposeBody.string(), "Material cadastrado.",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }catch (IOException | NumberFormatException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar material! ", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
