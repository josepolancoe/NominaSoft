/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;

import java.util.Date;
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
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

   
    
    //Primera prueba de calcular total de ingresos cuando se cuenta con asignacion familiar
    @Test
    public void test1CalcularTotalIngresos() {
        System.out.println("calcularTotalIngresos");
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-08-01");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-06-01");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Periodo periodo = new Periodo();
        Concepto concepto = new Concepto();
        boleta.setContrato(contrato);
        boleta.setPeriodo(periodo);
        boleta.setConceptoingresodescuento(concepto);
        contrato.setTotalhoras(8);
        periodo.setFechainicioperiodo(fechaInicioContratoNuevo);
        periodo.setFechafinperiodo(fechaFinContratoPrevio);
        contrato.setAsignacionfamiliar(true);
        contrato.setValorhora(2);
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        double expResult = 236;
        double result = boleta.calcularTotalIngresos();
        assertEquals(expResult, result, 0.0);
       
    }
    
    
    //Primera prueba de calcular total de ingresos cuando no se cuenta con asignacion familiar
      @Test
    public void test2CalcularTotalIngresos() {
        System.out.println("calcularTotalIngresos");
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-08-01");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-06-01");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Periodo periodo = new Periodo();
        Concepto concepto = new Concepto();
        boleta.setContrato(contrato);
        boleta.setPeriodo(periodo);
        boleta.setConceptoingresodescuento(concepto);
        contrato.setTotalhoras(8);
        periodo.setFechainicioperiodo(fechaInicioContratoNuevo);
        periodo.setFechafinperiodo(fechaFinContratoPrevio);
        contrato.setAsignacionfamiliar(false);
        contrato.setValorhora(2);
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        double expResult = 143;
        double result = boleta.calcularTotalIngresos();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

   //Prueba unitaria de calcular sueldo basico
    @Test
    public void testCalcularSueldoBasico() {
        System.out.println("calcularSueldoBasico");
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
        contrato.setValorhora(2);
        double expResult = 128;
        double result = instance.calcularSueldoBasico();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    //Primera prueba de calcular sueldo neto cuando se cuenta con asignacion familiar
    @Test
    public void test1CalcularSueldoNeto() {
        System.out.println("calcularSueldoNeto");
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-08-01");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-06-01");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Periodo periodo = new Periodo();
        Concepto concepto = new Concepto();
        boleta.setContrato(contrato);
        boleta.setPeriodo(periodo);
        boleta.setConceptoingresodescuento(concepto);
        contrato.setTotalhoras(8);
        periodo.setFechainicioperiodo(fechaInicioContratoNuevo);
        periodo.setFechafinperiodo(fechaFinContratoPrevio);
        contrato.setAsignacionfamiliar(false);
        contrato.setValorhora(2);
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        boleta.setPorcentajedesc_AFP(0.1);
        concepto.setHorasausente(2);
        concepto.setAdelantos(5);
        concepto.setOtrosdescuentos(10);
        double expResult = 172.8;
        double result = boleta.calcularSueldoNeto();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Primera prueba de calcular sueldo neto cuando no se cuenta con asignacion familiar
    @Test
    public void test2CalcularSueldoNeto() {
        System.out.println("calcularSueldoNeto");
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-08-01");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-06-01");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Periodo periodo = new Periodo();
        Concepto concepto = new Concepto();
        boleta.setContrato(contrato);
        boleta.setPeriodo(periodo);
        boleta.setConceptoingresodescuento(concepto);
        contrato.setTotalhoras(8);
        periodo.setFechainicioperiodo(fechaInicioContratoNuevo);
        periodo.setFechafinperiodo(fechaFinContratoPrevio);
        contrato.setAsignacionfamiliar(true);
        contrato.setValorhora(2);
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        boleta.setPorcentajedesc_AFP(0.1);
        concepto.setHorasausente(2);
        concepto.setAdelantos(5);
        concepto.setOtrosdescuentos(10);
        double expResult = 265.8;
        double result = boleta.calcularSueldoNeto();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    //Prueba unitaria de calcular total descuentos
    @Test
    public void testCalcularTotaldescuentos() {
        System.out.println("calcularTotaldescuentos");
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-08-01");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-06-01");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Periodo periodo = new Periodo();
        Concepto concepto = new Concepto();
        boleta.setConceptoingresodescuento(concepto);
        boleta.setContrato(contrato);
        boleta.setPeriodo(periodo);
        contrato.setTotalhoras(8);
        periodo.setFechainicioperiodo(fechaInicioContratoNuevo);
        periodo.setFechafinperiodo(fechaFinContratoPrevio);
        contrato.setValorhora(2);
        boleta.setPorcentajedesc_AFP(0.1);
        concepto.setHorasausente(2);
        concepto.setAdelantos(5);
        concepto.setOtrosdescuentos(10);
        double expResult = 29.8;
        double result = boleta.calcularTotaldescuentos();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    //Prueba unitaria del metodo calcular AFP
    @Test
    public void testCalcularAFP() {
        System.out.println("calcularAFP");
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-08-01");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-06-01");
        Boleta boleta = new Boleta();
        Contrato contrato = new Contrato();
        Periodo periodo = new Periodo();
        boleta.setContrato(contrato);
        boleta.setPeriodo(periodo);
        contrato.setTotalhoras(8);
        periodo.setFechainicioperiodo(fechaInicioContratoNuevo);
        periodo.setFechafinperiodo(fechaFinContratoPrevio);
        contrato.setValorhora(2);
        boleta.setPorcentajedesc_AFP(0.1);
        double expResult = 12.8;
        double result = boleta.calcularAFP();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
