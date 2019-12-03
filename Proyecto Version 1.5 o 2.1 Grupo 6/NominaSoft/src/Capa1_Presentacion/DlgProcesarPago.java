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
import Capa6_Globales.AtajosTecladoDlg;
import Capa6_Globales.EstiloEtiqueta;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JosePolancoLAP
 */
public class DlgProcesarPago extends javax.swing.JDialog {
    DefaultTableModel modeloBoleta;
    Boleta boleta;
    Periodo periodo, periodoVigente , periodoActivo, periodoPrevio;
    Contrato contrato;
    Concepto concepto;
    Empleado empleado;
    Afp afp;
    int ContratosVigentesProcesados = 0;
    private List<Periodo> listaPeriodo;
    private List<Contrato> listaContrato;
    DefaultTableModel modeloTabla;

    /**
     * Creates new form DlgProcesarPago
     */
    public DlgProcesarPago(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(new Color(153,153,153));
        setResizable(false);
        setLocationRelativeTo(null);
        EstadoDefault();
    }
    
    void EstadoDefault(){
        Estilo();
        jpnDatosPeriodo.grabFocus();
        EventoTecla();
        VerificarPeriodoActivo();
    }
    
    void LimpiarTabla(DefaultTableModel modelo){
        int a = modelo.getRowCount()-1;
        for(int i=a; i>=0; i--){
            modelo.removeRow(i );
        }
    }
    
    public void BuscarPeriodos(){     
        try{
            ServGestionarPeriodo gestionarPeriodo = new ServGestionarPeriodo();
            listaPeriodo = gestionarPeriodo.BuscarPeriodos();     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        }
        if (listaPeriodo == null){
            Mensaje.mostrarAfirmacion(this, "Sin periodos registrados");
        }
    }
    
    public void VerificarPeriodo(int parIdperiodo){
        try{
            ServGestionarPeriodo gestionarPeriodo = new ServGestionarPeriodo();
            periodo = gestionarPeriodo.VerificarPeriodo(parIdperiodo);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        }
        if (periodo == null){
            Mensaje.mostrarAfirmacion(this, "No exite periodo");
        }
    }

    public void VerificarPeriodoActivo(){
        BuscarPeriodos();
        if (listaPeriodo!=null) {
            for (Periodo objetoPeriodo : listaPeriodo) {
                if (objetoPeriodo.verificarPeriodoVigente()) {
                    periodoVigente = objetoPeriodo;                   
                    break;
                }
            }
            if (periodoVigente != null) {
                btnProcesar.setEnabled(true);
                MostrarDatos(periodoVigente);
            }else{
                JOptionPane.showMessageDialog(this, "No se encontró periodo vigente");
                btnProcesar.setEnabled(false);
                dispose();
                VerificarPeriodoPrevio();
            }
        }
    }
    
    public void VerificarPeriodoPrevio(){
        int codigomayor = 0;
        if (listaPeriodo != null) {
            for (Periodo objetoPeriodo : listaPeriodo) {
                int codigo = objetoPeriodo.getIdperiodo();
                if(codigo > codigomayor)
                    codigomayor = codigo;
            }
            VerificarPeriodo(codigomayor);
            MostrarDatos(periodo);
        }
    }
    
    private void MostrarDatos(Periodo parPeriodo){
        lblVerEstado.setText(parPeriodo.getEstado());
        lblVerFechaInicio.setText(String.valueOf(parPeriodo.getFechainicioperiodo()));
        lblVerFechaFin.setText(String.valueOf(parPeriodo.getFechafinperiodo()));
    }
        
    public void BuscarContratosActivos(){     
        try{
            ServGestionarContrato gestionarContrato = new ServGestionarContrato();
            listaContrato = gestionarContrato.buscarContratosActivos();     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        }
        if (listaContrato == null){
            Mensaje.mostrarAfirmacion(this, "No exite contratos");
        }
    }
    
    public Empleado BuscarEmpleado(int idEmpleado){     
        try{
            ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();
            empleado = gestionarEmpleado.BuscarEmpleado(idEmpleado);
              
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        }
        if (empleado == null){
            Mensaje.mostrarAfirmacion(this, "No exite empleado");
        }
        return empleado;
    }
     public Afp BuscarAfp(int idAfp){     
        try{
            ServGestionarAfp gestionarAfp = new ServGestionarAfp();
            afp = gestionarAfp.VerificarAfp(idAfp);
              
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        }
        if (afp == null){
            Mensaje.mostrarAfirmacion(this, "No exite afp");
        }
        return afp;
    }
  
    public Concepto BuscarConcepto(int idContrato){
        try{
            ServGestionarConcepto gestionarConcepto = new ServGestionarConcepto();
            concepto = gestionarConcepto.BuscarConceptoContrato(idContrato);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        }
//        if (concepto == null){
//            Mensaje.mostrarAfirmacion(this, "No exite concepto");
//        }
        return concepto;
    }
    
      public void ContarContratos(){
       BuscarContratosActivos();
        if (listaContrato != null) {
           for (Contrato objetoContrato : listaContrato) {
               if(objetoContrato.verificarContratoVigente() == true){
                   ContratosVigentesProcesados++;
               }
           }
        }
   }
 
    public void ProcesarPeriodo(Periodo parPeriodo){
        try{
            ServGestionarPeriodo gestionarPeriodo = new ServGestionarPeriodo();
            int registros_afectados = gestionarPeriodo.ProcesarPeriodo(parPeriodo);     
            if(registros_afectados == 1){
                JOptionPane.showMessageDialog(null,"Se procesaron los pagos de todos los empleados");  
            }     
        } catch (Exception e) {
            Mensaje.mostrarError(this, "Error al Procesar Periodo");
        }
    }

    private Boleta CrearBoleta(Contrato parContrato, Periodo parPeriodo, Concepto parConcepto){        
        Boleta boletaNueva = new Boleta();
        boletaNueva.setContrato(parContrato);
        boletaNueva.setPeriodo(parPeriodo); 
        boletaNueva.setConcepto(parConcepto);              
        java.util.Date fechaActual = new java.util.Date();
        long dateActual = 0;
        dateActual = fechaActual.getTime();
        java.sql.Date fecha = new java.sql.Date(dateActual); 
        boletaNueva.setFecha(fecha);
        int totalhoras = boletaNueva.calcularTotalHoras();
        boletaNueva.setTotalhoras(totalhoras);
        boletaNueva.setValorhora(parContrato.getValorhora());
        double asignacionfamiliar = parContrato.cacularAsignacionFamiliar();
        boletaNueva.setAsignacionfamiliar(asignacionfamiliar);
        try {                                           
            ServGestionarBoleta gestionarBoleta = new ServGestionarBoleta();
            int registros_afectados = gestionarBoleta.IngresarBoleta(boletaNueva);              
        } catch (Exception e) {
            Mensaje.mostrarError(this, "Error de Ingreso Boleta");
        } 
        return boletaNueva;
    }
       
    private void GenerarTabla(){  
        BuscarContratosActivos();
        if (listaContrato!=null) {
            Object columnas[] = obtenerColumnas();
            Object datos[][]= obtenerDatosTabla(listaContrato);
            modeloTabla = new DefaultTableModel(datos, columnas)
            {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    //return canEdit [columnIndex];
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
            //tablaBoleta.setAutoscrolls(true);
            //TableColumn  columna = tablaBoleta.getColumnModel().getColumn(3);
            //tablaBoleta.removeColumn(columna);
        }
    }
    
    public Object[] obtenerColumnas() {
        return new Object[]{"Código", "Nombre Empleado", "DNI", "Horas","Valor hora", "Sueldo Basico", "Ingresos", "Descuentos", "Sueldo Neto"};
    }

    public Object[][] obtenerDatosTabla(List<Contrato> parListaContrato) {
        Object datos[][] = null;       
        VerificarPeriodoActivo();
        if(periodoVigente != null){
            BuscarContratosActivos();  
            if (parListaContrato != null) {
                int fila = 0, filas = parListaContrato.size();
                datos = new Object[filas][9];
                for (Contrato objetoContrato : parListaContrato) {
                    if(objetoContrato.verificarContratoVigente() == true){
                        int idContrato = objetoContrato.getIdcontrato();   
                        int idafp = objetoContrato.getAfp().getIdafp();
                        BuscarAfp(idafp);
                        objetoContrato.setAfp(afp);
                        BuscarConcepto(idContrato);
                        if(concepto != null){  
                            concepto.setContrato(objetoContrato);
                            concepto.setPeriodo(periodoVigente);  
                            boleta = CrearBoleta(objetoContrato, periodoVigente, concepto);
                            int idEmpleado = objetoContrato.getEmpleado().getIdempleado();
                            empleado = BuscarEmpleado(idEmpleado);
                            datos[fila][0] = idEmpleado;
                            datos[fila][1] = empleado.getNombre();
                            datos[fila][2] = empleado.getDni();
                            datos[fila][3] = objetoContrato.getTotalhoras();
                            datos[fila][4] = objetoContrato.getValorhora();
                            datos[fila][5] = boleta.calcularSueldoBasico();
                            datos[fila][6] = boleta.calcularTotalIngresos();
                            datos[fila][7] = boleta.calcularTotaldescuentos();
                            datos[fila][8] = boleta.calcularSueldoNeto();
                            fila++;
                        }
                    }
                }    
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
        btnProcesar = new javax.swing.JButton();
        jpnDatosPeriodo = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        lblVerEstado = new javax.swing.JLabel();
        lblVerFechaInicio = new javax.swing.JLabel();
        lblVerFechaFin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBoleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBoleta.setOpaque(false);
        jsBoleta.setViewportView(tblBoleta);

        getContentPane().add(jsBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 830, 230));

        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        getContentPane().add(btnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 80, -1));

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
        lblVerEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblVerEstadoKeyPressed(evt);
            }
        });
        jpnDatosPeriodo.add(lblVerEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 270, -1));

        lblVerFechaInicio.setText("...");
        lblVerFechaInicio.setToolTipText("");
        jpnDatosPeriodo.add(lblVerFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 270, -1));

        lblVerFechaFin.setText("...");
        jpnDatosPeriodo.add(lblVerFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 270, -1));

        getContentPane().add(jpnDatosPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 730, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        if(periodoVigente != null){
           if(periodoVigente.verificarProcesoPeriodoVigente()){
               ContarContratos();
               if(ContratosVigentesProcesados>0){
                GenerarTabla();
                btnProcesar.setEnabled(false);
                ProcesarPeriodo(periodoVigente);
                int idperiodovigente = periodoVigente.getIdperiodo();
                VerificarPeriodo(idperiodovigente);
                MostrarDatos(periodo);
                }else{
                    Mensaje.mostrarError(this, "No se puede procesar porque no existen contratos vigentes");
                }
           }else
                Mensaje.mostrarError(this, "“No se puede procesar porque la fecha actual no corresponde a la fecha admitida para procesar pagos");
        }
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void lblVerEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblVerEstadoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_lblVerEstadoKeyPressed
    void Estilo(){
        Object[ ] etiquetas = {lblEstado, lblFechaInicio, lblFechaFin, lblVerEstado, lblVerFechaInicio, lblVerFechaFin};
        EstiloEtiqueta estilo = new EstiloEtiqueta(etiquetas);
    }
    
    void EventoTecla(){
        jpnDatosPeriodo.addKeyListener(new AtajosTecladoDlg(this));
        tblBoleta.addKeyListener(new AtajosTecladoDlg(this));

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgProcesarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgProcesarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgProcesarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgProcesarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgProcesarPago dialog = new DlgProcesarPago(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcesar;
    private javax.swing.JPanel jpnDatosPeriodo;
    private javax.swing.JScrollPane jsBoleta;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblVerEstado;
    private javax.swing.JLabel lblVerFechaFin;
    private javax.swing.JLabel lblVerFechaInicio;
    private javax.swing.JTable tblBoleta;
    // End of variables declaration//GEN-END:variables
}
