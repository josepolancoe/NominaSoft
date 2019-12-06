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
import Capa5_Excepcion.Mensaje;
import java.util.List;

/**
 *
 * @author JOSEPOLANCO
 */
public class ServGestionarConcepto {
    
    private GestorJDBC gestorJDBC;
    private ConceptoDAOPostgre conceptoDAOPostgre;
    
    public ServGestionarConcepto() {
        gestorJDBC = new ConexionDAOPostgre();
        conceptoDAOPostgre = new ConceptoDAOPostgre(gestorJDBC);
    }
    
       public int ingresarConcepto(Concepto concepto) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = conceptoDAOPostgre.ingresarConcepto(concepto);
            if(registros_afectados == 1)
                gestorJDBC.terminarTransaccion();
            return registros_afectados;  
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;                 
        }    
    }     

    public Concepto verificarConcepto(int idConcepto) throws Exception {
        Concepto concepto = null;
        gestorJDBC.abrirConexion();
        try {
            concepto = conceptoDAOPostgre.verificarConcepto(idConcepto);          
        } catch(Exception e){
            gestorJDBC.cerrarConexion();
            throw e;
        } 
        gestorJDBC.cerrarConexion();
        return concepto;
    }
   
    public Concepto verificarConceptoContrato(int idContrato) throws Exception {
        Concepto concepto = null;
        gestorJDBC.abrirConexion();
        try {
            concepto = conceptoDAOPostgre.verificarConceptoContrato(idContrato);          
        } catch(Exception e){
            gestorJDBC.cerrarConexion();
            throw e;
        } 
        gestorJDBC.cerrarConexion();
        return concepto;
    } 
    
    public List<Concepto> buscarConceptos(int idContrato) throws Exception{
        List<Concepto> listaConceptos = null;
        gestorJDBC.abrirConexion();
        try {            
            listaConceptos = conceptoDAOPostgre.buscarConceptos(idContrato);                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return listaConceptos;
    }          
        
        
        
    
}
