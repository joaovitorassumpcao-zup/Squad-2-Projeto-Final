package com.zup.StudyGoals.view;

import javax.swing.*;

public class AdicionarMaterialNovo extends JFrame {
    private JPanel adicionarMaterial;
    private JButton adicionarMaterialButton;
    private JTextField titulo;
    private JComboBox categoria;
    private JTextField url;
    private JEditorPane resumo;
    private JTextField dataInicio;
    private JTextField dataConclusao;

    public AdicionarMaterialNovo() {
        setContentPane(adicionarMaterial);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);
        setTitle("Adicionar material");
        setVisible(true);
    }
}
