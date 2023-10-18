package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

public class AdicionarMaterial extends JFrame{
    private JTextField titulo;
    private JComboBox categoria;
    private JTextField url;
    private JEditorPane resumo;
    private JTextField dataInicio;
    private JTextField dataConclusao;
    private JPanel adicionarMaterial;
    private JButton adicionarMaterialButton;
    private CadastrarMeta cadastrarMeta;
    private ApiClient apiClient;
    private ObjectMapper objectMapper;

    public AdicionarMaterial(CadastrarMeta cadastrarMeta) {
        this.cadastrarMeta = cadastrarMeta;
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

        //LOG PARA VERIFICAR JSON
        System.out.println("Titulo: " + tituloMaterial);
        System.out.println("Categoria: " + categoriaMaterial);
        System.out.println("URL" + url);
        System.out.println("Resumo: " + resumoMaterial);
        System.out.println("Inicio: " + inicioMaterial);
        System.out.println("Conclusão:" +conclusaoMaterial);

        MaterialDeEstudo materialDeEstudo = new MaterialDeEstudo(tituloMaterial,categoriaMaterial,urlMaterial,resumoMaterial, inicioMaterial,conclusaoMaterial);

        cadastrarMeta.adicionarMaterial(materialDeEstudo);

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
