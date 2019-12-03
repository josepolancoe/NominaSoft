/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.Empleado;
import Capa5_Excepcion.ExcepcionSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JOSEPOLANCO
 */
public class EmpleadoDAOPostgre {
    
    GestorJDBC gestorJDBC;

    public EmpleadoDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    private void asignarParametros(PreparedStatement sentencia, Empleado empleado) throws SQLException {
        //sentencia.setInt(8, empleado.getIdempleado());
        sentencia.setString(1, empleado.getDni());
        sentencia.setString(2, empleado.getNombre());
        sentencia.setDate(3, empleado.getFechanacimiento());
        sentencia.setString(4, empleado.getGradoacademico());
        sentencia.setString(5, empleado.getEstadocivil());
        sentencia.setString(6, empleado.getTelefono());
        sentencia.setString(7, empleado.getDireccion());
    }
    
    private Empleado crearObjEmpleado(ResultSet resultado) throws SQLException {
        return new Empleado(resultado.getInt("idempleado"), 
                            resultado.getString("dni"), 
                            resultado.getString("nombre"),
                            resultado.getDate("fechanacimiento"), 
                            resultado.getString("gradoacademico"), 
                            resultado.getString("estadocivil"), 
                            resultado.getString("telefono"),
                            resultado.getString("direccion"));    
    } 
     
    public int ingresarEmpleado(Empleado empleado) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "insert into empleado(dni, nombre, fechanacimiento, gradoacademico, estadocivil,  telefono, direccion)"
                                + "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, empleado);
            registros_afectados = sentencia.executeUpdate();
            sentencia.close();
            if(registros_afectados == 0){
                throw ExcepcionSQL.crearErrorInsertar();
            }
        return registros_afectados;
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorInsertar();
        }
    }
    
    public int modificarEmpleado(Empleado empleado) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "update empleado set dni = ?, nombre = ?, fechanacimiento = ?, gradoacademico = ?, estadocivil = ?, telefono = ?, direccion = ?"
                + " where idempleado = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, empleado);
            sentencia.setInt(8, empleado.getIdempleado());
            registros_afectados = sentencia.executeUpdate();
            sentencia.close();
            if(registros_afectados == 0){
                throw ExcepcionSQL.crearErrorModificar();
            }
        return registros_afectados;
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorModificar();
        }        
    }
    
    
    public int eliminarEmpleado(Empleado empleado) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "delete from empleado where idempleado = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setInt(1, empleado.getIdempleado());
            registros_afectados = sentencia.executeUpdate();
            sentencia.close();
            if(registros_afectados == 0){
                throw ExcepcionSQL.crearErrorEliminar();
            }
        return registros_afectados;
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorEliminar();
        }
    } 
       
    public Empleado verificarEmpleado(String dni) throws Exception {
        Empleado empleado = null;
        ResultSet resultado;
        String sentenciaSQL = "select idempleado, dni, nombre, fechanacimiento, gradoacademico, estadocivil, telefono, direccion"
                            + " from empleado"
                            + " where dni like '%" + dni + "%'";
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);            
            if(resultado.next()){
            empleado = crearObjEmpleado(resultado); 
            }
            resultado.close();
            return empleado;    
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }        
    }   
}