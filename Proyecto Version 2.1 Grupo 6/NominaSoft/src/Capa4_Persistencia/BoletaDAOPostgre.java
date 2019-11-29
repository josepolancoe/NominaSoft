/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.Boleta;
import Capa3_Dominio.Concepto;
import Capa3_Dominio.Contrato;
import Capa3_Dominio.Periodo;
import Capa5_Excepcion.ExcepcionSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JOSEPOLANCO
 */
public class BoletaDAOPostgre {
    
    GestorJDBC gestorJDBC;

    public BoletaDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    private void asignarParametros(PreparedStatement sentencia, Boleta boleta) throws SQLException {
        sentencia.setInt(1, boleta.getContrato().getIdcontrato());
        sentencia.setInt(2, boleta.getPeriodo().getIdperiodo());
        sentencia.setInt(3, boleta.getConcepto().getIdconcepto());
        sentencia.setDate(4, boleta.getFecha());
        sentencia.setInt(5, boleta.getTotalhoras());
        sentencia.setDouble(6, boleta.getValorhora());
        sentencia.setDouble(7, boleta.getAsignacionfamiliar());        
        }
     
    private Boleta crearObjBoleta(ResultSet resultado) throws SQLException {  
        Contrato contrato = new Contrato();
        Periodo periodo = new Periodo();       
        Concepto concepto = new Concepto();
        
        contrato.setIdcontrato(resultado.getInt("idcontrato"));
        periodo.setIdperiodo(resultado.getInt("idperiodo"));
        concepto.setIdconcepto(resultado.getInt("idconcepto"));
        
        return new Boleta(resultado.getInt("idboleta"),
                            contrato,
                            periodo,
                            concepto,
                            resultado.getDate("fecha"), 
                            resultado.getInt("totalhoras"), 
                            resultado.getDouble("valorhora"),
                            resultado.getDouble("asignacionfamiliar"));
    }
    
    public int ingresarBoleta(Boleta boleta)throws Exception{
        int registros_afectados;
        String sentenciaSQL = "insert into boleta(idcontrato, idperiodo, idconcepto, fecha, totalhoras, valorhora, asignacionfamiliar) "
                + "values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            asignarParametros(sentencia, boleta);
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
}
