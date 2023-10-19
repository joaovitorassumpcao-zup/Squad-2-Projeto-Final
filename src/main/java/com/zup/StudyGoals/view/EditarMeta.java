package com.zup.StudyGoals.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
import java.time.format.DateTimeFormatter;
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
    private JButton adicionarMaterialNovoButton;
    //private JButton adicionarMaterialButton;
    public List<MaterialDeEstudo> materiaisDeEstudo;

    private Long idMeta;
    public EditarMeta() {
        this.materiaisDeEstudo = new ArrayList<>();
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        objectMapper.registerModule(module);

        idMeta = pegarIdMeta();

        try {
            preencherCampos();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        atualizarMetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meta metaEditada = new Meta();
                metaEditada.setId(idMeta);
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
                    JOptionPane.showMessageDialog(null,"Meta editada com sucesso!");
                }catch (IOException | NumberFormatException exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao editar a meta!", "ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        adicionarMaterialNovoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdicionarMaterialNovo adicionarMaterialNovo = new AdicionarMaterialNovo(EditarMeta.this);
            }
        });


        setContentPane(editarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 350);
        setTitle("Editar meta");
        setVisible(true);
    }

    public void adicionarMaterial (MaterialDeEstudo materialDeEstudo){
        this.materiaisDeEstudo.add(materialDeEstudo);
    }

    private Long pegarIdMeta(){
        String idMetaString = JOptionPane.showInputDialog("Digite o ID da meta que deseja editar: ");
        return Long.parseLong(idMetaString);
    }

    private void preencherCampos() throws IOException {
        String response = null;
        try {
            response = apiClient.getRequest("/metas");
            List<Meta> metas = objectMapper.readValue(response,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, Meta.class));

            for (Meta meta : metas) {
                if (meta.getId() == idMeta) {
                    assunto.setText(meta.getAssunto());
                    dataInicio.setText(meta.getDataDeInicio().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                    dataFinal.setText(meta.getDataFinal().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                    minutos.setText(String.valueOf(meta.getMetaMinutosDia()));
                    objetivo.setText(meta.getObjetivo());

                    materiaisDeEstudo.clear();

                    materiaisDeEstudo.addAll(meta.getMateriaisDeEstudo());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
