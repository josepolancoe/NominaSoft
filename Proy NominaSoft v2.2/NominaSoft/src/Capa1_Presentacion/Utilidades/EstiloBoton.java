/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion.Utilidades;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author lucab
 */
public class EstiloBoton extends JButton{
    
    private JButton boton = null;
    private final Color colorCeleste = new Color(188, 220, 207);
    private final Color colorAzulClaro = new Color(52,111,177);
    private final Color colorAzul = new Color(35,86,131); 
    private final Dimension tamañoBoton = new Dimension(80,28);        
    private final Font estiloFuenteSobre = new Font("Arial", 1, 12);
    private final Font estiloFuenteFuera = new Font("Arial", 0, 12);
    
    
    public EstiloBoton(Object [] objLista) {
        for(Object obj : objLista){
            this.boton = (JButton) obj;
            this.boton.setPreferredSize(tamañoBoton);
            this.boton.setBackground(colorCeleste);            
            this.boton.setForeground(colorearBoton(boton));
            this.boton.addMouseListener(sensorMouse(boton));          
        }        
    }
 
    public Color colorearBoton(JButton parBoton){
        if(parBoton.isEnabled()){
            return colorAzul;
        }else{
            return Color.GRAY;
        }       
    }
    
    public MouseAdapter sensorMouse(JButton parBoton){
        MouseAdapter adaptador = new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(parBoton.isEnabled()){
                        parBoton.setBackground((colorAzulClaro));
                        parBoton.setFont(estiloFuenteSobre);   
                        parBoton.setForeground(Color.WHITE);
                        parBoton.setBorder(new BordeRedondeado());
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parBoton.setBackground((colorCeleste));
                    parBoton.setFont(estiloFuenteFuera);
                    parBoton.setForeground(colorAzul);                        
                    parBoton.setBorder(null);
            }
                
            };
        return adaptador;
    }
}

