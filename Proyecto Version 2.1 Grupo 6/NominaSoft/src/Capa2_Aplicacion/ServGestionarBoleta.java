/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa3_Dominio.Boleta;
import Capa4_Persistencia.BoletaDAOPostgre;
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
public class ServGestionarBoleta {
    private GestorJDBC gestorJDBC;
    private BoletaDAOPostgre boletaDAOPostgre;
    private ContratoDAOPostgre contratoDAOPostgre;
    private PeriodoDAOPostgre periodoDAOPostgre;
    private ConceptoDAOPostgre conceptoDAOPostgre;

   
    public ServGestionarBoleta(){
       gestorJDBC = new ConexionDAOPostgre();
       boletaDAOPostgre = new BoletaDAOPostgre(gestorJDBC);
       contratoDAOPostgre = new ContratoDAOPostgre(gestorJDBC);
       periodoDAOPostgre = new PeriodoDAOPostgre(gestorJDBC);
       conceptoDAOPostgre = new ConceptoDAOPostgre(gestorJDBC);       
   }
    
       public int IngresarBoleta(Boleta boleta) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = boletaDAOPostgre.ingresarBoleta(boleta);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;  
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
                    
        }    
    } 
   
//    public List<Boleta> BuscarBoletas() throws Exception{
//        List<Boleta> listaBoletas = null;
//        gestorJDBC.abrirConexion();
//        try {            
//            listaBoletas = boletaDAOPostgre.buscarBoletas();                        
//        } catch (Exception e) {
//            gestorJDBC.cerrarConexion();
//            throw e;
//        }
//        gestorJDBC.cerrarConexion();
//        return listaBoletas;
//    } 
    
}
