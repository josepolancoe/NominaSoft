/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa3_Dominio.Boleta;
import Capa4_Persistencia.BoletaDAOPostgre;
import Capa4_Persistencia.ConexionDAOPostgre;
import Capa4_Persistencia.GestorJDBC;

/**
 *
 * @author JOSEPOLANCO
 */
public class ServGestionarBoleta {
    private GestorJDBC gestorJDBC;
    private BoletaDAOPostgre boletaDAOPostgre;

   
    public ServGestionarBoleta(){
       gestorJDBC = new ConexionDAOPostgre();
       boletaDAOPostgre = new BoletaDAOPostgre(gestorJDBC);  
   }
    
       public int ingresarBoleta(Boleta boleta) throws Exception{
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
    
}
