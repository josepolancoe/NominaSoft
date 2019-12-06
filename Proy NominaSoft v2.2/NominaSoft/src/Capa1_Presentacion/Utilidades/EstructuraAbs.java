/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion.Utilidades;

import Capa5_Excepcion.ExcepcionSinSoporteOperacion;
import Capa5_Excepcion.Mensaje;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author lucab
 */
public abstract class EstructuraAbs extends JDialog{   

    public EstructuraAbs(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        this.getContentPane().setBackground(new Color(153,153,153));
        this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/Capa1_Presentacion/Imagen/iconoNomina.png")).getImage());                
        this.pack();
        }   
       
    public EstiloEtiqueta setEstiloEtiquetas(Object [] objListaLabels){
        return new EstiloEtiqueta(objListaLabels);
    }

    public EstiloBoton setEstiloBotones(Object [] objListaButtons){
        return new EstiloBoton(objListaButtons);
    }             
    
    
    public ImagenNominaSoft setImagenFondo(JLabel parLabel, int parAlto, int parAncho){
        return new ImagenNominaSoft(parLabel, parAlto, parAncho);
    }
    
    public void excepcionNoSoporte(ExcepcionSinSoporteOperacion parExcepcion){
        try {
            throw parExcepcion;
        } catch (ExcepcionSinSoporteOperacion er) {
            Mensaje.mostrarAdvertencia(null, er.getMessage());  
        } 
    }
 
    abstract public void estadoInicio();
    abstract public void estado(boolean parEstado);
    abstract public void estadoDefault();
    abstract public void limpiarEntradas();    
    abstract public void activarControles(boolean sw);    
    abstract public void activarEntradas(boolean sw);    
    abstract public void estadoControles(boolean sw);    
    abstract public void estadoEntradas(boolean sw);    
    abstract public void visibilidadControles(boolean sw);    
    abstract public void obtenerDatos();    
    abstract public void setMnemonic();  
    abstract public void estilo(); 
    abstract public void eventoTecla();

}