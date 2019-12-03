/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa3_Dominio.Contrato;
import Capa4_Persistencia.AfpDAOPostgre;
import Capa4_Persistencia.ConexionDAOPostgre;
import Capa4_Persistencia.ContratoDAOPostgre;
import Capa4_Persistencia.EmpleadoDAOPostgre;
import Capa4_Persistencia.GestorJDBC;
import java.util.List;

/**
 *
 * @author JOSEPOLANCO
 */
public class ServGestionarContrato {
   private GestorJDBC gestorJDBC;
   private EmpleadoDAOPostgre empleadoDAOPostgre;
   private ContratoDAOPostgre contratoDAOPostgre;
   private AfpDAOPostgre afpDAOPostgre;

   
    public ServGestionarContrato(){
       gestorJDBC = new ConexionDAOPostgre();
       empleadoDAOPostgre = new EmpleadoDAOPostgre(gestorJDBC);
       contratoDAOPostgre = new ContratoDAOPostgre(gestorJDBC);
       afpDAOPostgre = new AfpDAOPostgre(gestorJDBC);       
   }
   
    public int IngresarContrato(Contrato contrato) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = contratoDAOPostgre.ingresarContrato(contrato);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;  
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }    
    } 
   
    public List<Contrato> BuscarContratos(int idempleado) throws Exception{
        List<Contrato> listaContratos = null;
        gestorJDBC.abrirConexion();
        try {            
            listaContratos = contratoDAOPostgre.buscarContratos(idempleado);                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return listaContratos;
    }  
}
