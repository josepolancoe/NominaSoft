/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Junior
 */
public class BoletaTest {
    
   //Prueba unitaria del metodo calcular el total de horas
    @Test
    public void testCalcularTotalHoras() {
        System.out.println("calcularTotalHoras");
      
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-08-01");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-06-01");
        Boleta instance = new Boleta();
        Contrato contrato = new Contrato();
        Periodo periodo = new Periodo();
        instance.setContrato(contrato);
        instance.setPeriodo(periodo);
        contrato.setTotalhoras(8);
        periodo.setFechainicioperiodo(fechaInicioContratoNuevo);
        periodo.setFechafinperiodo(fechaFinContratoPrevio);
        double expResult = 64;
        double result = instance.calcularTotalHoras();
        assertEquals(expResult, result, 0.0);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    
    //Primera prueba de calcular total de ingresos cuando se cuenta con asignacion familiar
    @Test
    public void test1CalcularTotalIngresos() {
        System.out.println("calcularTotalIngresos");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Concepto concepto = new Concepto();
        boleta.setContrato(contrato);
        boleta.setConcepto(concepto);
        contrato.setTotalhoras(8);
        contrato.setAsignacionfamiliar(true);
        contrato.setValorhora(2);
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        double expResult = 124;
        double result = boleta.calcularTotalIngresos();
        assertEquals(expResult, result, 0.0);
       
    }
        
    //Primera prueba de calcular total de ingresos cuando no se cuenta con asignacion familiar
      @Test
    public void test2CalcularTotalIngresos() {
        System.out.println("calcularTotalIngresos");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Concepto concepto = new Concepto();
        boleta.setContrato(contrato);
        boleta.setConcepto(concepto);
        contrato.setTotalhoras(8);
        contrato.setAsignacionfamiliar(false);
        contrato.setValorhora(2);
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        double expResult = 31;
        double result = boleta.calcularTotalIngresos();
        assertEquals(expResult, result, 0.0);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
   //Prueba unitaria de calcular sueldo basico
    @Test
    public void testCalcularSueldoBasico() {
        System.out.println("calcularSueldoBasico");
        Boleta instance = new Boleta();
        Contrato contrato = new Contrato();
        instance.setContrato(contrato);
        contrato.setTotalhoras(8);
        contrato.setValorhora(2);
        double expResult = 16;
        double result = instance.calcularSueldoBasico();
        assertEquals(expResult, result, 0.0);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    //Primera prueba de calcular sueldo neto cuando se cuenta con asignacion familiar
    @Test
    public void test1CalcularSueldoNeto() {
        System.out.println("calcularSueldoNeto");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Concepto concepto = new Concepto();
        boleta.setContrato(contrato);
        boleta.setConcepto(concepto);
        contrato.setTotalhoras(8);
        Afp afp = new Afp();
        afp.setPorcentaje(0.1);
        contrato.setAfp(afp);
        contrato.setAsignacionfamiliar(false);
        contrato.setValorhora(2);
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        concepto.setHorasausente(2);
        concepto.setAdelantos(5);
        concepto.setOtrosdescuentos(10);
        double expResult = 49.6;
        double result = boleta.calcularSueldoNeto();
        assertEquals(expResult, result, 0.0);
    }
    
    //Primera prueba de calcular sueldo neto cuando no se cuenta con asignacion familiar
    @Test
    public void test2CalcularSueldoNeto() {
        System.out.println("calcularSueldoNeto");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Concepto concepto = new Concepto();
        boleta.setContrato(contrato);
        boleta.setConcepto(concepto);
        contrato.setTotalhoras(8);
        Afp afp = new Afp();
        afp.setPorcentaje(0.1);
        contrato.setAfp(afp);
        contrato.setAsignacionfamiliar(true);
        contrato.setValorhora(2);
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        concepto.setHorasausente(2);
        concepto.setAdelantos(5);
        concepto.setOtrosdescuentos(10);
        double expResult = 142.6;
        double result = boleta.calcularSueldoNeto();
        assertEquals(expResult, result, 0.0);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    //Prueba unitaria de calcular total descuentos
    @Test
    public void testCalcularTotaldescuentos() {
        System.out.println("calcularTotaldescuentos");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Concepto concepto = new Concepto();
        boleta.setConcepto(concepto);
        boleta.setContrato(contrato);
        contrato.setTotalhoras(8);
        Afp afp = new Afp();
        afp.setPorcentaje(0.1);
        contrato.setAfp(afp);
        contrato.setValorhora(2);
        concepto.setHorasausente(2);
        concepto.setAdelantos(5);
        concepto.setOtrosdescuentos(10);
        double expResult = 18.6;
        double result = boleta.calcularTotaldescuentos();
        assertEquals(expResult, result, 0.0);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    //Prueba unitaria del metodo calcular AFP
    @Test
    public void testCalcularAFP() {
        System.out.println("calcularAFP");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        boleta.setContrato(contrato);
        contrato.setTotalhoras(8);
        Afp afp = new Afp();
        afp.setPorcentaje(0.1);
        contrato.setAfp(afp);
        contrato.setValorhora(2);
        double expResult = 1.6;
        double result = boleta.calcularAFP();
        assertEquals(expResult, result, 0.0);
    }

}
