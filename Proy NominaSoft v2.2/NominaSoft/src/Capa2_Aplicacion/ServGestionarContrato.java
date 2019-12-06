/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa3_Dominio.Contrato;
import Capa4_Persistencia.ConexionDAOPostgre;
import Capa4_Persistencia.ContratoDAOPostgre;
import Capa4_Persistencia.GestorJDBC;
import Capa5_Excepcion.ExcepcionRegla;
import Capa5_Excepcion.ExcepcionSQL;
import Capa5_Excepcion.Mensaje;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JOSEPOLANCO
 */
public class ServGestionarContrato {
   private GestorJDBC gestorJDBC;
   private ContratoDAOPostgre contratoDAOPostgre;

    /**
     *
     */
    public ServGestionarContrato(){
       gestorJDBC = new ConexionDAOPostgre();
       contratoDAOPostgre = new ContratoDAOPostgre(gestorJDBC);     
   }
   
    /**
     *
     * @param contrato
     * @return
     * @throws Exception
     */
    public int ingresarContrato(Contrato contrato) throws Exception{
        int registros_afectados = 0;
        if(validarContrato(contrato)){
            gestorJDBC.abrirConexion();
            gestorJDBC.iniciarTransaccion();
            try {
                registros_afectados = contratoDAOPostgre.ingresarContrato(contrato);
                if(registros_afectados == 1)
                    gestorJDBC.terminarTransaccion(); 
            } catch (Exception e) {
                gestorJDBC.cancelarTransaccion();
                throw e;
            }
        }
        return registros_afectados;
    }
    public int modificarContrato(Contrato contrato) throws Exception{
        int registros_afectados = 0;
        if (validarContrato(contrato)){
            gestorJDBC.abrirConexion();
            gestorJDBC.iniciarTransaccion();
            try {
                registros_afectados = contratoDAOPostgre.modificarContrato(contrato);   
                if(registros_afectados == 1)
                    gestorJDBC.terminarTransaccion();
            } catch (Exception e) {
                gestorJDBC.cancelarTransaccion();
                throw e;
            }    
        }
        return registros_afectados;
    }

    public int anularContrato(Contrato contrato) throws Exception{
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
    
    public Contrato verificarContratoPrevio(int parIdEmpleado){
        Contrato contratoPrevio = null;
        List<Contrato>  listaContratos =  null;
        int codigomayor = 0;
        try {
            listaContratos = buscarContratos(parIdEmpleado); 
            
            if(!listaContratos.isEmpty()){
                for(Contrato objetoContrato : listaContratos){
                    int codigo = objetoContrato.getIdcontrato();
                    if(codigo > codigomayor) codigomayor = codigo;
                }
            contratoPrevio = verificarContrato(codigomayor);
            }else{
                contratoPrevio = new Contrato();
                contratoPrevio.setIdcontrato(0); 
            }
        } catch (Exception e) {
            Mensaje.mostrarAfirmacion(null, Mensaje.OBJETO_NULO);
        }
        return contratoPrevio;
    } 
    

    public List<Contrato> buscarContratos(int idempleado) throws Exception{
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
    
    public Contrato verificarContrato(int idContrato) throws Exception{
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

    public int actualizarEstadoContrato(Contrato contrato) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {            
            int registros_afectados = contratoDAOPostgre.actualizarEstadoContrato(contrato);   
            if(registros_afectados == 1)
                gestorJDBC.terminarTransaccion();
            return registros_afectados;                         
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
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
    
    public List<Contrato> buscarContratosVigentes() throws Exception{
        List<Contrato> listaContratosPeriodo = new ArrayList<>();
        gestorJDBC.abrirConexion();
        try {            
            List<Contrato> listaContrato = contratoDAOPostgre.buscarContratosActivos();
            if(listaContrato.isEmpty()) {
                throw ExcepcionRegla.crearErrorSinContratosVigentes(); 
            }else{                    
                listaContrato.stream().filter((objetoContrato) -> (objetoContrato.verificarContratoVigente() == true )).forEachOrdered((objetoContrato) -> {
                    listaContratosPeriodo.add(objetoContrato);
                });
            }                
        } catch (ExcepcionRegla | ExcepcionSQL er) {
            Mensaje.mostrarAdvertencia(null, er.getMessage());  
            gestorJDBC.cerrarConexion();
        } 
        gestorJDBC.cerrarConexion();
        return listaContratosPeriodo;
    }        
    
    public Boolean validarContrato(Contrato contrato) throws ExcepcionRegla{
        Contrato contratoPrevio = null;
        boolean respuesta = false;
        if(contrato != null){
            contratoPrevio = verificarContratoPrevio(contrato.getEmpleado().getIdempleado());
            try{
                if(contrato.verificarFechaInicioContratoNuevo(contratoPrevio)== false){ 
                    throw ExcepcionRegla.crearErrorFechaInicio();
                }else if(contrato.verificarFechaFinContratoNuevo() == false){ 
                   throw ExcepcionRegla.crearErrorFechaFin();
                }else if(contrato.verificarHorasContratadas()== false){       
                    throw ExcepcionRegla.crearErrorHorasSemana();
                }else if(contrato.verificarValorHora() == false){
                    throw ExcepcionRegla.crearErrorValorHora(contrato.getEmpleado().devolverRangoSueldo());
                }else{ 
                    respuesta = true;
                }                                      
            } catch (ExcepcionRegla er) {
                Mensaje.mostrarAdvertencia(null, er.getMessage());  
                respuesta = false;
            } 
        }
        return respuesta;
    }       
}

