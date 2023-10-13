package com.zup.StudyGoals.view;

import javax.swing.*;

public class GerarRelatorio extends JFrame{
    private JPanel relatorio;
    private JComboBox metas;
    private JButton gerarRelatórioButton;
    private JTable Relatório;

    public GerarRelatorio() {

        setContentPane(relatorio);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setTitle("Relatório");
        setVisible(true);
    }
}
