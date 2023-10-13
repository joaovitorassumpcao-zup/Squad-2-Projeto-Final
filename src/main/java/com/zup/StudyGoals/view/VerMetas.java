package com.zup.StudyGoals.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerMetas extends JFrame {

    public VerMetas() {

        this.setTitle("Ver todas as metas");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        DefaultTableModel tableModel = new DefaultTableModel();

        //Código de exemplo de adicionar colunas na tabela
        //ToDo modificar o código abaixo para se encaixar na nossa situação
        //String sql = "select * from atividades order by id ASC;";
//        try {
//            ResultSet resultSet = dadosService.getStatement().executeQuery(sql);
//
//            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
//                tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
//            }
//
//            while (resultSet.next()){
//                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
//                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
//                    row[i - 1] = resultSet.getObject(i);
//                }
//                tableModel.addRow(row);
//            }
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        scrollPane.setBounds(10, 10, 760, 540);

        this.add(scrollPane);

        this.setVisible(true);
    }
}
