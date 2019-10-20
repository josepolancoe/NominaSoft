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
}
