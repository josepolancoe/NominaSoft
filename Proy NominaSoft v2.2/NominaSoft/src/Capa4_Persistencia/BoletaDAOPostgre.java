/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.Boleta;
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
        sentencia.setDouble(5, boleta.getTotalhoras());
        sentencia.setDouble(6, boleta.getValorhora());
        sentencia.setDouble(7, boleta.getAsignacionfamiliar());        
        }
    
    public int ingresarBoleta(Boleta boleta)throws ExcepcionSQL{
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
        catch(ExcepcionSQL | SQLException e){
            throw ExcepcionSQL.crearErrorInsertar();
        }
    }
}
