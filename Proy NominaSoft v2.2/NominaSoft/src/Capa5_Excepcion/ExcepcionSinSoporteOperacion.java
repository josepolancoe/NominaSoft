/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa5_Excepcion;

/**
 *
 * @author lucab
 */
public class ExcepcionSinSoporteOperacion extends UnsupportedOperationException{
    
    private static final String MENSAJE_SIN_SOPORTE = "El metodo o funcion no esta disponible.";
    
    private ExcepcionSinSoporteOperacion(String message) {
        super(message);
    }

    public static ExcepcionSinSoporteOperacion crearNoSoportado(){
        return new ExcepcionSinSoporteOperacion(MENSAJE_SIN_SOPORTE);
    }    
}
