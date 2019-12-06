/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa6_Globales;

import java.util.List;

/**
 *
 * @author lucab
 */
public class Objeto {
    private String denominacion;  
    private int codigo;
    private double numero;
    private List<Integer> rango;
    private List<Objeto> lista;

    public Objeto(String denominacion, int codigo) {
        this.denominacion = denominacion;
        this.codigo = codigo;
    }

    public Objeto(String denominacion, int codigo, double numero) {
        this.denominacion = denominacion;
        this.codigo = codigo;
        this.numero = numero;
    }

    public Objeto(String denominacion, int codigo, List rango) {
        this.denominacion = denominacion;
        this.codigo = codigo;
        this.lista = rango;
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

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }
 
    @Override
    public String toString(){
        return denominacion;
    }
    
    public String getCodigoStr(){
        return String.valueOf(codigo);
    } 

    public List<Integer> getRango() {
        return rango;
    }

    public void setRango(List<Integer> rango) {
        this.rango = rango;
    }

    public List<Objeto> getLista() {
        return lista;
    }

    public void setLista(List<Objeto> lista) {
        this.lista = lista;
    }

    
    
//------------------------------------------------------------------
    
//    public void agregarElementoList(Objeto elemento){
//        lista.add(elemento);
//    }
//    
//    public void eliminarElementoList(int indice){
//        lista.remove(indice);
//    }
//    
//    public void editarElementoList(int indice, Objeto elemento){
//        lista.set(indice, elemento);
//    }
//    
//    public Objeto retornaElementoList(int indice){
//        return lista.get(indice);
//    }
//    
//    public int retornaIndiceElementoList(Objeto elemento){
//        return lista.indexOf(elemento);
//    }
//    
//    public int tamañoList(){
//        return lista.size();
//    }
//    
}
