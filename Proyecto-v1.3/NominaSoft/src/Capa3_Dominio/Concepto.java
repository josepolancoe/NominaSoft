/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;


public class Concepto {
    int idconcepto;
    Contrato contrato;
    Periodo periodo;
    int horasextra;
    double reintegros;
    double otrosingresos;
    int horasausente;
    double adelantos;
    double otrosdescuentos;

    public Concepto(int idconcepto, Contrato contrato, Periodo periodo, int horasextra, double reintegros, double otrosingresos, int horasausente, double adelantos, double otrosdescuentos) {
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

    public int getHorasextra() {
        return horasextra;
    }

    public void setHorasextra(int horasextras) {
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

    public int getHorasausente() {
        return horasausente;
    }

    public void setHorasausente(int horasausente) {
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
 
    public double calcularConcepto_Descuento(){
       return horasausente + adelantos + otrosdescuentos;
    }
    
    public double calcularConcepto_Ingresar(){
        return horasextra + reintegros + otrosingresos;
    }
}
