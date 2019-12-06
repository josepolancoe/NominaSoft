/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa2_Aplicacion;

import Capa4_Persistencia.ConexionDAOPostgre;
import Capa4_Persistencia.GestorJDBC;

/**
 *
 * @author lucab
 */
public class ServGestionarSesion {
    
    protected ConexionDAOPostgre sesion;
    
    public ServGestionarSesion(){
        sesion = new ConexionDAOPostgre();      
    }
    
    public int verificarSesion(String parUsuario, String parContrasenia) throws Exception{          
        int respuesta = 0;      
            try {
                respuesta = sesion.verificarConexion(parUsuario, parContrasenia);
                if(respuesta == 1)
                    sesion.cerrarConexion();
            } catch (Exception e) {
                sesion.cerrarConexion();
                throw e;
            }
        return respuesta;
    }
}
