/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;

/**
 *
 * @author lucab
 */
public class SesionBD {
    
    private static String usuario;
    private static String passwordBD;

    private SesionBD() {
    }

    
    
    public static void asignarUsuario(String parUsuario) {
        usuario = parUsuario;
    }    
    
    public static String retornarUsuario() {
        return usuario;
    }

    public static void asignarPasswordBD(String parPassword) {
        passwordBD = parPassword;
    }     
    
    public static String retornarPasswordBD() {
        return passwordBD;
    }

}
