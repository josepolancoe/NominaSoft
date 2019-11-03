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
 * @author lucab
 */
public class ContratoTest {
    
    public ContratoTest() {
    }

 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
   //Prueba unitaria del metodo verifiContratoVigente cuando la fecha está correcta y la condicion es Anulado
    @Test
    public void test1VerificarContratoVigente() {
        System.out.println("verificarContratoVigente");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2019-12-25");
        Contrato instance = new Contrato();
        instance.setCondicion("ANULADO");  
        instance.setFechafincontrato(sqlTS);
        Boolean expResult = false;
        Boolean result = instance.verificarContratoVigente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo verifiContratoVigente cuando la fecha es inccorrecta y la condicion es Anulado
    @Test
    public void test2VerificarContratoVigente() {
        System.out.println("verificarContratoVigente");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2019-12-22");
        Contrato instance = new Contrato();
        instance.setCondicion("ANULADO");  
        instance.setFechafincontrato(sqlTS);
        Boolean expResult = false;
        Boolean result = instance.verificarContratoVigente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
     //Prueba unitaria del metodo verifiContratoVigente cuando la fecha es correcta y la condicion es ACTIVO
       @Test
    public void test3VerificarContratoVigente() {
        System.out.println("verificarContratoVigente");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2019-12-25");
        Contrato instance = new Contrato();
        instance.setCondicion("ACTIVO");  
        instance.setFechafincontrato(sqlTS);
        Boolean expResult = true;
        Boolean result = instance.verificarContratoVigente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
     //Prueba unitaria del metodo verificaEstadoContrato cuando la fecha es correcta
    @Test
    public void test1VerificaEstadoContrato() {
        System.out.println("verificaEstadoContrato");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2019-12-19");
        Contrato instance = new Contrato();
        instance.setFechafincontrato(sqlTS);
        Boolean expResult = true;
        Boolean result = instance.verificaEstadoContrato();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
        //Prueba unitaria del metodo verificaEstadoContrato cuando la fecha es incorrecta
        @Test
    public void test2VerificaEstadoContrato() {
        System.out.println("verificaEstadoContrato");
        java.sql.Date sqlTS = java.sql.Date.valueOf("2019-10-10");
        Contrato instance = new Contrato();
        instance.setFechafincontrato(sqlTS);
        Boolean expResult = false;
        Boolean result = instance.verificaEstadoContrato();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    //Prueba unitaria del metodo verificaHorasContratadas cuando el valor de la hora esta fuera del rango permitido
    
    @Test
    public void test1VerificarHorasContratadas() {
        System.out.println("verificarHorasContratadas");
        Contrato instance = new Contrato();
        instance.setTotalhoras(5);
        Boolean expResult = false;
        Boolean result = instance.verificarHorasContratadas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo verificaHorasContratadas cuando el valor de la hora esta dentro del rango permitido
    @Test
    public void test2VerificarHorasContratadas() {
        System.out.println("verificarHorasContratadas");
        Contrato instance = new Contrato();
        instance.setTotalhoras(15);
        Boolean expResult = true;
        Boolean result = instance.verificarHorasContratadas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Primaria y el valor de horas esta dentro de lo permitido
    @Test
    public void test1VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Primaria");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(8);
        Boolean expResult = true;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Secundaria y el valor de horas esta dentro de lo permitido
    @Test
    public void test2VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Secundaria");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(8);
        Boolean expResult = true;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Bachiller y el valor de horas esta dentro de lo permitido
    @Test
    public void test3VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Bachiller");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(19);
        Boolean expResult = true;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Profesional y el valor de horas esta dentro de lo permitido
    @Test
    public void test4VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Profesional");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(21);
        Boolean expResult = true;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Magister y el valor de horas esta dentro de lo permitido
    @Test
    public void test5VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Magister");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(35);
        Boolean expResult = true;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Doctor y el valor de horas esta dentro de lo permitido
    @Test
    public void test6VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Doctor");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(42);
        Boolean expResult = true;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Primaria y el valor de horas no esta dentro de lo permitido
    @Test
    public void test7VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Primaria");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(11);
        Boolean expResult = false;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Secundaria y el valor de horas no esta dentro de lo permitido
    @Test
    public void test8VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Secundaria");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(4);
        Boolean expResult = false;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Bachiller y el valor de horas no esta dentro de lo permitido
    @Test
    public void test9VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Bachiller");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(8);
        Boolean expResult = false;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Profesional y el valor de horas no esta dentro de lo permitido
    @Test
    public void test10VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Profesional");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(45);
        Boolean expResult = false;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Magister y el valor de horas no esta dentro de lo permitido
    @Test
    public void test11VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Magister");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(10);
        Boolean expResult = false;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo verificarValorHora cuando el grado academico es Doctor y el valor de horas no esta dentro de lo permitido
    @Test
    public void test12VerificarRangoSueldo() {
        System.out.println("verificarValorHora");
        Contrato contrato = new Contrato();
        Empleado empleado = new Empleado();
        empleado.setGradoacademico("Doctor");
        contrato.setEmpleado(empleado);
        contrato.setValorhora(25);
        Boolean expResult = false;
        Boolean result = contrato.verificarValorHora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    //Prueba unitaria del metodo verificarFechaInicioContratoNuevo cuando la fecha inicio del contrato es mayor a la fecha final 
    // de un contrato anterior
    @Test
    public void test1VerificarFechaInicioContratoNuevo() {
        System.out.println("verificarFechaInicioContratoNuevo");
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-06-03");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-10-03");
        Contrato contrato = new Contrato();
        contrato.setFechainiciocontrato(fechaInicioContratoNuevo);
        Boolean expResult = true;
        Boolean result = contrato.verificarFechaInicioContratoNuevo(fechaFinContratoPrevio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo verificarFechaInicioContratoNuevo cuando la fecha inicio del contrato es menor o igual a la fecha final 
    // de un contrato anterior
    @Test
    public void test2VerificarFechaInicioContratoNuevo() {
        System.out.println("verificarFechaInicioContratoNuevo");
        java.sql.Date fechaFinContratoPrevio = java.sql.Date.valueOf("2019-12-03");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2019-12-02");
        Contrato contrato = new Contrato();
        contrato.setFechainiciocontrato(fechaInicioContratoNuevo);
        Boolean expResult = false;
        Boolean result = contrato.verificarFechaInicioContratoNuevo(fechaFinContratoPrevio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
    
    //Prueba unitaria verificarFechaFinContratoNuevo cuando entre las fechas del nuevo contrato hay una diferencia entre 3 a 12 meses 
    @Test
    public void test1VerificarFechaFinContratoNuevo() {
        System.out.println("verificarFechaFinContratoNuevo");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2020-02-01");
        java.sql.Date fechaFinContratoNuevo = java.sql.Date.valueOf("2021-01-01");
        Contrato contrato = new Contrato();
        contrato.setFechainiciocontrato(fechaInicioContratoNuevo);
        contrato.setFechafincontrato(fechaFinContratoNuevo);
        Boolean expResult = true;
        Boolean result = contrato.verificarFechaFinContratoNuevo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
       //Prueba unitaria verificarFechaFinContratoNuevo cuando entre las fechas del nuevo contrato no hay una diferencia entre 3 a 12 meses
       @Test
    public void test2VerificarFechaFinContratoNuevo() {
        System.out.println("verificarFechaFinContratoNuevo");
        java.sql.Date fechaInicioContratoNuevo = java.sql.Date.valueOf("2020-01-01");
        java.sql.Date fechaFinContratoNuevo = java.sql.Date.valueOf("2020-02-01");
        Contrato contrato = new Contrato();
        contrato.setFechainiciocontrato(fechaInicioContratoNuevo);
        contrato.setFechafincontrato(fechaFinContratoNuevo);
        Boolean expResult = false;
        Boolean result = contrato.verificarFechaFinContratoNuevo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    //Prueba unitaria del metodo calcularAsignacionFamiliar cuando la variable asignacionfamiliar tiene como dato TRUE
   @Test
    public void testCacularAsignacionFamiliar() {
        System.out.println("cacularAsignacionFamiliar");
        Contrato contrato = new Contrato();
        contrato.setAsignacionfamiliar(true);
        double expResult = 93;
        double result = contrato.cacularAsignacionFamiliar();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //Prueba unitaria del metodo calcularAsignacionFamiliar cuando la variable asignacionfamiliar tiene como dato FALSE
     @Test
    public void test2CacularAsignacionFamiliar() {
        System.out.println("cacularAsignacionFamiliar");
        Contrato instance = new Contrato();
        instance.setAsignacionfamiliar(false);
        double expResult = 0.0;
        double result = instance.cacularAsignacionFamiliar();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

   
}
