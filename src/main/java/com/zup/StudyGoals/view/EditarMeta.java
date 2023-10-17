package com.zup.StudyGoals.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zup.StudyGoals.domain.Meta;
import com.zup.StudyGoals.presentation.apiclient.ApiClient;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditarMeta extends JFrame{
    private JComboBox metas;
    private JTextField assunto;
    private JTextField dataInicio;
    private JTextField dataFinal;
    private JTextField minutos;
    private JTextField objetivo;
    private JButton atualizarMetaButton;
    private ApiClient apiClient;
    private ObjectMapper objectMapper;
    private JPanel editarMeta;

    public EditarMeta() {
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Long idMeta = pegarIdMeta();
        atualizarMetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    Meta metaEditada = new Meta(assunto.getText(),dataInicio.getText(),dataFinal.getText(),  Integer.parseInt(minutos.getText()),objetivo.getText());

                    String jsonBody = objectMapper.writeValueAsString(metaEditada);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonBody);
                    ResponseBody responseBody = apiClient.putRequest(requestBody,"/metas/" + idMeta);
                    JOptionPane.showMessageDialog(null, responseBody.string(), "Meta editada com sucesso.",JOptionPane.INFORMATION_MESSAGE);
                }catch (IOException | NumberFormatException exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao editar a meta! ", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        setContentPane(editarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 350);
        setTitle("Editar meta");
        setVisible(true);
    }
    private Long pegarIdMeta(){
        String idMetaString = JOptionPane.showInputDialog("Digite o ID da meta que deseja editar: ");
        return Long.parseLong(idMetaString);
    }

}
