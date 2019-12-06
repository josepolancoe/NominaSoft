/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion.Utilidades;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JDialog;

/**
 *
 * @author lucab
 */
public class AtajosTecladoDlg implements KeyListener  {
    
    private JDialog dialog = null;    
    boolean respuesta = false;
    Set<Integer> ingresoTeclaInt = new HashSet<Integer>();
    
    public AtajosTecladoDlg(JDialog dialog) {
        this.dialog = dialog;
    }

    public Boolean solucionIngreso(){    
        return respuesta;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //Sin Usar
    }

    @Override
    public void keyPressed(KeyEvent ke) {
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
        ingresoTeclaInt.remove(ke.getKeyCode());
   }
}
