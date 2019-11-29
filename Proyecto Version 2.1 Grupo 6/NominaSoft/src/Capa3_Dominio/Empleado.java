/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa3_Dominio;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Date;


public class Empleado {
    
    int idempleado;
    String dni;
    String nombre;
    Date fechanacimiento;
    String gradoacademico;
    String estadocivil;
    String telefono;
    String direccion;
    
    
    public static final String PRIMARIA = "PRIMARIA";
    public static final String SECUNDARIA = "SECUNDARIA";
    public static final String BACHILLER = "BACHILLER";
    public static final String PROFESIONAL = "PROFESIONAL";
    public static final String MAGISTER = "MAGISTER";
    public static final String DOCTOR = "DOCTOR";

    public Empleado(String dni, String nombre, Date fechanacimiento, String gradoacademico, String estadocivil, String telefono, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechanacimiento = fechanacimiento;
        this.gradoacademico = gradoacademico;
        this.estadocivil = estadocivil;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Empleado(int idempleado, String dni, String nombre, Date fechanacimiento, String gradoacademico, String estadocivil, String telefono, String direccion) {
        this.idempleado = idempleado;
        this.dni = dni;
        this.nombre = nombre;
        this.fechanacimiento = fechanacimiento;
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
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
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
    
       public List<Integer> verificarSueldoEmpleado(){
        String Grado = gradoacademico;;
        List<Integer> listaSueldo = new ArrayList<>();
        
        switch (Grado) {
		case "Primaria":
                        listaSueldo = Arrays.asList(5,6,7,8,9,10);
			break;
		case "Secundaria":
                        listaSueldo = Arrays.asList(5,6,7,8,9,10);
			break;
		case "Bachiller":
                        listaSueldo = Arrays.asList(11,12,13,14,15,16,17,18,19,20);
			break;
		case "Profesional":
                        listaSueldo = Arrays.asList(21,22,23,24,25,26,27,28,29,30);
			break;
		case "Magister":
                        listaSueldo = Arrays.asList(31,32,33,34,35,36,37,38,39,40);
			break;
                case "Doctor":
                        listaSueldo = Arrays.asList(41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60);
			break;        
	}
        return listaSueldo;
    }  
  
}
