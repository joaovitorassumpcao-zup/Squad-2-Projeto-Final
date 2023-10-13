package com.zup.StudyGoals.view;

import javax.swing.*;

public class CadastrarMeta extends JFrame{
    private JTextField assunto;
    private JTextField dataInicio;
    private JTextField dataFinal;
    private JTextField minutos;
    private JTextField objetivo;
    private JButton salvarMetaButton;
    private JPanel cadastrarMeta;

    public CadastrarMeta() {
        setContentPane(cadastrarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setTitle("Cadastrar meta");
        setVisible(true);
    }
}
