package com.zup.StudyGoals.view;

import javax.swing.*;

public class EditarMeta extends JFrame{
    private JComboBox metas;
    private JTextField assunto;
    private JTextField dataInicio;
    private JTextField dataFinal;
    private JTextField minutos;
    private JTextField objetivo;
    private JButton atualizarMetaButton;
    private JPanel editarMeta;

    public EditarMeta() {
        setContentPane(editarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 350);
        setTitle("Editar meta");
        setVisible(true);
    }
}
