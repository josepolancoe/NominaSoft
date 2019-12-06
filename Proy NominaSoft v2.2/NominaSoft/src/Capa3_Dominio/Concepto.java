/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;


public class Concepto {
    private int idconcepto;
    private Contrato contrato;
    private Periodo periodo;
    private double horasextra;
    private double reintegros;
    private double otrosingresos;
    private double horasausente;
    private double adelantos;
    private double otrosdescuentos;

    public Concepto(int idconcepto, Contrato contrato, Periodo periodo, double horasextra, double reintegros, double otrosingresos, double horasausente, double adelantos, double otrosdescuentos) {
        this.idconcepto = idconcepto;
        this.contrato = contrato;
        this.periodo = periodo;
        this.horasextra = horasextra;
        this.reintegros = reintegros;
        this.otrosingresos = otrosingresos;
        this.horasausente = horasausente;
        this.adelantos = adelantos;
        this.otrosdescuentos = otrosdescuentos;
    }

    public Concepto(Contrato contrato, Periodo periodo, double horasextra, double reintegros, double otrosingresos, double horasausente, double adelantos, double otrosdescuentos) {
        this.contrato = contrato;
        this.periodo = periodo;
        this.horasextra = horasextra;
        this.reintegros = reintegros;
        this.otrosingresos = otrosingresos;
        this.horasausente = horasausente;
        this.adelantos = adelantos;
        this.otrosdescuentos = otrosdescuentos;
    }    
    
    public Concepto() {
    }
    
    public int getIdconcepto() {
        return idconcepto;
    }

    public void setIdconcepto(int idconceptoingresodescuento) {
        this.idconcepto = idconceptoingresodescuento;
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

    public double getHorasextra() {
        return horasextra;
    }

    public void setHorasextra(double horasextras) {
        this.horasextra = horasextras;
    }

    public double getReintegros() {
        return reintegros;
    }

    public void setReintegros(double reintegros) {
        this.reintegros = reintegros;
    }

    public double getOtrosingresos() {
        return otrosingresos;
    }

    public void setOtrosingresos(double otrosingresos) {
        this.otrosingresos = otrosingresos;
    }

    public double getHorasausente() {
        return horasausente;
    }

    public void setHorasausente(double horasausente) {
        this.horasausente = horasausente;
    }

    public double getAdelantos() {
        return adelantos;
    }

    public void setAdelantos(double adelantos) {
        this.adelantos = adelantos;
    }

    public double getOtrosdescuentos() {
        return otrosdescuentos;
    }

    public void setOtrosdescuentos(double otrosdescuentos) {
        this.otrosdescuentos = otrosdescuentos;
    }
 
    public double calcularConceptoDescuento(){
       return horasausente + adelantos + otrosdescuentos;
    }
    
    public double calcularConceptoIngreso(){
        return horasextra + reintegros + otrosingresos;
    }
}
