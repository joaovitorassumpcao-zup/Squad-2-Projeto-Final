package com.zup.StudyGoals.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame{
    private JPanel menu;
    private JButton cadastrarUmaMetaButton;
    private JButton verMetasAtivasButton;
    private JButton verTodasAsMetasButton;
    private JButton adicionarUmMaterialAButton;
    private JButton deletarUmaMetaButton;
    private JButton gerarRelatorioDeUmaButton;
    private JButton verTodosOsRelatoriosButton;
    private JButton deletarRelatorioButton;
    private JButton editarUmaMetaButton;
    private JButton verMateriaisDeEstudoButton;

    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
    }

    public MenuPrincipal() {
        Nimbus.pegaNimbus();

        SwingUtilities.updateComponentTreeUI(menu);

        setContentPane(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(350, 350);
        setTitle("Menu Principal");
        setResizable(false);
        setVisible(true);


        cadastrarUmaMetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastrarMeta cadastrarMeta = new CadastrarMeta();
            }
        });

        verMetasAtivasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerMetasAtivas verMetasAtivas = new VerMetasAtivas();
            }
        });

        verTodasAsMetasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerMetas verMetas = new VerMetas();
            }
        });

        deletarUmaMetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeletarMeta deletarMeta = new DeletarMeta();
            }
        });

        gerarRelatorioDeUmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerarRelatorio relatorio = new GerarRelatorio();
            }
        });

        verTodosOsRelatoriosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerRelatorios verRelatorios = new VerRelatorios();
            }
        });

        deletarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeletarRelatorio deletarRelatorio = new DeletarRelatorio();
            }
        });

        editarUmaMetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarMeta editarMeta = new EditarMeta();
            }
        });

        verMateriaisDeEstudoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerMateriais verMateriais = new VerMateriais();
            }
        });
    }
}
