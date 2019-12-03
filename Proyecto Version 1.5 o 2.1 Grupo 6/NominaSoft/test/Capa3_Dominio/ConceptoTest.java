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
public class ConceptoTest {
    
  //   Prueba unitaria del metodo calcular concepto descuento
    @Test
    public void testCalcularConcepto_Descuento() {
        System.out.println("calcularConcepto_Descuento");
        Concepto concepto = new Concepto();
        concepto.setHorasausente(2);
        concepto.setAdelantos(5);
        concepto.setOtrosdescuentos(10);
        double expResult = 17;
        double result = concepto.calcularConceptoDescuento();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

   //Prueba unitaria del metodo calcular concepto ingresar
    @Test
    public void testCalcularConcepto_Ingresar() {
        System.out.println("calcularConcepto_Ingresar");
        Concepto concepto = new Concepto();
        concepto.setHorasextra(4);
        concepto.setReintegros(6);
        concepto.setOtrosingresos(5);
        double expResult = 15;
        double result = concepto.calcularConceptoIngreso();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
