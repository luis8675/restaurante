/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinalprogra;

import Restaurante.loggin;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;


/**
 *
 * @author 50242
 */

public class Proyectofinalprogra {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo aplicar Nimbus: " + e.getMessage());
        }

        // Iniciar la aplicación desde el login
        SwingUtilities.invokeLater(() -> {
            loggin frm = new loggin();
            frm.setLocationRelativeTo(null); // Centra la ventana
            frm.setVisible(true);
        });
    }
}

