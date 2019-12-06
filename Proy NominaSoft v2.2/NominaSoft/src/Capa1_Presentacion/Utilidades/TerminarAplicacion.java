/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion.Utilidades;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author lucab
 */
public class TerminarAplicacion {
 
    private JFrame frame = null;
    
    public TerminarAplicacion(JFrame frame) {
        this.frame = frame;
        this.frame.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");

        this.frame.getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                close();
            }
        });

        this.frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
    }

    public void close(){
        java.awt.Toolkit.getDefaultToolkit().beep();
        if (JOptionPane.showConfirmDialog(this.frame, "¿Desea realmente salir de la aplicación?",
                "Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Runtime.getRuntime().exit(0);
        }
    }
}