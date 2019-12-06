/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion.Utilidades;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

/**
 *
 * @author lucab
 */
public class BordeRedondeado implements Border{

    private int radius;    
    
    BordeRedondeado() {
        this.radius = 6;
    }
    
    @Override
    public void paintBorder(Component componente, Graphics graphics, int x, int y, int parAncho, int parAlto) {
        graphics.drawRoundRect(x, y, parAncho - 1, parAlto - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component componente) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    
    
}
