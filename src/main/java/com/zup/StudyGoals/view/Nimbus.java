package com.zup.StudyGoals.view;

import javax.swing.UIManager;

import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Nimbus {

    private Nimbus(){

    }

    public static void pegaNimbus(){
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (InstantiationException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (IllegalAccessException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        }
    }
}
