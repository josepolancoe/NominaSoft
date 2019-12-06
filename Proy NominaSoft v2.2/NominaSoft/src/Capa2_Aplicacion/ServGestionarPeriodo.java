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
import Capa5_Excepcion.ExcepcionRegla;
import Capa5_Excepcion.Mensaje;
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
    
    public int ingresarPeriodo(Periodo periodo) throws Exception{
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
      
    public int modificarPeriodo(Periodo periodo) throws Exception{
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
    
    public int procesarPeriodo(Periodo periodo) throws Exception{
        int registros_afectados = 0;
        if(validarPeriodo(periodo)){
            gestorJDBC.abrirConexion();
            gestorJDBC.iniciarTransaccion();
            try {
                    registros_afectados = periodoDAOPostgre.procesarPeriodo(periodo);
                    if(registros_afectados == 1)
                        gestorJDBC.terminarTransaccion();
            } catch (Exception e) {
                gestorJDBC.cancelarTransaccion();
                throw e;
            }
        }
         return registros_afectados;
    }

    public Boolean validarPeriodo(Periodo parPeriodo) throws ExcepcionRegla{
        boolean respuesta = false;
        if(parPeriodo != null){
            try {
                if(parPeriodo.verificarProcesoPeriodoVigente()==false){         
                     throw ExcepcionRegla.crearErrorFechaParaProcesarNoValida();                        
                }else{
                    respuesta = true;
                }
            }catch (ExcepcionRegla er) {
               Mensaje.mostrarAdvertencia(null, er.getMessage());
            }
        }
        return respuesta;
    } 
    
    public int eliminarPeriodo(Periodo periodo) throws Exception{
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
    
    public Periodo verificarPeriodoVigente() throws Exception {
        List<Periodo> listaPeriodos;
        Periodo periodo = null;       
        listaPeriodos = buscarPeriodos();           
        if (!listaPeriodos.isEmpty()) {
            for (Periodo objetoPeriodo : listaPeriodos) {
                if (objetoPeriodo.verificarPeriodoVigente()) {
                    periodo = objetoPeriodo;                   
                    break;
                }
            }
            try {
                if(periodo == null){
                    throw ExcepcionRegla.crearErrorSinPeriodoActivo();
                }       
            } catch (ExcepcionRegla er) {
                Mensaje.mostrarAdvertencia(null, er.getMessage());  
            }
        }
        return periodo;
    }

    public Periodo verificarPeriodo(int idperiodo) throws Exception {
        Periodo periodo = null;
        gestorJDBC.abrirConexion();
        try {
            periodo = periodoDAOPostgre.devolverPeriodo(idperiodo);
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return periodo;
    }
    
    public List<Periodo> buscarPeriodos() throws Exception{
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
