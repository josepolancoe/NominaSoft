/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;

import Capa2_Aplicacion.ServGestionarContrato;
import Capa2_Aplicacion.ServGestionarPeriodo;
import Capa5_Excepcion.Mensaje;
import java.util.Calendar;
import java.util.Date;


public class Boleta {
    
    int idboleta;
    Contrato contrato;
    Periodo periodo;
    Concepto conceptoingresodescuento;
    Date fecha;
    int totalhoras;
    double valorhora;
    boolean asignacionfamiliar;
    double porcentajedesc_AFP;

    public Boleta(int idboleta, Contrato contrato, Periodo periodo, Concepto conceptoingresodescuento, Date fecha, int totalhoras, double valorhora, boolean asignacionfamiliar, double porcentajedesc_AFP) {
        this.idboleta = idboleta;
        this.contrato = contrato;
        this.periodo = periodo;
        this.conceptoingresodescuento = conceptoingresodescuento;
        this.fecha = fecha;
        this.totalhoras = totalhoras;
        this.valorhora = valorhora;
        this.asignacionfamiliar = asignacionfamiliar;
        this.porcentajedesc_AFP = porcentajedesc_AFP;
    }
    public Boleta(){}

    public int getIdboleta() {
        return idboleta;
    }

    public void setIdboleta(int idboleta) {
        this.idboleta = idboleta;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Concepto getConceptoingresodescuento() {
        return conceptoingresodescuento;
    }

    public void setConceptoingresodescuento(Concepto conceptoingresodescuento) {
        this.conceptoingresodescuento = conceptoingresodescuento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public boolean getAsignacionfamiliar() {
        return asignacionfamiliar;
    }

    public void setAsignacionfamiliar(boolean asignacionfamiliar) {
        this.asignacionfamiliar = asignacionfamiliar;
    }

    public double getPorcentajedesc_AFP() {
        return porcentajedesc_AFP;
    }

    public void setPorcentajedesc_AFP(double porcentajedesc_AFP) {
        this.porcentajedesc_AFP = porcentajedesc_AFP;
    }

// Metodos y Funciones (Reglas del negocio)
    

    public double calcularTotalHoras(){
        return periodo.calculartotalSemanasPeriodo() * contrato.totalhoras;   
    }
    
    
    public double calcularTotalIngresos(){
        return calcularSueldoBasico()+ contrato.cacularAsignacionFamiliar()+ conceptoingresodescuento.calcularConcepto_Ingresar();
    }
    
    public double calcularSueldoBasico(){   
        return contrato.valorhora * calcularTotalHoras();
    }
    
     public double calcularSueldoNeto(){ 
       return calcularTotalIngresos()+calcularTotaldescuentos();
    }
     
    public double calcularTotaldescuentos(){
        return calcularAFP()+ conceptoingresodescuento.calcularConcepto_Descuento();
    }
    
    public double calcularAFP(){        
         return calcularSueldoBasico()*porcentajedesc_AFP;
    }
        
    
}
