/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa6_Globales;

/**
 *
 * @author lucab
 */
public class Objeto {
    private String denominacion;  
    private int codigo;

    public Objeto(String denominacion, int codigo) {
        this.denominacion = denominacion;
        this.codigo = codigo;
    }

    public Objeto() {
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
 
    public String toString(){
        return denominacion;

    }
    
    public String getCodigoStr(){
        return String.valueOf(codigo);
    } 
}
