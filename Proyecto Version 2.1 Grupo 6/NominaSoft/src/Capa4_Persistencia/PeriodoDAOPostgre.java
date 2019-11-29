/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.Periodo;
import Capa5_Excepcion.ExcepcionSQL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JOSEPOLANCO
 */
public class PeriodoDAOPostgre {
    GestorJDBC gestorJDBC;

    public PeriodoDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }  
     private void asignarParametros(PreparedStatement sentencia, Periodo periodo) throws SQLException {
        //sentencia.setString(1, periodo.getEstado());
        sentencia.setDate(1, (Date) periodo.getFechainicioperiodo());
        sentencia.setDate(2, (Date) periodo.getFechafinperiodo());   
    }
     
     private Periodo crearObjPeriodo(ResultSet resultado) throws SQLException {
        return new Periodo(resultado.getInt("idperiodo"), 
                            resultado.getString("estado"), 
                            resultado.getDate("fechainicioperiodo"), 
                            resultado.getDate("fechafinperiodo"));
    } 
     
     public int ingresarPeriodo(Periodo periodo) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "insert into periodo (fechainicioperiodo, fechafinperiodo)"
                                + "values(?,?)";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, periodo);
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
     
      public int modificarPeriodo(Periodo periodo) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "update periodo set fechainicioperiodo = ?, fechafinperiodo = ?"
                + " where idperiodo = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, periodo);
            sentencia.setInt(3, periodo.getIdperiodo());
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
     
     public int procesarPeriodo(Periodo periodo) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "update periodo set estado = 'PROCESADO' "
                + " where idperiodo = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            //asignarParametros(sentencia, periodo);
            //sentencia.setString(1, periodo.getEstado());
            sentencia.setInt(1, periodo.getIdperiodo());
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
         
    public int eliminarPeriodo(Periodo periodo) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "delete from periodo where idperiodo = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setInt(1, periodo.getIdperiodo());
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
     
     
    public Periodo buscarPeriodo(int idperiodo)throws Exception{
        ResultSet resultado;   
        Periodo periodo = null;
        String sentenciaSQL ="Select idperiodo, estado,  fechainicioperiodo, fechafinperiodo from periodo where idperiodo = '" +
                            idperiodo + "'";       
        try{
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);  
            while(resultado.next()){   
                periodo = new Periodo(resultado.getInt("idperiodo"),
                                        resultado.getString("estado"),
                                        resultado.getDate("fechainicioperiodo"), 
                                        resultado.getDate("fechafinperiodo"));    
            }
            resultado.close();
            return periodo;    
        }catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }
    }
     public Periodo verificarPeriodo(int idperiodo) throws Exception {
        Periodo periodo = null;
        ResultSet resultado;
        String sentenciaSQL = "select idperiodo, estado, fechainicioperiodo, fechafinperiodo"
                            + " from periodo"
                            + " where idperiodo =  '" + idperiodo + "'";
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);            
            if(resultado.next()){
            periodo = crearObjPeriodo(resultado); 
            }
            resultado.close();
            return periodo;    
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }        
    }   
    
         public List<Periodo> buscarPeriodos()throws Exception{
        ArrayList<Periodo> listaPeriodos = new ArrayList();
        ResultSet resultado;
        Periodo periodo;
        
        String sentenciaSQL ="Select idperiodo, estado, fechainicioperiodo, fechafinperiodo "
                             + " from periodo ";       
  
        try{
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);    
            while(resultado.next()){ 
                periodo = crearObjPeriodo(resultado); 
                listaPeriodos.add(periodo);
           }          
            resultado.close();
            return listaPeriodos;    
        }catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }
    }
}
