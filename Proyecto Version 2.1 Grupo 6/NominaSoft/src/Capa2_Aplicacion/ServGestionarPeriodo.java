/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa3_Dominio.Periodo;
import Capa4_Persistencia.ConexionDAOPostgre;
import Capa4_Persistencia.GestorJDBC;
import Capa4_Persistencia.PeriodoDAOPostgre;
import java.util.List;

/**
 *
 * @author JOSEPOLANCO
 */
public class ServGestionarPeriodo {
   private GestorJDBC gestorJDBC;
   private PeriodoDAOPostgre periodoDAOPostgre;


   
    public ServGestionarPeriodo(){
       gestorJDBC = new ConexionDAOPostgre();
       periodoDAOPostgre = new PeriodoDAOPostgre(gestorJDBC);     
   }
    
    public int IngresarPeriodo(Periodo periodo) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = periodoDAOPostgre.ingresarPeriodo(periodo);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;  
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }    
    }
      
    public int ModificarPeriodo(Periodo periodo) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = periodoDAOPostgre.modificarPeriodo(periodo);
            if(registros_afectados == 1)
                gestorJDBC.terminarTransaccion();
                return registros_afectados;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
    public int ProcesarPeriodo(Periodo periodo) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = periodoDAOPostgre.procesarPeriodo(periodo);
            if(registros_afectados == 1)
                gestorJDBC.terminarTransaccion();
                return registros_afectados;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }

    public int EliminarPeriodo(Periodo periodo) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = periodoDAOPostgre.eliminarPeriodo(periodo);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;             
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
    public Periodo BuscarPeriodo(int idperiodo) throws Exception {
        Periodo periodo = null;
        gestorJDBC.abrirConexion();
        try {
            periodo = periodoDAOPostgre.buscarPeriodo(idperiodo);
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return periodo;
    }

    public Periodo VerificarPeriodo(int idperiodo) throws Exception {
        Periodo periodo = null;
        gestorJDBC.abrirConexion();
        try {
            periodo = periodoDAOPostgre.verificarPeriodo(idperiodo);
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return periodo;
    }
    
    public List<Periodo> BuscarPeriodos() throws Exception{
        List<Periodo> listaPeriodos = null;
        gestorJDBC.abrirConexion();
        try {            
            listaPeriodos = periodoDAOPostgre.buscarPeriodos();                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return listaPeriodos;
    } 
}
