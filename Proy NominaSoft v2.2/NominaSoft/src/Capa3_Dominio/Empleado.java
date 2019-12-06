/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;
import java.sql.Date;

public class Empleado {
    
    private int idempleado;
    private String dni;
    private String nombre;
    private Date fechanacimiento;
    private String gradoacademico;
    private String estadocivil;
    private String telefono;
    private String direccion;   

    public Empleado(String dni, String nombre, Date fechanacimiento, String gradoacademico, String estadocivil, String telefono, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechanacimiento = new Date(fechanacimiento.getTime());
        this.gradoacademico = gradoacademico;
        this.estadocivil = estadocivil;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    public Empleado(int idempleado, String dni, String nombre, Date fechanacimiento, String gradoacademico, String estadocivil, String telefono, String direccion) {
        this.idempleado = idempleado;
        this.dni = dni;
        this.nombre = nombre;
        this.fechanacimiento = new Date(fechanacimiento.getTime());
        this.gradoacademico = gradoacademico;
        this.estadocivil = estadocivil;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    public Empleado() {
    }
    
    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechanacimiento() {
        if(fechanacimiento==null){
           return null;
        }else{
            return new Date(fechanacimiento.getTime());
        }    
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = new Date(fechanacimiento.getTime());
    }

    public String getGradoacademico() {
        return gradoacademico;
    }

    public void setGradoacademico(String gradoacademico) {
        this.gradoacademico = gradoacademico;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String devolverRangoSueldo(){       
        String respuesta = "";
	switch (gradoacademico) {
		case "Primaria":                       
                            respuesta = "de 5 a 10"; 
                            break;             
		case "Secundaria":
                            respuesta = "de 5 a 10";  
                            break;
		case "Bachiller":
                            respuesta = "de 11 a 20";
                            break;
		case "Profesional":
                            respuesta = "de 21 a 30";
                            break;
		case "Magister":
                            respuesta = "de 31 a 40";
                            break;
                case "Doctor":
                            respuesta = "de 41 a 60";
                            break;
                default:
                        respuesta = null;
			break;
	}
        return respuesta;
    }
    
    
    
    
    
}
