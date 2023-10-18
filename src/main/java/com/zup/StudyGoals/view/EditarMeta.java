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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EditarMeta extends JFrame{
    private JTextField assunto;
    private JTextField dataInicio;
    private JTextField dataFinal;
    private JTextField minutos;
    private JTextField objetivo;
    private JButton atualizarMetaButton;
    private ApiClient apiClient;
    private ObjectMapper objectMapper;
    private JPanel editarMeta;
    private JButton adicionarMaterialButton;
    public List<MaterialDeEstudo> materiaisDeEstudo;

    private Long idMeta;
    public EditarMeta() {
        this.materiaisDeEstudo = new ArrayList<>();
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        idMeta = pegarIdMeta();
        atualizarMetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meta metaEditada = new Meta();
                metaEditada.setAssunto(assunto.getText());
                metaEditada.setDataDeInicio(LocalDateTime.parse(dataInicio.getText()));
                metaEditada.setDataFinal(LocalDateTime.parse(dataFinal.getText()));
                metaEditada.setMetaMinutosDia(Integer.parseInt(minutos.getText()));
                metaEditada.setMateriaisDeEstudo(materiaisDeEstudo);
                metaEditada.setObjetivo(objetivo.getText());

                try{
                    String jsonBody = objectMapper.writeValueAsString(metaEditada);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonBody);
                    ResponseBody responseBody = apiClient.putRequest(requestBody,"/metas/" + idMeta);
                    JOptionPane.showMessageDialog(null,"Meta cadastrada com sucesso! ");
                }catch (IOException | NumberFormatException exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao editar a meta! ", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        if(adicionarMaterialButton != null) {
            adicionarMaterialButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarMaterial();
                    adicionarMaterialButton.setVisible(true);
                }
            });
        }


        setContentPane(editarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 350);
        setTitle("Editar meta");
        setVisible(true);
    }

    private void adicionarMaterial() {

        JDialog dialog = new JDialog(this, "Adicionar Material de Estudo", true);
        dialog.setLayout(new GridLayout(0, 1));

        JTextField campoTitulo = new JTextField();
        campoTitulo.setBorder(BorderFactory.createTitledBorder("Título: "));
        dialog.add(campoTitulo);

        JComboBox<Categoria> comboBoxCategoria = new JComboBox<>(Categoria.values());
        comboBoxCategoria.setBorder(BorderFactory.createTitledBorder("Categoria: "));
        dialog.add(comboBoxCategoria);

        JTextField campoUrl = new JTextField();
        campoUrl.setBorder(BorderFactory.createTitledBorder("URL: "));
        dialog.add(campoUrl);

        JEditorPane editorPaneResumo = new JEditorPane();
        editorPaneResumo.setBorder(BorderFactory.createTitledBorder("Resumo: "));
        dialog.add(new JScrollPane(editorPaneResumo));

        JTextField dataInicioMaterial = new JTextField();
        dataInicioMaterial.setBorder(BorderFactory.createTitledBorder("URL: "));
        dialog.add(dataInicioMaterial);

        JTextField dataConclusaoMaterial = new JTextField();
        dataConclusaoMaterial.setBorder(BorderFactory.createTitledBorder("URL: "));
        dialog.add(dataConclusaoMaterial);

        JButton salvarButton = new JButton("Salvar Material");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaterialDeEstudo novoMaterial = new MaterialDeEstudo();
                novoMaterial.setTitulo(campoTitulo.getText());
                novoMaterial.setCategoria((Categoria) comboBoxCategoria.getSelectedItem());
                novoMaterial.setUrl(campoUrl.getText());
                novoMaterial.setResumo(editorPaneResumo.getText());
                novoMaterial.setDataInicio(LocalDateTime.parse(dataInicioMaterial.getText()));
                novoMaterial.setDataConclusao(LocalDateTime.parse(dataConclusaoMaterial.getText()));
                materiaisDeEstudo.add(novoMaterial);

                dialog.dispose();
            }
        });
        dialog.add(salvarButton);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    private Long pegarIdMeta(){
        String idMetaString = JOptionPane.showInputDialog("Digite o ID da meta que deseja editar: ");
        return Long.parseLong(idMetaString);
    }

}
