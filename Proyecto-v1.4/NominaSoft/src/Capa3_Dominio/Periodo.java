/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;

import java.util.Calendar;
import java.sql.Date;



public class Periodo {
    int idperiodo; 
    String estado;
    Date fechainicioperiodo;
    Date fechafinperiodo;
    

    public Periodo(int idperiodo, String estado, Date fechainicioperiodo, Date fechafinperiodo) {
        this.idperiodo = idperiodo;
        this.estado = estado;
        this.fechainicioperiodo = fechainicioperiodo;
        this.fechafinperiodo = fechafinperiodo; 
        
    }

    public Periodo(Date fechainicioperiodo, Date fechafinperiodo) {
        this.fechainicioperiodo = fechainicioperiodo;
        this.fechafinperiodo = fechafinperiodo;
    }

    public Periodo() {
    }

    public int getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(int idperiodo) {
        this.idperiodo = idperiodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechainicioperiodo() {
        return fechainicioperiodo;
    }

    public void setFechainicioperiodo(Date fechainicioperiodo) {
        this.fechainicioperiodo = fechainicioperiodo;
    }

    public Date getFechafinperiodo() {
        return fechafinperiodo;
    }

    public void setFechafinperiodo(Date fechafinperiodo) {
        this.fechafinperiodo = fechafinperiodo;
    }
    
    public Boolean verificarPeriodoVigente(){   
        java.util.Date fechaActual = new java.util.Date();        
        if(fechafinperiodo.getTime() >= fechaActual.getTime() &&  estado.compareTo("PENDIENTE")==0){
            return true;
        }else         
            return false; 
    }

    public int calculartotalSemanasPeriodo(){
        int totalSemanas = 0;
        int dias = 0;
        int control = 0;

        Calendar calendarinicio = Calendar.getInstance();
        Calendar calendarfin = Calendar.getInstance();
        calendarinicio.setTime(fechainicioperiodo); 
        calendarfin.setTime(fechafinperiodo); 
        
        while(calendarinicio.before(calendarfin)|| calendarinicio.equals(calendarfin)){
            dias ++;
            if(Calendar.DAY_OF_WEEK == 2){
                control ++;
            } 
            calendarinicio.add(Calendar.DAY_OF_YEAR, 1);  
        }
        if(control*7 > dias){
            totalSemanas = control - 1;
        }else{
            totalSemanas = dias/7;
        }
        return totalSemanas; 
    }
    
}
