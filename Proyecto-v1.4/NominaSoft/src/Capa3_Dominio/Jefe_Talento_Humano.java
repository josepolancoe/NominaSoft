/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;


public class Jefe_Talento_Humano {
    int idjefe;
    double usuario;
    double contraseña;
    String nombre;

    public Jefe_Talento_Humano(int idjefe, double usuario, double contraseña, String nombre) {
        this.idjefe = idjefe;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
    }

    public int getIdjefe() {
        return idjefe;
    }

    public void setIdjefe(int idjefe) {
        this.idjefe = idjefe;
    }

    public double getUsuario() {
        return usuario;
    }

    public void setUsuario(double usuario) {
        this.usuario = usuario;
    }

    public double getContraseña() {
        return contraseña;
    }

    public void setContraseña(double contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
