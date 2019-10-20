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

    public Periodo(String estado, Date fechainicioperiodo, Date fechafinperiodo) {
        this.estado = estado;
        this.fechainicioperiodo = fechainicioperiodo;
        this.fechafinperiodo = fechafinperiodo;
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
    
    public int totalSemanasPeriodo(){
        int totalSemanas = 0;
        int dias = 0;
        
        Calendar calendarinicio = Calendar.getInstance();
        Calendar calendarfin = Calendar.getInstance();
        calendarinicio.setTime(fechainicioperiodo); 
        calendarfin.setTime(fechafinperiodo); 
        
        while(calendarinicio.before(calendarfin)|| calendarinicio.equals(calendarfin)){
            dias ++;
            calendarinicio.add(Calendar.DAY_OF_YEAR, 1);  
        }
        
        if (dias > 0){
        totalSemanas = dias/7;
        }

        return totalSemanas; 
    }
    
}
