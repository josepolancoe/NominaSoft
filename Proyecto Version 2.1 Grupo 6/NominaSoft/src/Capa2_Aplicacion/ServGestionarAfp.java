/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa3_Dominio.Afp;
import Capa4_Persistencia.AfpDAOPostgre;
import Capa4_Persistencia.ConexionDAOPostgre;
import Capa4_Persistencia.GestorJDBC;
import java.util.List;

/**
 *
 * @author JOSEPOLANCO
 */
public class ServGestionarAfp {
    private GestorJDBC gestorJDBC;
    private AfpDAOPostgre afpDAOPostgre;

    public ServGestionarAfp(){
       gestorJDBC = new ConexionDAOPostgre();
       afpDAOPostgre = new AfpDAOPostgre(gestorJDBC);       
    }
    
    public int IngresarAfp(Afp afp) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = afpDAOPostgre.ingresarAfp(afp);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;  
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }    
    }
    
    public int ModificarAfp(Afp afp) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = afpDAOPostgre.modificarAfp(afp);
            if(registros_afectados == 1)
                gestorJDBC.terminarTransaccion();
                return registros_afectados;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
    public int EliminarAfp(Afp afp) throws Exception{
        gestorJDBC.abrirConexion();
        gestorJDBC.iniciarTransaccion();
        try {
            int registros_afectados = afpDAOPostgre.eliminarAfp(afp);
            if(registros_afectados == 1)
                   gestorJDBC.terminarTransaccion();
                   return registros_afectados;             
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }

    public List<Afp> BuscarAfps() throws Exception{
        List<Afp> listaAfps = null;
        gestorJDBC.abrirConexion();
        try {            
            listaAfps = afpDAOPostgre.buscarAfps();                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return listaAfps;
    }   
    
    public Afp VerificarAfp(int idAfp) throws Exception{
        Afp afp = null;
        gestorJDBC.abrirConexion();
        try {            
            afp = afpDAOPostgre.verificarAfp(idAfp);                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return afp;
    }
    
     public Afp VerificarNombreAfp(String nombre) throws Exception{
        Afp afp = null;
        gestorJDBC.abrirConexion();
        try {            
            afp = afpDAOPostgre.verificarNombreAfp(nombre);                        
        } catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
        gestorJDBC.cerrarConexion();
        return afp;
    }
}
