/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;


public class Afp {
    
    int idafp;
    String nombre;
    double porcentaje;

    public Afp(int idafp, String nombre, double porcentaje) {
        this.idafp = idafp;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }
    
    public Afp(String nombre, double porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public Afp() {
    }

    public int getIdafp() {
        return idafp;
    }

    public void setIdafp(int idafp) {
        this.idafp = idafp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    
}
