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
        sentencia.setInt(1, afp.getIdafp());
        sentencia.setString(2, afp.getNombre());
        sentencia.setDouble(3, afp.getPorcentaje());
        }
    
    private Afp crearObjAfp(ResultSet resultado) throws SQLException {  
        return new Afp(resultado.getInt("idafp"),
                            resultado.getString("nombre"), 
                            resultado.getDouble("porcentaje"));

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
}
