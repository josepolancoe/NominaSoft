/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa6_Globales;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author lucab
 */
public class AtajosTecladoDlg implements KeyListener  {
    
    private JDialog dialog = null;    
    boolean respuesta = false;
    Set<Integer> ingresoTeclaInt = new HashSet<Integer>();
    //Set<Character> ingresoTeclaChr = new HashSet<Character>();
    
    public AtajosTecladoDlg(JDialog dialog) {
        this.dialog = dialog;
    }

    public Boolean SolucionIngreso(){    
        return respuesta;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //System.out.println("Tecla tipeada");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //System.out.println("Presionar tecla!");
        ingresoTeclaInt.add(ke.getKeyCode());
        if(ingresoTeclaInt.contains(17) && ingresoTeclaInt.contains(10) && ingresoTeclaInt.contains(83)){
            respuesta = true;
        }    
        if(ke.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dialog.dispose();
        }     
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //System.out.println("Soltar la tecla!");
        ingresoTeclaInt.remove(ke.getKeyCode());
   }
}
