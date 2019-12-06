/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarAfp;
import Capa2_Aplicacion.ServGestionarBoleta;
import Capa2_Aplicacion.ServGestionarConcepto;
import Capa2_Aplicacion.ServGestionarContrato;
import Capa2_Aplicacion.ServGestionarEmpleado;
import Capa2_Aplicacion.ServGestionarPeriodo;
import Capa3_Dominio.Afp;
import Capa3_Dominio.Boleta;
import Capa3_Dominio.Concepto;
import Capa3_Dominio.Contrato;
import Capa3_Dominio.Empleado;
import Capa3_Dominio.Periodo;
import Capa5_Excepcion.Mensaje;
import Capa1_Presentacion.Utilidades.AtajosTecladoDlg;
import Capa1_Presentacion.Utilidades.EstiloBoton;
import Capa1_Presentacion.Utilidades.EstiloEtiqueta;
import Capa1_Presentacion.Utilidades.ImagenNominaSoft;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JosePolancoLAP
 */
public class DlgProcesarPago extends JDialog{

    private transient Boleta boleta;
    private transient Periodo periodo, periodoVigente;
    private transient Concepto concepto;
    private transient Empleado empleado;
    private transient Afp afp;
    private transient List<Periodo> listaPeriodo;
    private transient List<Contrato> listaContrato;
    private transient List<Concepto> listaConcepto;    
    private transient ServGestionarPeriodo gestionarPeriodo;
    private transient ServGestionarContrato gestionarContrato;
    private transient ServGestionarEmpleado gestionarEmpleado;
    private transient ServGestionarAfp gestionarAfp;
    private transient ServGestionarConcepto gestionarConcepto;
    private transient ServGestionarBoleta gestionarBoleta;
    private EstiloEtiqueta estilo;
    private EstiloBoton estiloBoton;
    private ImagenNominaSoft imagen; 
    
    /**
     * Creates new form DlgProcesarPago
     */
    public DlgProcesarPago(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(new Color(153,153,153));
        setSize(880,498);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Procesar Pago");        
        estadoDefault();       
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Capa1_Presentacion/Imagen/iconoNomina.png"));
        return retValue;
    }

    public void verificarPeriodo(int parIdperiodo){
        try{
            gestionarPeriodo = new ServGestionarPeriodo();
            periodo = gestionarPeriodo.verificarPeriodo(parIdperiodo);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "VerificarPeriodo()");            
        }
    }

    public void verificarPeriodoVigente(){
        try{
            gestionarPeriodo = new ServGestionarPeriodo();
            periodoVigente = gestionarPeriodo.verificarPeriodoVigente();     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "VerificarPeriodoVigente()");            
        }        
            if (periodoVigente != null) {
                btnProcesar.setEnabled(true);
                mostrarDatos(periodoVigente);
            }else{
                btnProcesar.setEnabled(false);
                dispose();
            }
    }
   
    public void buscarPeriodos(){     
        try{
            gestionarPeriodo = new ServGestionarPeriodo();
            listaPeriodo = gestionarPeriodo.buscarPeriodos();     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "BuscarPeriodos()");            
        }
    }

    public void buscarContratosVigentes(){     
        try{
            gestionarContrato = new ServGestionarContrato();
            listaContrato = gestionarContrato.buscarContratosVigentes();     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "BuscarContratosVigentes()");            
        }
    }  
    
    public Empleado buscarEmpleado(int idEmpleado){     
        try{
            gestionarEmpleado = new ServGestionarEmpleado();
            empleado = gestionarEmpleado.buscarEmpleado(idEmpleado);             
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "BuscarEmpleado()");            
        }
        return empleado;
    }
     
    public Afp buscarAfp(int idAfp){     
        try{
            gestionarAfp = new ServGestionarAfp();
            afp = gestionarAfp.verificarAfp(idAfp);              
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "BuscarAfp()");            
        }
        return afp;
    }
   
    public Concepto buscarConcepto(int idContrato){
        try{
            gestionarConcepto = new ServGestionarConcepto();
            concepto = gestionarConcepto.verificarConceptoContrato(idContrato);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "BuscarConcepto()");            
        }
        return concepto;
    }

    public void buscarConceptos(int idContrato){
        try{
            gestionarConcepto = new ServGestionarConcepto();
            listaConcepto = gestionarConcepto.buscarConceptos(idContrato);
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "BuscarConceptos()");            
        }
    } 
    
    public Concepto verificarConceptoVigente(Contrato parContrato){
        Concepto conceptoVigente = null;
        buscarConceptos(parContrato.getIdcontrato());
        if(!listaConcepto.isEmpty()){
            for(Concepto objetoConcepto : listaConcepto) { 
                if(objetoConcepto.getPeriodo().getIdperiodo() == periodoVigente.getIdperiodo()){
                    conceptoVigente = objetoConcepto;
                }
            }
        }else crearConceptoVacio(parContrato, periodoVigente);
        return conceptoVigente;
    }
    
    public void crearConceptoVacio(Contrato parContrato, Periodo parPeriodo){
        Concepto conceptoVacio = new Concepto(parContrato, parPeriodo, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        try {
            gestionarConcepto = new ServGestionarConcepto();
            gestionarConcepto.ingresarConcepto(conceptoVacio);
        } catch (Exception e) {
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "crearConceptoVacio()");    
        } 
    }
    
    private void mostrarDatos(Periodo parPeriodo){
        lblVerEstado.setText(parPeriodo.getEstado());
        lblVerFechaInicio.setText(String.valueOf(parPeriodo.getFechainicioperiodo()));
        lblVerFechaFin.setText(String.valueOf(parPeriodo.getFechafinperiodo()));
    }    
    
    private Boleta crearBoleta(Contrato parContrato, Periodo parPeriodo, Concepto parConcepto){        
        Boleta boletaNueva = new Boleta();
        boletaNueva.setContrato(parContrato);
        boletaNueva.setPeriodo(parPeriodo); 
        boletaNueva.setConcepto(parConcepto);              
        java.util.Date fechaActual = new java.util.Date();
        long dateActual = 0;
        dateActual = fechaActual.getTime();
        java.sql.Date fecha = new java.sql.Date(dateActual); 
        boletaNueva.setFecha(fecha);
        boletaNueva.setTotalhoras(boletaNueva.calcularTotalHoras());
        boletaNueva.setValorhora(parContrato.getValorhora());
        double asignacionfamiliar = parContrato.cacularAsignacionFamiliar();
        boletaNueva.setAsignacionfamiliar(asignacionfamiliar);
        try {                                           
            gestionarBoleta = new ServGestionarBoleta();
            gestionarBoleta.ingresarBoleta(boletaNueva);              
        } catch (Exception e) {
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO);
        } 
        return boletaNueva;
    }

    public void validarConceptosDeContratosVigentes(){
        Concepto conceptoVigenteContrato = null;
        buscarContratosVigentes();
        if (!listaContrato.isEmpty()) {               
            for (Contrato objetoContrato : listaContrato) { 
                conceptoVigenteContrato = verificarConceptoVigente(objetoContrato);             
                if(conceptoVigenteContrato == null){
                    crearConceptoVacio(objetoContrato, periodoVigente);
                } 
            }
        }
    }
    
    public List<Boleta> crearListaBoletasProcesar(){
        ArrayList<Boleta> listaBoletasParaProcesar = new ArrayList();
            buscarContratosVigentes();
            validarConceptosDeContratosVigentes();
            if (!listaContrato.isEmpty()) {               
                for (Contrato objetoContrato : listaContrato) { 
                    objetoContrato.setAfp(buscarAfp(objetoContrato.getAfp().getIdafp()));                     
                    objetoContrato.setEmpleado(buscarEmpleado(objetoContrato.getEmpleado().getIdempleado()));
                    concepto = verificarConceptoVigente(objetoContrato);
                    if(concepto != null){  
                        concepto.setContrato(objetoContrato);
                        concepto.setPeriodo(periodoVigente);      
                    }                      
                    boleta = crearBoleta(objetoContrato, periodoVigente, concepto);
                    listaBoletasParaProcesar.add(boleta);
                }
            }
        return listaBoletasParaProcesar;
    }    
        
    private void generarTabla(){  
        Object columnas[] = obtenerColumnas();
        Object datos[][]= obtenerDatosTabla(crearListaBoletasProcesar());
        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas)
        {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        tblBoleta.setModel(modeloTabla);
        tblBoleta.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblBoleta.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblBoleta.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblBoleta.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblBoleta.getColumnModel().getColumn(4).setPreferredWidth(60);
        tblBoleta.getColumnModel().getColumn(5).setPreferredWidth(90);
        tblBoleta.getColumnModel().getColumn(6).setPreferredWidth(60);
        tblBoleta.getColumnModel().getColumn(7).setPreferredWidth(60);
        tblBoleta.getColumnModel().getColumn(8).setPreferredWidth(80);
        tblBoleta.setAutoscrolls(true);     
    }
    
    public Object[] obtenerColumnas() {
        return new Object[]{"Código", "Nombre Empleado", "DNI", "Horas","Valor hora", "Sueldo Basico", "Ingresos", "Descuentos", "Sueldo Neto"};
    }

    public Object[][] obtenerDatosTabla(List<Boleta> parListaBoleta) {                
        Object datos[][] = null;    
            if (parListaBoleta != null) {
                int fila = 0, filas = parListaBoleta.size();
                datos = new Object[filas][9];
                for (Boleta objetoBoleta : parListaBoleta) {
                        datos[fila][0] = objetoBoleta.getContrato().getEmpleado().getIdempleado();
                        datos[fila][1] = objetoBoleta.getContrato().getEmpleado().getNombre();
                        datos[fila][2] = objetoBoleta.getContrato().getEmpleado().getDni();
                        datos[fila][3] = objetoBoleta.getContrato().getTotalhoras();
                        datos[fila][4] = objetoBoleta.getContrato().getValorhora();
                        datos[fila][5] = objetoBoleta.calcularSueldoBasico();
                        datos[fila][6] = objetoBoleta.calcularTotalIngresos();
                        datos[fila][7] = objetoBoleta.calcularTotaldescuentos();
                        datos[fila][8] = objetoBoleta.calcularSueldoNeto();
                        fila++;
                    }      
                }  
            return datos;
            }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsBoleta = new javax.swing.JScrollPane();
        tblBoleta = new javax.swing.JTable();
        jpnDatosPeriodo = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        lblVerEstado = new javax.swing.JLabel();
        lblVerFechaInicio = new javax.swing.JLabel();
        lblVerFechaFin = new javax.swing.JLabel();
        btnProcesar = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBoleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBoleta.setOpaque(false);
        jsBoleta.setViewportView(tblBoleta);

        getContentPane().add(jsBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 830, 230));

        jpnDatosPeriodo.setBorder(javax.swing.BorderFactory.createTitledBorder("Periodo"));
        jpnDatosPeriodo.setOpaque(false);
        jpnDatosPeriodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEstado.setText("Estado:");
        jpnDatosPeriodo.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblFechaInicio.setText("Fecha Inicio:");
        jpnDatosPeriodo.add(lblFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        lblFechaFin.setText("Fecha Fin:");
        jpnDatosPeriodo.add(lblFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        lblVerEstado.setText("...");
        jpnDatosPeriodo.add(lblVerEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 270, -1));

        lblVerFechaInicio.setText("...");
        lblVerFechaInicio.setToolTipText("");
        jpnDatosPeriodo.add(lblVerFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 270, -1));

        lblVerFechaFin.setText("...");
        jpnDatosPeriodo.add(lblVerFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 270, -1));

        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        jpnDatosPeriodo.add(btnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 80, -1));

        getContentPane().add(jpnDatosPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 830, 130));
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        buscarContratosVigentes();
        if(!listaContrato.isEmpty()){
            try{
                gestionarPeriodo = new ServGestionarPeriodo();
                int registros_afectados = gestionarPeriodo.procesarPeriodo(periodoVigente);     
                if(registros_afectados == 1){
                    generarTabla();
                    btnProcesar.setEnabled(false);
                    verificarPeriodo(periodoVigente.getIdperiodo());
                    mostrarDatos(periodo);
                    Mensaje.mostrarAfirmacion(this, Mensaje.DATOS_PROCESADOS); 
                }     
            } catch (Exception e) {
                Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "ProcesarPeriodo()");
            }
        }

    }//GEN-LAST:event_btnProcesarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcesar;
    private javax.swing.JPanel jpnDatosPeriodo;
    private javax.swing.JScrollPane jsBoleta;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblVerEstado;
    private javax.swing.JLabel lblVerFechaFin;
    private javax.swing.JLabel lblVerFechaInicio;
    private javax.swing.JTable tblBoleta;
    // End of variables declaration//GEN-END:variables

    void estadoDefault(){
        estilo();
        jpnDatosPeriodo.grabFocus();
        eventoTecla();
        verificarPeriodoVigente();
    }
    
    void limpiarTabla(DefaultTableModel modelo){
        int a = modelo.getRowCount()-1;
        for(int i = a; i >= 0; i--){
            modelo.removeRow(i);
        }
    }
    
    void estilo(){
        Object[ ] etiquetas = {lblEstado, lblFechaInicio, lblFechaFin, lblVerEstado, lblVerFechaInicio, lblVerFechaFin};
        estilo = new EstiloEtiqueta(etiquetas);
        estiloBoton = new EstiloBoton(new Object[]{btnProcesar});
        imagen = new ImagenNominaSoft(lblImagen, getPreferredSize().height, getPreferredSize().width); 
    }
    
    void eventoTecla(){
        jpnDatosPeriodo.addKeyListener(new AtajosTecladoDlg(this));
        tblBoleta.addKeyListener(new AtajosTecladoDlg(this));
        btnProcesar.addKeyListener(new AtajosTecladoDlg(this));

    }
}
