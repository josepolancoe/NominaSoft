/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.Contrato;
import Capa3_Dominio.Empleado;
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
public class ContratoDAOPostgre {
        
    GestorJDBC gestorJDBC;

    public ContratoDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
       
    private void asignarParametros(PreparedStatement sentencia, Contrato contrato) throws SQLException {
        sentencia.setInt(1, contrato.getEmpleado().getIdempleado());
        sentencia.setInt(2, contrato.getAfp().getIdafp());
        sentencia.setDate(3, contrato.getFechainiciocontrato());
        sentencia.setDate(4, contrato.getFechafincontrato());
        sentencia.setString(5, contrato.getCargo());
        sentencia.setBoolean(6, contrato.getAsignacionfamiliar());
        sentencia.setInt(7, contrato.getTotalhoras  ());
        sentencia.setDouble(8, contrato.getValorhora());
        }
     
    private Contrato crearObjContrato(ResultSet resultado) throws SQLException {  
        Empleado empleado = new Empleado();
        Afp afp = new Afp();       
        empleado.setIdempleado(resultado.getInt("idempleado"));
        afp.setIdafp(resultado.getInt("idafp"));
        
        return new Contrato(resultado.getInt("idcontrato"),
                            empleado,
                            afp,
                            resultado.getDate("fechainiciocontrato"), 
                            resultado.getDate("fechafincontrato"), 
                            resultado.getString("cargo"),
                            resultado.getBoolean("asignacionfamiliar"), 
                            resultado.getInt("totalhoras"), 
                            resultado.getDouble("valorhora"),
                            resultado.getString("condicion"));
    }
    
    public int ingresarContrato(Contrato contrato)throws Exception{
        int registros_afectados;
        String sentenciaSQL = "insert into contrato(idempleado, idafp, fechainiciocontrato, fechafincontrato, cargo, asignacionfamiliar, totalhoras, valorhora) "
                + "values(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, contrato);
            registros_afectados = sentencia.executeUpdate();
            if(registros_afectados == 0){
                throw ExcepcionSQL.crearErrorInsertar();   
             }
             return registros_afectados;
        }
        catch(Exception e){
            throw ExcepcionSQL.crearErrorInsertar();
        }
    }
    
    public int modificarContrato(Contrato contrato) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "update contrato set idempleado = ?, idafp = ?, fechainiciocontrato = ?, fechafincontrato = ?, cargo = ?, asignacionfamiliar = ?, totalhoras = ?, valorhora = ?"
                + " where idcontrato = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, contrato);
            sentencia.setInt(9, contrato.getIdcontrato());
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

    public List<Contrato> buscarContratos(int idempleado)throws Exception{
        ArrayList<Contrato> listaContratos = new ArrayList();
        ResultSet resultado;
        Contrato contrato;
        
        String sentenciaSQL ="Select idcontrato, idempleado, idafp, fechainiciocontrato, fechafincontrato, cargo, asignacionfamiliar, totalhoras, valorhora, condicion "
                             + " from contrato where idempleado = '" + idempleado + "'";       
  
        try{
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);    
            while(resultado.next()){ 
                contrato = crearObjContrato(resultado); 
                listaContratos.add(contrato);
           }          
            resultado.close();
            return listaContratos;    
        }catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }
    }
        
        public Contrato verificarContrato(int idContrato) throws Exception {
        Contrato contrato = null;
        ResultSet resultado;
        String sentenciaSQL = "Select idcontrato, idempleado, idafp, fechainiciocontrato, fechafincontrato, cargo, asignacionfamiliar, totalhoras, valorhora, condicion "
                             + " from contrato where idcontrato = '" + idContrato + "'";       
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);            
            if(resultado.next()){
            contrato = crearObjContrato(resultado); 
            }
            resultado.close();
            return contrato;    
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }        
    } 
    
    public int anularContrato(Contrato contrato) throws Exception {
        int registros_afectados;
        String sentenciaSQL = "update contrato set condicion = 'ANULADO', fechafincontrato =  CURRENT_DATE "
                + "where idcontrato = ?";
        try {
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setInt(1, contrato.getIdcontrato());
            registros_afectados = sentencia.executeUpdate();
            sentencia.close();
            if(registros_afectados == 0){
                throw ExcepcionSQL.crearErrorAnular();
            }
            return registros_afectados;
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorAnular();
        }        
    } 
    
    public List<Contrato> buscarContratosActivos()throws Exception{
        ArrayList<Contrato> listaContrato = new ArrayList();
        ResultSet resultado;
        Contrato contrato;
        
        String sentenciaSQL = " select c.idcontrato, e.idempleado, e.nombre, e.dni,"
                            + " c.idafp, c.fechainiciocontrato, c.fechafincontrato, c.cargo,"
                            + " c.asignacionfamiliar, c.totalhoras, c.valorhora, c.condicion"
                            + " from contrato as c"   
                            + " inner join empleado as e"
                            + " on e.idempleado=c.idempleado"
                            + " where c.condicion='ACTIVO'" 
                            + " order by e.idempleado asc";
  
        try{
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);    
            while(resultado.next()){ 
                contrato = crearObjContrato(resultado);
                listaContrato.add(contrato);
           }          
            resultado.close();
            return listaContrato;    
        }catch (Exception e) {
            throw ExcepcionSQL.crearErrorConsultar();
        }
    }
    
}
