/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucab
 */
public class PeriodoTest {
    
    public PeriodoTest() {
    }

    //Prueba unitaria del metodo verificarPeriodoVigente cuando la fecha fin del periodo es mayor a la fecha actual
    // y el estado del periodo es PENDIENTE
    @Test
    public void test1VerificarPeriodoVigente() {
        System.out.println("verificarPeriodoVigente");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2020-06-30");
        Periodo periodo = new Periodo();
        periodo.setFechafinperiodo(sqlTS);
        periodo.setEstado("ACTIVO");
        Boolean expResult = true;
        Boolean result = periodo.verificarPeriodoVigente();
        assertEquals(expResult, result);
    }
    
         //Prueba unitaria del metodo verificarPeriodoVigente cuando la fecha fin del periodo es menor a la fecha actual
    // y el estado del periodo es PROCESADO
        @Test
    public void test2VerificarPeriodoVigente() {
        System.out.println("verificarPeriodoVigente");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2019-10-20");
        Periodo periodo = new Periodo();
        periodo.setFechafinperiodo(sqlTS);
        periodo.setEstado("PROCESADO");
        Boolean expResult = false;
        Boolean result = periodo.verificarPeriodoVigente();
        assertEquals(expResult, result);
    }
    
     //Prueba unitaria del metodo verificarPeriodoVigente cuando la fecha fin del periodo es menor a la fecha actual
    // y el estado del periodo es PENDIENTE
     @Test
    public void test3VerificarPeriodoVigente() {
        System.out.println("verificarPeriodoVigente");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2019-10-20");
        Periodo periodo = new Periodo();
        periodo.setFechafinperiodo(sqlTS);
        periodo.setEstado("ACTIVO");
        Boolean expResult = false;
        Boolean result = periodo.verificarPeriodoVigente();
        assertEquals(expResult, result);
    }
    
     //Prueba unitaria del metodo verificarPeriodoVigente cuando la fecha fin del periodo es mayor a la fecha actual
    // y el estado del periodo es PROCESADO
       @Test
    public void test4VerificarPeriodoVigente() {
        System.out.println("verificarPeriodoVigente");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2019-12-20");
        Periodo periodo = new Periodo();
        periodo.setFechafinperiodo(sqlTS);
        periodo.setEstado("PROCESADO");
        Boolean expResult = false;
        Boolean result = periodo.verificarPeriodoVigente();
        assertEquals(expResult, result);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Prueba unitaria del metodo calcularTotalSemanaPeriodo cuando entre las fechas si existe una diferencia de semanas
    @Test
    public void test1CalcularTotalSemanasPeriodo() {
        System.out.println("totalSemanasPeriodo");
        java.sql.Date fechaInicio = java.sql.Date.valueOf("2019-11-01");
        java.sql.Date fechaFin = java.sql.Date.valueOf("2020-01-01");
        Periodo periodo = new Periodo();
        periodo.setFechainicioperiodo(fechaInicio);
        periodo.setFechafinperiodo(fechaFin);
        int expResult = 8;
        int result = periodo.calculartotalSemanasPeriodo();
        assertEquals(expResult, result);
    }
        
    //Prueba unitaria del metodo calcularTotalSemanaPeriodo cuando entre las fechas no existe una diferencia de semanas
        @Test
    public void test2CalcularTotalSemanasPeriodo() {
        System.out.println("totalSemanasPeriodo");
        java.sql.Date fechaInicio = java.sql.Date.valueOf("2019-11-24");
        java.sql.Date fechaFin = java.sql.Date.valueOf("2019-10-11");
        Periodo periodo = new Periodo();
        periodo.setFechainicioperiodo(fechaInicio);
        periodo.setFechafinperiodo(fechaFin);
        int expResult = 0;
        int result = periodo.calculartotalSemanasPeriodo();
        assertEquals(expResult, result);
    }
}
