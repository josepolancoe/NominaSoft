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
   
    public static final String MENSAJE_SIN_ACCESO = "Usuario y/o contraseña incorrectos.";
    public static final String MENSAJE_INGRESE_USUARIO = "Ingresar usuario";
    public static final String MENSAJE_INGRESE_CONTRASENIA = "Ingresar contraseña";
    public static final String MENSAJE_INGRESE_USUARIOCONTRASENIA = "Ingresar usuario y/o contraseña";
    
    public static final String INGRESE_DNI = "Ingrese DNI.";
    public static final String ERROR_DNI = "Por favor, ingrese DNI valido";    
    
    public static final String REGISTRO_NO_EXISTE = "El registro no existe.";
    public static final String LISTA_VACIA = "La lista no contiene ";
    public static final String OBJETO_NULO = "No existe registro ";   
    
    public static final String DATOS_INCORRECTOS = "Existen datos incorrectos, verifique.";
    public static final String DATOS_INCOMPLETOS = "Ingrese o selecciones los datos que faltan.";
    public static final String DATOS_PROCESADOS = "Se procesaron los pagos de todos los empleados."; 
    public static final String DATOS_NO_PROCESADOS = "No se procesaron los Datos."; 
    public static final String ELEMENTO_NO_SELECCIONADO = "Seleccione un elemento de la lista o tabla.";    
    
    public static final String REGISTRO_ELIMINADO = "El registro fue eliminado.";
    public static final String REGISTRO_CREADO = "El registro fue creado.";
    public static final String REGISTRO_ACTUALIZADO = "El registro fue modificado.";
    public static final String REGISTRO_ANULADO = "El contrato fue anulado.";

    public static final String ERROR_INGRESO = "Error de Ingreso, no se completo el registro.";
    public static final String ERROR_ACTUALIZACION = "Error de Actualizacion, no se completo el registro.";
    public static final String ERROR_ELIMINAR = "Error de Eliminar, no se elimino el registro.";
    public static final String ERROR_ANULAR = "Error de Anular, no se anulo el contrato.";
    public static final String ERROR_SERVICIO = "Error de Servicio, el servicio no obtuvo respuesta.";

    private Mensaje() {
    }
    
    public static void mostrarAfirmacion(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Warning : Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarAdvertencia(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Warning : Advertencia",JOptionPane.WARNING_MESSAGE);
    }
 
    public static void mostrarError(JDialog dialogo, String mensaje){
        JOptionPane.showMessageDialog(dialogo,mensaje,"Warning : Error",JOptionPane.ERROR_MESSAGE);
    }
   
}
