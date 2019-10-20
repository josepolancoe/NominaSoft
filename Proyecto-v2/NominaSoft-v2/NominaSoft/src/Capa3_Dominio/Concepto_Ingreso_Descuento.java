/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;


public class Concepto_Ingreso_Descuento {
    int idconceptoingresodescuento;
    Contrato contrato;
    Periodo periodo;
    int horasextras;
    double reintegros;
    double otrosingresos;
    int horasausente;
    double adelantos;
    double otrosdescuentos;

    public Concepto_Ingreso_Descuento(int idconceptoingresodescuento, Contrato contrato, Periodo periodo, int horasextras, double reintegros, double otrosingresos, int horasausente, double adelantos, double otrosdescuentos) {
        this.idconceptoingresodescuento = idconceptoingresodescuento;
        this.contrato = contrato;
        this.periodo = periodo;
        this.horasextras = horasextras;
        this.reintegros = reintegros;
        this.otrosingresos = otrosingresos;
        this.horasausente = horasausente;
        this.adelantos = adelantos;
        this.otrosdescuentos = otrosdescuentos;
    }

    public int getIdconceptoingresodescuento() {
        return idconceptoingresodescuento;
    }

    public void setIdconceptoingresodescuento(int idconceptoingresodescuento) {
        this.idconceptoingresodescuento = idconceptoingresodescuento;
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

    public int getHorasextras() {
        return horasextras;
    }

    public void setHorasextras(int horasextras) {
        this.horasextras = horasextras;
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
        double concedes=0;
        concedes= horasausente + adelantos + otrosdescuentos;
        return concedes;

    }
    public double calcularConcepto_Ingresar(){
        double concedes=0;
        concedes= horasextras + reintegros + otrosdescuentos;
        return concedes;

    }
}
