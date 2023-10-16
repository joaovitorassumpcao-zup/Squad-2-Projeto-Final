package com.zup.StudyGoals.view;

import javax.swing.*;

public class EditarMeta extends JFrame{
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
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
