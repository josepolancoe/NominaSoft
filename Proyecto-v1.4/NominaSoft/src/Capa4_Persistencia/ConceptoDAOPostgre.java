/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.Concepto;
import Capa3_Dominio.Contrato;
import Capa3_Dominio.Periodo;
import Capa5_Excepcion.ExcepcionSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JOSEPOLANCO
 */
public class ConceptoDAOPostgre {
    
    GestorJDBC gestorJDBC;
    
    public ConceptoDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    private void asignarParametros(PreparedStatement sentencia, Concepto concepto) throws SQLException{
        //sentencia.setInt(9, conceptos.getIdconceptoingresodescuento());
        sentencia.setInt(1, concepto.getContrato().getIdcontrato());
        sentencia.setInt(2, concepto.getPeriodo().getIdperiodo());
        sentencia.setInt(3, concepto.getHorasextra());
        sentencia.setDouble(4, concepto.getReintegros());
        sentencia.setDouble(5, concepto.getOtrosingresos());
        sentencia.setInt(6, concepto.getHorasausente());
        sentencia.setDouble(7, concepto.getAdelantos());
        sentencia.setDouble(8, concepto.getOtrosdescuentos());
            
    }
    private Concepto crearObjConcepto (ResultSet resultado) throws SQLException {
        
        Contrato contrato = new Contrato();
        contrato.setIdcontrato(resultado.getInt("idcontrato"));
        Periodo periodo = new Periodo();
        periodo.setIdperiodo(resultado.getInt("idperiodo"));       
        return new Concepto(resultado.getInt("idconcepto"),
                            contrato,
                            periodo,
                            resultado.getInt("horasextra"),
                            resultado.getDouble("reintegros"),
                            resultado.getDouble("otrosingresos"),
                            resultado.getInt("horasausente"),
                            resultado.getDouble("adelantos"),
                            resultado.getDouble("otrosdescuentos"));
            
    }
     
    public List<Concepto> buscarConceptos(int idcontrato) throws Exception{
        ArrayList<Concepto> listaConceptos = new ArrayList ();
        ResultSet resultado;
        Concepto concepto;
          
        String sentenciaSQL = "Select idconcepto, idcontrato, idperiodo, horasextra, reintegros, otrosingresos, horasausente, adelantos, otrosdescuentos"
                + "from concepto where idconcepto = '" +idcontrato + "'";       
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
            while(resultado.next()){
                concepto = crearObjConcepto(resultado);
                listaConceptos.add(concepto);
            }
            resultado.close();
            return listaConceptos;
        } catch (Exception e){
            throw ExcepcionSQL.crearErrorConsultar();
        }
    }
    
    
    public Concepto buscarConcepto (int idConcepto) throws Exception {
        Concepto concepto = null;
        ResultSet resultado;
        String sentenciaSQL = "Select idconcepto, idcontrato, idperiodo, horasextra, reintegros, otrosingresos, horasausente, adelantos, otrosdescuentos"
                        + "from concepto where idconcepto = '"+ idConcepto + "'";
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
            if(resultado.next()){
                concepto = crearObjConcepto(resultado);
            }
            resultado.close();
            return concepto;
        } catch ( Exception e){
            throw ExcepcionSQL.crearErrorConsultar();
        }
    }
    



}

