/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa4_Persistencia;

import Capa5_Excepcion.ExcepcionSQL;
import java.sql.DriverManager;

/**
 *
 * @author JOSEPOLANCO
 */
public class ConexionDAOPostgre extends GestorJDBC {
        
    public void abrirConexion() throws Exception{        
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/dbNomina";
            conexion = DriverManager.getConnection(url, "postgres", "junior10");   
        } catch (Exception e) {
            throw ExcepcionSQL.crearErrorAbrirConexion();
        }        
    }
}
