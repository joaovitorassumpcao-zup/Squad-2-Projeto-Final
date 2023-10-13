package com.zup.StudyGoals.view;

import javax.swing.*;

public class AdicionarMaterial extends JFrame{
    private JTextField titulo;
    private JComboBox categoria;
    private JTextField url;
    private JEditorPane resumo;
    private JTextField dataInicio;
    private JTextField dataConclusao;
    private JPanel adicionarMaterial;
    private JButton adicionarMaterialButton;
    private JComboBox metas;

    public AdicionarMaterial() {
        setContentPane(adicionarMaterial);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);
        setTitle("Adicionar material");
        setVisible(true);
    }
}
