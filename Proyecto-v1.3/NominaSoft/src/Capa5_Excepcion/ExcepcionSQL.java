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
public class ExcepcionSQL extends Exception{
    
    private static final String MENSAJE_ERROR_CONSULTAR = "No se pudo realizar la consulta.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String MENSAJE_ERROR_INSERTAR = "No se pudo guardar los datos.\n"
            + "Verifique los datos obligatorios y únicos.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String MENSAJE_ERROR_MODIFICAR = "No se pudo actualizar los datos.\n"
            + "Verifique los datos obligatorios y únicos.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String MENSAJE_ERROR_ELIMINAR = "No se pudo eliminar el registro.\n"
            + "Intente de nuevo o consulte con el Administrador.";
        private static final String MENSAJE_ERROR_ANULAR = "No se pudo anular el registro.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String MENSAJE_ERROR_ABRIRCONEXION = "No se pudo abrir la conexión con la base de datos.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String MENSAJE_ERROR_CERRARCONEXION = "No se pudo cerrar la conexión con la base de datos.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String MENSAJE_ERROR_INICIARTRANSACCION = "No se pudo iniciar la transacción.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String MENSAJE_ERROR_TERMINARTRANSACCION = "No se pudo terminar la transacción.\n"
            + "Intente de nuevo o consulte con el Administrador.";
    private static final String MENSAJE_ERROR_CANCELARTRANSACCION = "No se pudo cancelar la transacción.\n"
            + "Intente de nuevo o consulte con el Administrador.";

    private ExcepcionSQL(String message) {
        super(message);
    }
    
    public static ExcepcionSQL crearErrorConsultar(){
        return new ExcepcionSQL(MENSAJE_ERROR_CONSULTAR);
    }
    
    public static ExcepcionSQL crearErrorInsertar(){
        return new ExcepcionSQL(MENSAJE_ERROR_INSERTAR);
    }
    
    public static ExcepcionSQL crearErrorModificar(){
        return new ExcepcionSQL(MENSAJE_ERROR_MODIFICAR);
    }
    
    public static ExcepcionSQL crearErrorEliminar(){
        return new ExcepcionSQL(MENSAJE_ERROR_ELIMINAR);
    }
    
    public static ExcepcionSQL crearErrorAnular(){
        return new ExcepcionSQL(MENSAJE_ERROR_ANULAR);
    }
    
    public static ExcepcionSQL crearErrorAbrirConexion(){
        return new ExcepcionSQL(MENSAJE_ERROR_ABRIRCONEXION);
    }
    
    public static ExcepcionSQL crearErrorCerrarConexion(){
        return new ExcepcionSQL(MENSAJE_ERROR_CERRARCONEXION);
    }
    
    public static ExcepcionSQL crearErrorIniciarTransaccion(){
        return new ExcepcionSQL(MENSAJE_ERROR_INICIARTRANSACCION);
    }
    
    public static ExcepcionSQL crearErrorTerminarTransaccion(){
        return new ExcepcionSQL(MENSAJE_ERROR_TERMINARTRANSACCION);
    }
    
    public static ExcepcionSQL crearErrorCancelarTransaccion(){
        return new ExcepcionSQL(MENSAJE_ERROR_CANCELARTRANSACCION);
    }
}
