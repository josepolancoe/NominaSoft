/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa5_Excepcion;

/**
 *
 * @author lucab
 */
public class ExcepcionRegla extends Exception{

    private static final String MENSAJE_INGRESOINCORRECTO_FECHAINICIO = "Fecha de inicio no valida.";
    private static final String MENSAJE_INGRESOINCORRECTO_FECHAFIN = "Fecha fin no valida, el periodo es de 3 a 12 meses.";
    private static final String MENSAJE_INGRESOINCORRECTO_HORASXSEMANA = "Horas/Semana no validas, el promedio es de 8 a 40 horas.";
    private static final String MENSAJE_INGRESOINCORRECTO_VALORHORA = "Valor/Hora no valido, ingrese valores entre: ";
    private static final String MENSAJE_SINPERIODO_ACTIVO = "No se puede procesar porque no existe periodo de pago activo.";
    private static final String MENSAJE_SINCONTRATOS_VIGENTES = "No se puede procesar porque no existen contratos vigentes";
    private static final String MENSAJE_FECHAACTUAL_NO_VALIDA =  "No se puede procesar porque la fecha actual no corresponde a la fecha admitida para procesar pagos.";
    
    private ExcepcionRegla(String message) {
        super(message);
    }

    public static ExcepcionRegla crearErrorFechaInicio(){
        return new ExcepcionRegla(MENSAJE_INGRESOINCORRECTO_FECHAINICIO);
    }

    public static ExcepcionRegla crearErrorFechaFin(){
        return new ExcepcionRegla(MENSAJE_INGRESOINCORRECTO_FECHAFIN);
    }

    public static ExcepcionRegla crearErrorHorasSemana(){
        return new ExcepcionRegla(MENSAJE_INGRESOINCORRECTO_HORASXSEMANA);
    }

    public static ExcepcionRegla crearErrorValorHora(String parValorHora){
        return new ExcepcionRegla(MENSAJE_INGRESOINCORRECTO_VALORHORA + parValorHora);
    }    
    
    public static ExcepcionRegla crearErrorSinPeriodoActivo(){
        return new ExcepcionRegla(MENSAJE_SINPERIODO_ACTIVO);
    }     
    
    public static ExcepcionRegla crearErrorSinContratosVigentes(){
        return new ExcepcionRegla(MENSAJE_SINCONTRATOS_VIGENTES);
    } 
    public static ExcepcionRegla crearErrorFechaParaProcesarNoValida(){
        return new ExcepcionRegla(MENSAJE_FECHAACTUAL_NO_VALIDA);
    }  
}

