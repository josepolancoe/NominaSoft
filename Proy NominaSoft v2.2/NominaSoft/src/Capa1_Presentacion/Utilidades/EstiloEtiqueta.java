/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion.Utilidades;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author lucab
 */
public class EstiloEtiqueta extends JLabel{
    
    private final Font estiloFuenteLabel = new Font("Arial", 1, 12);
    private final Color colorFuenteLabel = new Color(255, 255, 255);
    private JLabel etiqueta = null;
    
    public EstiloEtiqueta(Object [] objLista) {
        for(Object obj : objLista){
            this.etiqueta = (JLabel) obj;
            this.etiqueta.setFont(estiloFuenteLabel);
            this.etiqueta.setForeground(colorFuenteLabel);
        }
    }

    public Font getEstiloFuenteLabel() {
        return estiloFuenteLabel;
    }

    public Color getColorFuenteLabel() {
        return colorFuenteLabel;
    }
    
    
}
