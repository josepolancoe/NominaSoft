/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.Afp;
import Capa5_Excepcion.ExcepcionSQL;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author JOSEPOLANCO
 */
public class AfpDAOPostgre {
    
    GestorJDBC gestorJDBC;

    public AfpDAOPostgre (GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    private void asignarParametros(PreparedStatement sentencia, Afp afp) throws SQLException {
        //sentencia.setInt(3, afp.getIdafp());
        sentencia.setString(1, afp.getNombre());
        sentencia.setDouble(2, afp.getPorcentaje());
        }
    
    private Afp crearObjAfp(ResultSet resultado) throws SQLException {  
        return new Afp(resultado.getInt("idafp"),
                            resultado.getString("nombre"), 
                            resultado.getDouble("porcentaje"));

    }
    
    public int ingresarAfp(Afp afp) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "insert into afp(nombre, porcentaje)"
                                + " values(?,?)";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, afp);            
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
         

    public int modificarAfp(Afp afp) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "update afp set nombre = ?, porcentaje = ? where idafp = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, afp);
            sentencia.setInt(3, afp.getIdafp());
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
      
    public int eliminarAfp(Afp afp) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "delete from afp where idafp = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setInt(1, afp.getIdafp());
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

    public List<Afp> buscarAfps()throws Exception{
        ArrayList<Afp> listaAfps = new ArrayList();
        ResultSet resultado;
        Afp afp;       
        String sentenciaSQL ="Select idafp, nombre, porcentaje from afp ";       
        try{
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);    
            while(resultado.next()){ 
                afp = crearObjAfp(resultado); 
                listaAfps.add(afp);
           }          
            resultado.close();
            return listaAfps;    
        }catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }
    }
    
    public Afp verificarAfp(int idAfp) throws Exception {
        Afp afp = null;
        ResultSet resultado;
        String sentenciaSQL = "select idafp, nombre, porcentaje"
                            + " from afp"
                            + " where idafp =" + idAfp;
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);            
            if(resultado.next()){
            afp = crearObjAfp(resultado); 
            }
            resultado.close();
            return afp;    
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }        
    }  
    
        public Afp verificarNombreAfp(String nombre) throws Exception {
        Afp afp = null;
        ResultSet resultado;
        String sentenciaSQL = "select idafp, nombre, porcentaje"
                            + " from afp"
                            + " where nombre like '%" + nombre + "%'";
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);            
            if(resultado.next()){
            afp = crearObjAfp(resultado); 
            }
            resultado.close();
            return afp;    
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }        
    }  
}
