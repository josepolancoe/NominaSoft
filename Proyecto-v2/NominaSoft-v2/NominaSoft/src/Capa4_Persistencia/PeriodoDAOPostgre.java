/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.Periodo;
import Capa5_Excepcion.ExcepcionSQL;
import java.sql.ResultSet;

/**
 *
 * @author JOSEPOLANCO
 */
public class PeriodoDAOPostgre {
    GestorJDBC gestorJDBC;

    public PeriodoDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }  
    
    
    public Periodo buscarPeriodo(int idPeriodo)throws Exception{
        ResultSet resultado;   
        Periodo periodo = null;
        String sentenciaSQL ="Select idperiodo, estado,  fechainicioperiodo, fechafinperiodo from Periodo where idperiodo = '" +
                            idPeriodo + "'";       
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
    
}
