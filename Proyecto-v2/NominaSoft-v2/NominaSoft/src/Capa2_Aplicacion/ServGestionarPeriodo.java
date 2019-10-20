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
    
        public Periodo BuscarPeriodo(int idPeriodo) throws Exception{
        Periodo periodo = null;
        gestorJDBC.abrirConexion();
        try {            
            periodo = periodoDAOPostgre.buscarPeriodo(idPeriodo);                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return periodo;
    } 
}
