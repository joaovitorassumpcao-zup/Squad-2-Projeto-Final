package com.zup.StudyGoals.view;

import javax.swing.*;

public class DeletarMeta extends JFrame{
    private JComboBox metas;
    private JButton deletarMetaButton;
    private JPanel deletarMeta;

    public DeletarMeta() {
        setContentPane(deletarMeta);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(350, 150);
        setTitle("Deletar meta");
        setVisible(true);
    }
}
