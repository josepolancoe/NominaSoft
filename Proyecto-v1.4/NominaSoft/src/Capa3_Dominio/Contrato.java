/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class Contrato {
    int idcontrato;
    Empleado empleado;
    Afp afp;
    Date fechainiciocontrato;
    Date fechafincontrato;
    String cargo;
    boolean asignacionfamiliar;
    int totalhoras;
    double valorhora;
    String condicion;

    public Contrato(Empleado empleado, Afp afp, Date fechainiciocontrato, Date fechafincontrato, String cargo, boolean asignacionfamiliar, int totalhoras, double valorhora) {
        this.empleado = empleado;
        this.afp = afp;
        this.fechainiciocontrato = fechainiciocontrato;
        this.fechafincontrato = fechafincontrato;
        this.cargo = cargo;
        this.asignacionfamiliar = asignacionfamiliar;
        this.totalhoras = totalhoras;
        this.valorhora = valorhora;
    }
  
    
    public Contrato(int idcontrato, Empleado empleado, Afp afp, Date fechainiciocontrato, Date fechafincontrato, String cargo, boolean asignacionfamiliar, int totalhoras, double valorhora, String condicion) {
        this.idcontrato = idcontrato;
        this.empleado = empleado;
        this.afp = afp;
        this.fechainiciocontrato = fechainiciocontrato;
        this.fechafincontrato = fechafincontrato;
        this.cargo = cargo;
        this.asignacionfamiliar = asignacionfamiliar;
        this.totalhoras = totalhoras;
        this.valorhora = valorhora;
        this.condicion = condicion;
    }

    public Contrato() {
    }

    
    public int getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Afp getAfp() {
        return afp;
    }

    public void setAfp(Afp afp) {
        this.afp = afp;
    }

    public Date getFechainiciocontrato() {
        return fechainiciocontrato;
    }

    public void setFechainiciocontrato(Date fechainiciocontrato) {
        this.fechainiciocontrato = fechainiciocontrato;
    }

    public Date getFechafincontrato() {
        return fechafincontrato;
    }

    public void setFechafincontrato(Date fechafincontrato) {
        this.fechafincontrato = fechafincontrato;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean getAsignacionfamiliar() {
        return asignacionfamiliar;
    }

    public void setAsignacionfamiliar(boolean asignacionfamiliar) {
        this.asignacionfamiliar = asignacionfamiliar;
    }

    public int getTotalhoras() {
        return totalhoras;
    }

    public void setTotalhoras(int totalhoras) {
        this.totalhoras = totalhoras;
    }

    public double getValorhora() {
        return valorhora;
    }

    public void setValorhora(double valorhora) {
        this.valorhora = valorhora;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    
     public double cacularAsignacionFamiliar(){
        if(asignacionfamiliar==true){
             return 930*0.1;
        }else
            return 0.0;
     }
    public Boolean verificarContratoVigente(){
     java.util.Date fechaActual = new java.util.Date();
     if(fechafincontrato.getTime() >= fechaActual.getTime() && condicion.compareTo("ANULADO")!=0){       
        return true;
     }else         
         return false; 
    }
    
    public Boolean verificaEstadoContrato(){
        java.util.Date fechaActual = new java.util.Date();
        if(fechafincontrato.getTime() >= fechaActual.getTime()){
        return true;
     }else         
         return false; 
    }
    
    public Boolean verificarHorasContratadas(){
        if(totalhoras >= 8 && totalhoras <= 40)
            return true;
        else
            return false;
    }
    public Boolean verificarValorHora(){
        String gradoAcademico = empleado.getGradoacademico();
        double valorHora = valorhora;
        boolean respuesta = true;       
	switch (gradoAcademico) {
		case "Primaria":
                        if(valorHora >=5 && valorHora <= 10){
                            respuesta = true; 
                        }else{
                            respuesta = false;
                        }	
		case "Secundaria":
                        if(valorHora >=5 && valorHora <= 10){
                            respuesta = true;
                            break;
                        }else{
                            respuesta = false;
                            break;
                        }
		case "Bachiller":
                        if (valorHora >=11 && valorHora <= 20){
                            respuesta = true;
                            break;
                        }else{
                            respuesta = false;
                            break;
                        }
		case "Profesional":
                        if (valorHora >=21 && valorHora <= 30){
                            respuesta = true;
                            break;
                        }else{
                            respuesta = false;
                            break;
                        }
		case "Magister":
                        if (valorHora >=31  && valorHora <= 40){
                            respuesta = true;
                            break;
                        }else{
                            respuesta = false;
                            break;
                        }
                case "Doctor":
                        if (valorHora >=41 && valorHora <= 60){
                            respuesta = true;
                            break;
                        }else{
                            respuesta = false;
                            break;
                        }        
	}
        return respuesta;
  
    }
    
    public Boolean verificarFechaInicioContratoNuevo(Date fecha){
        if(fechainiciocontrato.getTime()> fecha.getTime()){
        return true;
        }
        return false;
    }
      
 public Boolean verificarFechaFinContratoNuevo(){
        
        Calendar fechaInicio = new GregorianCalendar();
        fechaInicio.setTime(fechainiciocontrato);
        int mesInicio = fechaInicio.get(Calendar.MONTH) + 1;
        int anioInicio = fechaInicio.get(Calendar.YEAR);
        
        Calendar fechaFin = new GregorianCalendar();
        fechaFin.setTime(fechafincontrato);
        int mesFin = fechaFin.get(Calendar.MONTH) + 1;
        int anioFin = fechaFin.get(Calendar.YEAR);
        
        int difereciaAños = anioFin - anioInicio;
        int diferenciaMeses = difereciaAños * 12 + mesFin - mesInicio;

        if(diferenciaMeses >= 3 && diferenciaMeses <= 12){
            return true;
        }else
             return false;
	} 
     
}
