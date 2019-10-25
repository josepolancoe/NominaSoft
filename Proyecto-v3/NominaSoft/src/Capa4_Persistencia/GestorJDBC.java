/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa5_Excepcion.ExcepcionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JOSEPOLANCO
 */
public abstract class GestorJDBC {
          protected Connection conexion;
    
    public abstract void abrirConexion() throws Exception;
    
    public void cerrarConexion() throws Exception{
        try {
            conexion.close();
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorCerrarConexion();
        }        
    }
    
    public void iniciarTransaccion() throws Exception{
        try {
            conexion.setAutoCommit(false);
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorIniciarTransaccion();
        }        
    }
    
    public void terminarTransaccion() throws Exception{
        try {
            conexion.commit();
            conexion.setAutoCommit(true);
            conexion.close();
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorTerminarTransaccion();
        }        
    }
    
    public void cancelarTransaccion() throws Exception{
        try {
            conexion.rollback();
            conexion.setAutoCommit(true);
            conexion.close();
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorCancelarTransaccion();
        }
    }
    
    public PreparedStatement prepararSentencia(String sql) throws SQLException{
        return conexion.prepareStatement(sql);
    }
    
    public ResultSet ejecutarConsulta(String sql) throws SQLException{
        Statement sentencia;
        ResultSet resultado;
        sentencia = conexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        return resultado;
    }
    
}
