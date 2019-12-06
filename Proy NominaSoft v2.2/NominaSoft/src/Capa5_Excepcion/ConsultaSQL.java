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
public class ConsultaSQL {
    public static final String TIPO_SELECT = "select";
    public static final String TIPO_INSERT = "insert into";
    public static final String TIPO_UPDATE = "update";
    public static final String TIPO_DELETE = "delete";


    public static final String CLAVE_FROM = "from";
    public static final String CLAVE_WHERE = "where";
    public static final String CLAVE_VALUES = "values";
    public static final String CLAVE_SET = "set";    
    
    
    public static final String SIGNO_IGUAL = "=";
    public static final String SIGNO_COMILLA_SIMPLE = "'";
    public static final String SIGNO_PARENTESIS_ABRIR = "(";
    public static final String SIGNO_PARENTESIS_CERRAR = ")";
    public static final String SIGNO_PARAMETRO = "?";    
    
    public static final String ESPACIO = " ";

    private ConsultaSQL() {
    }
     
    public static String crearConsultaSelect(String tabla, String campos, String campoParametro, int parametro){
        String consulta = TIPO_SELECT + ESPACIO + campos + ESPACIO + CLAVE_FROM + ESPACIO + tabla + ESPACIO + CLAVE_WHERE +
                ESPACIO + campoParametro + ESPACIO + SIGNO_IGUAL + ESPACIO + SIGNO_COMILLA_SIMPLE + parametro + SIGNO_COMILLA_SIMPLE;
        return consulta;
    }    
    
    public static String crearConsultaInsert(String tabla, String campos, int valores){
        String nValores = cadenaValores(valores); 
        String consulta = TIPO_INSERT + ESPACIO + tabla + ESPACIO + SIGNO_PARENTESIS_ABRIR + campos + SIGNO_PARENTESIS_CERRAR +
                ESPACIO + CLAVE_VALUES + ESPACIO + SIGNO_PARENTESIS_ABRIR + nValores + SIGNO_PARENTESIS_CERRAR;
        return consulta;
    }      

    
    public static String crearConsultaUpdate(String tabla, String campos, String campoParametro){
        String consulta = TIPO_UPDATE + ESPACIO + tabla + ESPACIO + CLAVE_SET + ESPACIO + campos + ESPACIO + CLAVE_WHERE + ESPACIO +
                campoParametro; 
        return consulta;
    }    
    
    public static String crearConsultaDelete(String tabla, String campoParametro){
        String consulta = TIPO_DELETE + ESPACIO + CLAVE_FROM + ESPACIO + tabla + ESPACIO + CLAVE_WHERE + ESPACIO + campoParametro
                + ESPACIO + SIGNO_IGUAL + ESPACIO + SIGNO_PARAMETRO; 
        return consulta;                
    }
    
    private static String cadenaValores(int valores){
        String cadena = "";
        for(int i = 1; i <= valores; i++){
            cadena = cadena + "?";
            if(i < valores) cadena = cadena + ", ";
        }
        return cadena;
    }    
}
