package com.zup.StudyGoals.view;

import javax.swing.*;

public class DeletarRelatorio extends JFrame{
    private JPanel deletaRelatorio;
    private JComboBox comboBox1;
    private JButton deletarRelatorioButton;

    public DeletarRelatorio() {
        setContentPane(deletaRelatorio);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(350, 150);
        setTitle("Deletar relatório");
        setVisible(true);
    }
}
