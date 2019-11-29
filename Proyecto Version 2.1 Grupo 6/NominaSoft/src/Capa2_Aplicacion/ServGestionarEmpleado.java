/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa3_Dominio.Empleado;
import Capa4_Persistencia.ConexionDAOPostgre;
import Capa4_Persistencia.EmpleadoDAOPostgre;
import Capa4_Persistencia.GestorJDBC;

/**
 *
 * @author JOSEPOLANCO
 */
public class ServGestionarEmpleado {
    
    private GestorJDBC gestorJDBC;
    private EmpleadoDAOPostgre empleadoDAOPostgre;
    
    
    public ServGestionarEmpleado() {
        gestorJDBC = new ConexionDAOPostgre();
        empleadoDAOPostgre = new EmpleadoDAOPostgre(gestorJDBC);
    }  
    
    public int IngresarEmpleado(Empleado empleado) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = empleadoDAOPostgre.ingresarEmpleado(empleado);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;  
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }    
    }
   
    public int ModificarEmpleado(Empleado empleado) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = empleadoDAOPostgre.modificarEmpleado(empleado);
            if(registros_afectados == 1)
                gestorJDBC.terminarTransaccion();
                return registros_afectados;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
    public int EliminarEmpleado(Empleado empleado) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = empleadoDAOPostgre.eliminarEmpleado(empleado);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;             
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
        
    public Empleado VerificarEmpleado(String dni) throws Exception{
        Empleado empleado = null;
        gestorJDBC.abrirConexion();
        try {            
            empleado = empleadoDAOPostgre.verificarEmpleado(dni);                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return empleado;
    }  
    
    
    public Empleado BuscarEmpleado(int idEmpleado) throws Exception{
        Empleado empleado = null;
        gestorJDBC.abrirConexion();
        try {            
            empleado = empleadoDAOPostgre.buscarEmpleado(idEmpleado);                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return empleado;
    }
    
    
}
