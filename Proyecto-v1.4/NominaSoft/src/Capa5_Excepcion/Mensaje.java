/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa5_Excepcion;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author lucab
 */
public class Mensaje {
    
    public static final String REGISTRO_NO_EXISTE = "El registro no existe, verifique.";
    public static final String REGISTRO_ELIMINADO = "El registro fue eliminado.";
    public static final String REGISTRO_CREADO = "El registro fue creado.";
    public static final String REGISTRO_MODIFICADO = "El registro fue modificado.";
    public static final String DATOS_INCORRECTOS = "Existen datos incorrectos, verifique.";
    public static final String ELEMENTO_NO_SELECCIONADO = "Seleccione un elemento de la lista o tabla.";
    
    /**
     * Muestra un mensaje de afirmación
     * @param dialogo
     * @param mensaje es el mensaje que se desea mostrar
     */
    public static void mostrarAfirmacion(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Warning : Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de advertencia
     * @param dialogo
     * @param mensaje es el mensaje que se desea mostrar
     */
    public static void mostrarAdvertencia(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Warning : Advertencia",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de error
     * @param dialogo
     * @param mensaje es el mensaje que se desea mostrar
     */
    public static void mostrarError(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Warning : Error",JOptionPane.ERROR_MESSAGE);
    }
    
}
