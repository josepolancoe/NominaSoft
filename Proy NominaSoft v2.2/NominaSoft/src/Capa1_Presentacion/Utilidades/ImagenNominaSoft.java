/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion.Utilidades;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author lucab
 */
public class ImagenNominaSoft extends JLabel{
    
    private JLabel imagen = null;

    public ImagenNominaSoft(JLabel parLabel, int parAlto, int parAncho) {
            imagen = parLabel;
            this.imagen.setPreferredSize(new Dimension(parAlto,parAncho));
            this.imagen.setIcon(new ImageIcon(getClass().getResource("/Capa1_Presentacion/Imagen/nombreNominaSoft.png")));
            this.imagen.setVerticalAlignment(JLabel.BOTTOM);
            this.imagen.setHorizontalAlignment(JLabel.RIGHT);            
            this.imagen.setLocation((parAncho - this.imagen.getWidth())/2, (parAlto - this.imagen.getHorizontalAlignment())/2);             
            
    }
    
    
    
    
}