/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa6_Globales;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author lucab
 */
public class EstiloEtiqueta {
    
    private JLabel label = null;
    private  Font miEstilo = new Font("Arial", 1, 12);
    private Color color = new Color(255, 255, 255);
    private Object [] objLista = null;
    
    public EstiloEtiqueta(Object [] objLista) {
        for(Object obj : objLista){
            this.label = (JLabel) obj;
            this.label.setFont(miEstilo);
            this.label.setForeground(color);
        }
    }   
}
