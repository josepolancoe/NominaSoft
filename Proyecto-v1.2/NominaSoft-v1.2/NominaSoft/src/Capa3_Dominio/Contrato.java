/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;
import java.sql.Date;



public class Contrato {
    int idcontrato;
    Empleado empleado;
    Afp afp;
    Date fechainiciocontrato;
    Date fechafincontrato;
    String cargo;
    Boolean asignacionfamiliar;
    int totalhoras;
    double valorhora;
    String condicion;

    public Contrato(Empleado empleado, Afp afp, Date fechainiciocontrato, Date fechafincontrato, String cargo, Boolean asignacionfamiliar, int totalhoras, double valorhora, String condicion) {
        this.empleado = empleado;
        this.afp = afp;
        this.fechainiciocontrato = fechainiciocontrato;
        this.fechafincontrato = fechafincontrato;
        this.cargo = cargo;
        this.asignacionfamiliar = asignacionfamiliar;
        this.totalhoras = totalhoras;
        this.valorhora = valorhora;
        this.condicion = condicion;
    }
  
    
    public Contrato(int idcontrato, Empleado empleado, Afp afp, Date fechainiciocontrato, Date fechafincontrato, String cargo, Boolean asignacionfamiliar, int totalhoras, double valorhora, String condicion) {
        this.idcontrato = idcontrato;
        this.empleado = empleado;
        this.afp = afp;
        this.fechainiciocontrato = fechainiciocontrato;
        this.fechafincontrato = fechafincontrato;
        this.cargo = cargo;
        this.asignacionfamiliar = asignacionfamiliar;
        this.totalhoras = totalhoras;
        this.valorhora = valorhora;
        this.condicion = condicion;
    }

    public Contrato() {
    }

    
    public int getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Afp getAfp() {
        return afp;
    }

    public void setAfp(Afp afp) {
        this.afp = afp;
    }

    public Date getFechainiciocontrato() {
        return fechainiciocontrato;
    }

    public void setFechainiciocontrato(Date fechainiciocontrato) {
        this.fechainiciocontrato = fechainiciocontrato;
    }

    public Date getFechafincontrato() {
        return fechafincontrato;
    }

    public void setFechafincontrato(Date fechafincontrato) {
        this.fechafincontrato = fechafincontrato;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Boolean getAsignacionfamiliar() {
        return asignacionfamiliar;
    }

    public void setAsignacionfamiliar(Boolean asignacionfamiliar) {
        this.asignacionfamiliar = asignacionfamiliar;
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

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public double cacularAsignacionFamiliar(Boolean asignacionfamiliar){
        double asigfam = 0,sueldominimo=950;
        if(asignacionfamiliar==true){
        asigfam = sueldominimo*0.1;
        }else{
            asigfam= 0;
        }
        return asigfam;
    }
    public Boolean verificarContratoVigente(Date fechafincontrato,String condicion){
     java.util.Date fechaActual = new java.util.Date();
     if(fechafincontrato.getTime() >= fechaActual.getTime() && condicion == "ACTIVO"){
        return true;
     }else
          condicion = "INACTIVO";
         return false;
    
    }
    
    public Boolean verificarHorasContratadas(int totalhoras){
        if(totalhoras >= 8 && totalhoras <= 40)
            return true;
        else
            return false;
    }
    public Boolean verificarRangoSueldo(String gradoAcademico, double valorHora){
            if(gradoAcademico=="Primaria" || gradoAcademico == "Secundaria"){
                if(5 <= valorHora && valorHora <= 10){
                    return true;
                }else{
                    return false;
                }
            }else if(gradoAcademico == "Bachiller"){
                if (11 <= valorHora && valorHora <= 20){
                    return true;
                }else{
                    return false;
                }
            }else if (gradoAcademico == "Profesional"){
                if (21 <= valorHora && valorHora <= 30){
                    return true;
                }else{
                    return false;
                }
            } else if (gradoAcademico == "Magister"){
                if (31 <= valorHora && valorHora <= 40){
                    return true;
                }else{
                    return false;
                }
            }else {
                if (41 <= valorHora && valorHora <= 60){
                    return true;
                }else{
                    return false;
                }
            }
        }
    
    
    
//    public Boolean verificarFechaInicio(){
//        long fechafin = fechafincontrato.getTime();
//        long fechainicio = fechainiciocontrato.getTime();
//        if(verificarContratoVigente()==true){
//          if(fechainicio > fechafin)
//          return true;
//        }
//        return false;
//    }
    
//    public Boolean verificarFechaFin(){       
//        long fechafin = fechafincontrato.getTime();
//        long fechainicio = fechainiciocontrato.getTime();
//        long diferencia = fechafin - fechainicio;
//        if(diferencia <= 3)
//        return true;
//        else
//            return false;
//    }    
    
    public Boolean verificarFechaInicioContratoNuevo(Date fechainiciocontrato,Date fechafincontrato){
        
        if(fechainiciocontrato.getTime()> fechafincontrato.getTime()){
        return true;
        }
        return false;
    } 
    
    /* public Boolean verificarFechaFinContratoNuevo(){
 
        Date fechaInicio = fechainiciocontrato;
        Date fechaFin = fechafincontrato;
        int tipo;
	
    // Fecha inicio
	Calendar calendarInicio = Calendar.getInstance();
	calendarInicio.setTime(fechaInicio);
	int diaInicio = calendarInicio.get(Calendar.DAY_OF_MONTH);
	int mesInicio = calendarInicio.get(Calendar.MONTH) + 1; // 0 Enero, 11 Diciembre
	int anioInicio = calendarInicio.get(Calendar.YEAR);

    // Fecha fin
	Calendar calendarFin = Calendar.getInstance();
	calendarFin.setTime(fechaFin);
	int diaFin = calendarFin.get(Calendar.DAY_OF_MONTH);
	int mesFin = calendarFin.get(Calendar.MONTH) + 1; // 0 Enero, 11 Diciembre
	int anioFin = calendarFin.get(Calendar.YEAR);

	int anios = 0;
	int mesesPorAnio = 0;
	int diasPorMes = 0;
	int diasTipoMes = 0;

	// Calculo de días del mes
	if (mesInicio == 2) {
		// Febrero
		if ((anioFin % 4 == 0) && ((anioFin % 100 != 0) || (anioFin % 400 == 0))) {
			// Bisiesto
			diasTipoMes = 29;
		} else {
			// No bisiesto
			diasTipoMes = 28;
		}
	} else if (mesInicio <= 7) {
		// De Enero a Julio los meses pares tienen 30 y los impares 31
		if (mesInicio % 2 == 0) {
			diasTipoMes = 30;
		} else {
			diasTipoMes = 31;
		}
	} else if (mesInicio > 7) {
		// De Julio a Diciembre los meses pares tienen 31 y los impares 30
		if (mesInicio % 2 == 0) {
			diasTipoMes = 31;
		} else {
			diasTipoMes = 30;
		}
	}

	// Calculo de diferencia de año, mes y dia

	if ((anioInicio > anioFin) || (anioInicio == anioFin && mesInicio > mesFin)
			|| (anioInicio == anioFin && mesInicio == mesFin && diaInicio > diaFin)) {
		// La fecha de inicio ha de ser anterior a la fecha fin
		return false;
	} else {
		if (mesInicio <= mesFin) {
			anios = anioFin - anioInicio;
			if (diaInicio <= diaFin) {
				mesesPorAnio = mesFin - mesInicio;
				diasPorMes = diaFin - diaInicio;
			} else {
				if (mesFin == mesInicio) {
					anios = anios - 1;
				}
				mesesPorAnio = (mesFin - mesInicio - 1 + 12) % 12;
				diasPorMes = diasTipoMes - (diaInicio - diaFin);
			}
		} else {
			anios = anioFin - anioInicio - 1;
			System.out.println(anios);
			if (diaInicio > diaFin) {
				mesesPorAnio = mesFin - mesInicio - 1 + 12;
				diasPorMes = diasTipoMes - (diaInicio - diaFin);
			} else {
				mesesPorAnio = mesFin - mesInicio + 12;
				diasPorMes = diaFin - diaInicio;
			}
		}
        long returnValue = -1;
        returnValue = anios * 12 + mesesPorAnio;        
        
        if(returnValue > 3 && returnValue < 12){
            return true;
        }else
             return false;
	} 
    } */ 
}
