/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa3_Dominio.Concepto;
import Capa4_Persistencia.ConceptoDAOPostgre;
import Capa4_Persistencia.ConexionDAOPostgre;
import Capa4_Persistencia.ContratoDAOPostgre;
import Capa4_Persistencia.GestorJDBC;
import Capa4_Persistencia.PeriodoDAOPostgre;
import java.util.List;

/**
 *
 * @author JOSEPOLANCO
 */
public class ServGestionarConcepto {
    
    private GestorJDBC gestorJDBC;
    private ConceptoDAOPostgre conceptoDAOPostgre;
    private ContratoDAOPostgre contratoDAOPostgre;
    private PeriodoDAOPostgre periodoDAOPostgre;
    
    public ServGestionarConcepto() {
        gestorJDBC = new ConexionDAOPostgre();
        conceptoDAOPostgre = new ConceptoDAOPostgre(gestorJDBC);
        contratoDAOPostgre = new ContratoDAOPostgre (gestorJDBC);
        periodoDAOPostgre = new PeriodoDAOPostgre (gestorJDBC); 
    }
    
    public Concepto buscarConcepto(int idConcepto) throws Exception {
        Concepto concepto = null;
        gestorJDBC.abrirConexion();
        try {
            concepto = conceptoDAOPostgre.buscarConcepto (idConcepto);          
        } catch(Exception e){
            gestorJDBC.cerrarConexion();
            throw e;
        } 
        gestorJDBC.cerrarConexion();
        return concepto;
    }
   
    public Concepto BuscarConceptoContrato(int idContrato) throws Exception {
        Concepto concepto = null;
        gestorJDBC.abrirConexion();
        try {
            concepto = conceptoDAOPostgre.buscarConceptoContrato(idContrato);          
        } catch(Exception e){
            gestorJDBC.cerrarConexion();
            throw e;
        } 
        gestorJDBC.cerrarConexion();
        return concepto;
    }

//    public List<Concepto> BuscarConceptos(int idcontrato) throws Exception{
//        List<Concepto> listaConceptos = null;
//        gestorJDBC.abrirConexion();
//        try {
//            listaConceptos = conceptoDAOPostgre.buscarConceptos(idcontrato);     
//        } catch (Exception e) {
//            gestorJDBC.cerrarConexion();
//            throw e;
//        }
//        gestorJDBC.cerrarConexion();
//        return listaConceptos;
//    }       
}
