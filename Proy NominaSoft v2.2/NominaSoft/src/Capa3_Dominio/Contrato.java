/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;
import java.sql.Date;
import java.util.Calendar;

public class Contrato {
    private int idcontrato;
    private Empleado empleado;
    private Afp afp;
    private Date fechainiciocontrato;
    private Date fechafincontrato;
    private String cargo;
    private boolean asignacionfamiliar;
    private int totalhoras;
    private double valorhora;
    private String condicion;

    public Contrato(Empleado empleado, Afp afp, Date fechainiciocontrato, Date fechafincontrato, String cargo, boolean asignacionfamiliar, int totalhoras, double valorhora) {
        this.empleado = empleado;
        this.afp = afp;
        this.fechainiciocontrato = new Date(fechainiciocontrato.getTime());
        this.fechafincontrato = new Date(fechafincontrato.getTime());
        this.cargo = cargo;
        this.asignacionfamiliar = asignacionfamiliar;
        this.totalhoras = totalhoras;
        this.valorhora = valorhora;
    }
  
    
    public Contrato(int idcontrato, Empleado empleado, Afp afp, Date fechainiciocontrato, Date fechafincontrato, String cargo, boolean asignacionfamiliar, int totalhoras, double valorhora, String condicion) {
        this.idcontrato = idcontrato;
        this.empleado = empleado;
        this.afp = afp;
        this.fechainiciocontrato = new Date(fechainiciocontrato.getTime());
        this.fechafincontrato = new Date(fechafincontrato.getTime());
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
        if(fechainiciocontrato==null){
            return null;
        }else{
            return new Date(fechainiciocontrato.getTime());
        }
    }

    public void setFechainiciocontrato(Date fechainiciocontrato) {
        this.fechainiciocontrato= new Date( fechainiciocontrato.getTime());
    }

    public Date getFechafincontrato() {
        if(fechafincontrato==null){
            return null;
        }else{
            return new Date(fechafincontrato.getTime());
        }
    }

    public void setFechafincontrato(Date fechafincontrato) {
        this.fechafincontrato= new Date( fechafincontrato.getTime());
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
        if(asignacionfamiliar == true){
             return 930 * 0.1;
        }else
            return 0.0;
     }
    public Boolean verificarContratoVigente(){
     java.util.Date fechaActual = new java.util.Date();
        return fechafincontrato.getTime() >= fechaActual.getTime() && condicion.compareToIgnoreCase("ANULADO")!=0 && fechainiciocontrato.getTime() <= fechaActual.getTime(); 
    }
    
    public Boolean verificaEstadoContrato(){
        java.util.Date fechaActual = new java.util.Date();
        return fechafincontrato.getTime() >= fechaActual.getTime(); 
    }
    
    public Boolean verificarHorasContratadas(){
        return totalhoras >= 8 && totalhoras <= 40;
    }

    public Boolean verificarValorHora(){
        String gradoAcademico = empleado.getGradoacademico();
        if(gradoAcademico.equalsIgnoreCase("Primaria") && valorhora >=5 && valorhora <= 10){
            return true;
        }else if(gradoAcademico.equalsIgnoreCase("Secundaria") && valorhora >=5 && valorhora <= 10){    
            return true;
        }else if(gradoAcademico.equalsIgnoreCase("Bachiller") && valorhora >=11 && valorhora <= 20){
            return true;
        }else if(gradoAcademico.equalsIgnoreCase("Profesional") && valorhora >=21 && valorhora <= 30){
            return true;
        }else if(gradoAcademico.equalsIgnoreCase("Magister") && valorhora >=31  && valorhora <= 40){
            return true;
        }else return gradoAcademico.equalsIgnoreCase("Doctor") && valorhora >=41 && valorhora <= 60;   
    }    

    public Boolean verificarFechaInicioContratoNuevo(Contrato contratoAnterior){
        boolean respuesta = false;       
        if(contratoAnterior.getIdcontrato() != 0){
            if(contratoAnterior.getCondicion().equalsIgnoreCase("Anulado") ){
                respuesta = true;
            }else{
                if(contratoAnterior.getIdcontrato() == idcontrato){ 
                    respuesta = true;
                }else{              
                    Date fecha = contratoAnterior.getFechafincontrato();
                    if(fechainiciocontrato.getTime()> fecha.getTime()){
                        respuesta = true;
                    }else{
                        respuesta = false;
                    }
                }        
            }
        }else{
            respuesta = true;
        }
        return respuesta;
    }
    
 public Boolean verificarFechaFinContratoNuevo(){
     
            Calendar inicio = Calendar.getInstance();
            Calendar fin = Calendar.getInstance();
            inicio.setTime(fechainiciocontrato);
            fin.setTime(fechafincontrato);
            int dias=-1;
            
            while(inicio.before(fin)||inicio.equals(fin)){
                dias++;
                inicio.add(Calendar.DATE,1); 
            }
            return dias>=90 && 366>=dias;
        
	}

}
