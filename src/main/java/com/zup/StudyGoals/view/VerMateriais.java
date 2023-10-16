package com.zup.StudyGoals.view;

import javax.swing.*;

public class VerMateriais extends JFrame{
    private JComboBox metas;
    private JButton verMateriaisButton;
    private JPanel verMateriais;
    private JTable materiais;

    public VerMateriais() {
        setContentPane(verMateriais);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setTitle("Ver materiais");
        setVisible(true);
    }
}
