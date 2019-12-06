/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa3_Dominio.SesionBD;
import Capa5_Excepcion.ExcepcionSQL;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JOSEPOLANCO
 */
public class ConexionDAOPostgre extends GestorJDBC {
    
    private String password = null;
    private static final String NOMBRE_BASEDATOS = "dbNomina";
    private static final String USUARIO_BASEDATOS = "postgres";
    private static final String URL_BASEDATOS = "jdbc:postgresql://localhost:5432/" + NOMBRE_BASEDATOS;

    @Override
    public void abrirConexion() throws Exception{  
        password = SesionBD.retornarPasswordBD();
        try {
            Class.forName("org.postgresql.Driver");            
            conexion = DriverManager.getConnection(URL_BASEDATOS, USUARIO_BASEDATOS, password);   
        } catch (ClassNotFoundException | SQLException e) {
            throw ExcepcionSQL.crearErrorAbrirConexion();
        }       
    }
    
    public int verificarConexion(String parUsuario, String parPassword) throws Exception{  
        int respuesta = 0;  
        try{
            boolean respuestaBase = verificarBaseDatos(parPassword);
            if(respuestaBase && parUsuario.equals("admin")){
                SesionBD.asignarPasswordBD(parPassword);
                respuesta = 1;
            }
        }catch (ClassNotFoundException | SQLException e) {
            throw ExcepcionSQL.crearErrorAbrirConexion();
        } 
        return respuesta;
    }
   
      public boolean verificarBaseDatos(String parPassword) throws Exception{  
        try{
            Class.forName("org.postgresql.Driver");          
            conexion = DriverManager.getConnection(URL_BASEDATOS, USUARIO_BASEDATOS, parPassword);
            if(conexion.isValid(0)){
                conexion.close();
                return true;
            }else{
                conexion.close();
                return false;
            }       
        }catch (ClassNotFoundException | SQLException e) {
            throw ExcepcionSQL.crearErrorAbrirConexion();
        }  
    }  
  
}
