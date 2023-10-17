package com.zup.StudyGoals.view;

import com.zup.StudyGoals.presentation.apiclient.ApiClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeletarMeta extends JFrame{
    private JButton deletarMetaButton;
    private ApiClient apiClient;
    private JPanel deletarMeta;
    private JComboBox metas;

    public DeletarMeta() {
        this.apiClient = new ApiClient();
        Long idMeta = pegarIdMeta();

        deletarMetaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    apiClient.deleteRequest("/metas/" + idMeta);
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
    private Long pegarIdMeta(){
        String idMetaString = JOptionPane.showInputDialog("Digite o ID da meta que deseja editar: ");
        return Long.parseLong(idMetaString);
    }
}
