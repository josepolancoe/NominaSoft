/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;

import java.util.Calendar;
import java.sql.Date;
import java.text.SimpleDateFormat;



public class Periodo {
    private int idperiodo; 
    private String estado;
    private Date fechainicioperiodo;
    private Date fechafinperiodo;
    

    public Periodo(int idperiodo, String estado, Date fechainicioperiodo, Date fechafinperiodo) {
        this.idperiodo = idperiodo;
        this.estado = estado;
        this.fechainicioperiodo = new Date(fechainicioperiodo.getTime());
        this.fechafinperiodo = new Date(fechafinperiodo.getTime()); 
        
    }

    public Periodo(Date fechainicioperiodo, Date fechafinperiodo) {
        this.fechainicioperiodo = new Date(fechainicioperiodo.getTime());
        this.fechafinperiodo = new Date(fechafinperiodo.getTime());
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
        if(fechainicioperiodo==null){
           return null;
        }else{
            return new Date(fechainicioperiodo.getTime());
        }
    }

    public void setFechainicioperiodo(Date fechainicioperiodo) {
        this.fechainicioperiodo = new Date(fechainicioperiodo.getTime());
    }

    public Date getFechafinperiodo() {
        if(fechafinperiodo==null){
           return null;
        }else{
            return new Date(fechafinperiodo.getTime());
        }
    }

    public void setFechafinperiodo(Date fechafinperiodo) {
        this.fechafinperiodo = new Date(fechafinperiodo.getTime());
    }
    
    public Boolean verificarPeriodoVigente(){   
        boolean respuesta = false;
        java.util.Date fechaActual = new java.util.Date(); 
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSistemaSDF = formateador.format(fechaActual);
        String fechafinperiodoSDF = formateador.format(fechafinperiodo); 
        if(fechafinperiodo.getTime() > fechaActual.getTime() || fechaSistemaSDF.equalsIgnoreCase(fechafinperiodoSDF)){
            respuesta = estado.compareTo("ACTIVO")==0;
        }
        return respuesta;
    }
     
    public Boolean verificarProcesoPeriodoVigente(){   
        java.util.Date fechaActual = new java.util.Date();        
        return fechafinperiodo.getTime() <= fechaActual.getTime(); 
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
