/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;

import java.sql.Date;


public class Boleta {
    
    int idboleta;
    Contrato contrato;
    Periodo periodo;
    Concepto concepto;
    Date fecha;
    int totalhoras;
    double valorhora;
    double asignacionfamiliar;   

    public Boleta(int idboleta, Contrato contrato, Periodo periodo, Concepto concepto, Date fecha, int totalhoras, double valorhora, double asignacionfamiliar) {
        this.idboleta = idboleta;
        this.contrato = contrato;
        this.periodo = periodo;
        this.concepto = concepto;
        this.fecha = fecha;
        this.totalhoras = totalhoras;
        this.valorhora = valorhora;
        this.asignacionfamiliar = asignacionfamiliar;
    }
    
    public Boleta(){
    }

    public Boleta(Contrato contrato, Periodo periodo, Concepto concepto, Date fecha, int totalhoras, double valorhora, double asignacionfamiliar) {
        this.contrato = contrato;
        this.periodo = periodo;
        this.concepto = concepto;
        this.fecha = fecha;
        this.totalhoras = totalhoras;
        this.valorhora = valorhora;
        this.asignacionfamiliar = asignacionfamiliar;
    }
    
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

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
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

    public double getAsignacionfamiliar() {
        return asignacionfamiliar;
    }

    public void setAsignacionfamiliar(double asignacionfamiliar) {
        this.asignacionfamiliar = asignacionfamiliar;
    }

// Metodos y Funciones (Reglas del negocio)
    

    public int calcularTotalHoras(){
        return periodo.calculartotalSemanasPeriodo() * contrato.getTotalhoras();   
    }
       
    public double calcularTotalIngresos(){
        return calcularSueldoBasico()+ contrato.cacularAsignacionFamiliar()+ concepto.calcularConceptoIngreso();
    }
    
    public double calcularSueldoBasico(){   
        return contrato.getValorhora() * contrato.getTotalhoras();
    }
    
     public double calcularSueldoNeto(){ 
       return calcularTotalIngresos()+calcularTotaldescuentos();
    }
     
    public double calcularTotaldescuentos(){
        return calcularAFP()+ concepto.calcularConceptoDescuento();
    }
    
    public double calcularAFP(){        
         return calcularSueldoBasico()* contrato.getAfp().getPorcentaje();
    }
        
    
}
