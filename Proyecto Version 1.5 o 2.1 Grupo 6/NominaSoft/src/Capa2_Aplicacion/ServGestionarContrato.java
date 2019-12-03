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
   private ContratoDAOPostgre contratoDAOPostgre;
   private EmpleadoDAOPostgre empleadoDAOPostgre;
   private AfpDAOPostgre afpDAOPostgre;

   
    public ServGestionarContrato(){
       gestorJDBC = new ConexionDAOPostgre();
       contratoDAOPostgre = new ContratoDAOPostgre(gestorJDBC);
       empleadoDAOPostgre = new EmpleadoDAOPostgre(gestorJDBC);
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
    
    public Contrato VerificarContrato(int idContrato) throws Exception{
        Contrato contrato = null;
        gestorJDBC.abrirConexion();
        try {            
            contrato = contratoDAOPostgre.verificarContrato(idContrato);                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return contrato;
    } 
    
    public int ModificarContrato(Contrato contrato) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = contratoDAOPostgre.modificarContrato(contrato);
            if(registros_afectados == 1)
                gestorJDBC.terminarTransaccion();
                return registros_afectados;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
    
    public int AnularContrato(Contrato contrato) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = contratoDAOPostgre.anularContrato(contrato);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;             
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
    public List<Contrato> buscarContratosActivos() throws Exception{
        List<Contrato> listaContrato = null;
        gestorJDBC.abrirConexion();
        try {            
            listaContrato = contratoDAOPostgre.buscarContratosActivos();
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return listaContrato;
    }
    
}
