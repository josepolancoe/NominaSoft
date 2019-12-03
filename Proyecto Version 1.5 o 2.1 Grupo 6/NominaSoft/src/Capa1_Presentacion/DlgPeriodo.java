/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;


import Capa2_Aplicacion.ServGestionarPeriodo;
import Capa3_Dominio.Periodo;
import Capa5_Excepcion.Mensaje;
import Capa6_Globales.AtajosTecladoDlg;
import Capa6_Globales.EstiloEtiqueta;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jgarc
 */
public class DlgPeriodo extends javax.swing.JDialog {

    /**
     * Creates new form DlgPeriodo
     */
    private List<Periodo> listaPeriodo;
    private Periodo periodo, periodoVigente;
    private int idPeriodo;
    private String estadoPeriodo = "";
    private java.sql.Date fechainicioperiodo;
    private java.sql.Date fechafinperiodo;
    
    public DlgPeriodo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(new Color(153,153,153));
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Periodo");
        EstadoDefault();
        Inicio();
    }
  
    
    void Inicio(){
        BuscarPeriodoVigente();
        if(periodoVigente == null){
            JOptionPane.showMessageDialog(null,"Sin periodo vigente");
        }
    }
    
    void EstadoDefault(){
        Estilo();
        LimpiarEntradas();
        txtCodigo.setEditable(false);
        txtEstado.setEditable(false);
        jdcFechaInicioPeriodo.requestFocus();
        btnRegistrar.setText("Registrar");       
        ActivarControles(false);
        ActivarEntradas(false);
        EventoTecla();
    }
 
    void LimpiarEntradas (){
        txtCodigo.setText("");
        txtEstado.setText("");
        jdcFechaInicioPeriodo.setDate(null);
        jdcFechaFinPeriodo.setDate(null);
    }
    
    void Estado(boolean estado){
        if(estado == true){
        //Estado Registrar 
            LimpiarEntradas();
            ActivarEntradas(true);
            EstadoControles(true);
            jdcFechaInicioPeriodo.requestFocus();     
            btnRegistrar.setText("Registrar");
        }else{
        //Estado Modificar
            ActivarEntradas(false);
            EstadoControles(false);
            jdcFechaInicioPeriodo.setEnabled(false);
            jdcFechaFinPeriodo.setEnabled(false);
        }  
    }

    void ActivarControles(boolean sw){
        btnNuevo.setEnabled(!sw);
        btnRegistrar.setEnabled(sw);
        btnModificar.setEnabled(sw);
        btnCancelar.setEnabled(sw);
    }
    
    void EstadoControles(boolean sw){
        btnNuevo.setEnabled(false);
        btnRegistrar.setEnabled(sw);
        btnModificar.setEnabled(!sw);
        btnCancelar.setEnabled(true);     
    } 
    
    void ActivarEntradas(boolean sw){       
        txtCodigo.setEnabled(sw);
        txtEstado.setEnabled(sw);
        jdcFechaInicioPeriodo.setEnabled(sw);       
        jdcFechaFinPeriodo.setEnabled(sw);
    }  

    
   public void VerificarPeriodo(int parIdPeriodo){             
        try{
            ServGestionarPeriodo gestionarPeriodo = new ServGestionarPeriodo();
            periodo = gestionarPeriodo.VerificarPeriodo(parIdPeriodo);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        } 
        if (periodo == null){
            Mensaje.mostrarAfirmacion(this, "No exite Periodo");
        }
    }
    
    public void ConsultarPeriodos(){     
        try{
            ServGestionarPeriodo gestionarPeriodo = new ServGestionarPeriodo();
            listaPeriodo = gestionarPeriodo.BuscarPeriodos();     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        }
        if (listaPeriodo == null){
            Mensaje.mostrarAfirmacion(this, "Sin Periodo Registrado");
        }
    } 
   
    public void BuscarPeriodoVigente(){
        ConsultarPeriodos();
        if(listaPeriodo != null){
            for(Periodo objetoPeriodo : listaPeriodo){
                if(objetoPeriodo.verificarPeriodoVigente()){
                    MostrarDatos(objetoPeriodo);
                    periodoVigente = objetoPeriodo;
                    Estado(false);
                    break;
                }
            }
            if(periodoVigente == null){
                EstadoDefault();
                btnNuevo.setEnabled(true);                
            }
        }
    }
  
    public void MostrarDatos(Periodo parPeriodo){  
        if(parPeriodo != null){
            txtCodigo.setText(String.valueOf(parPeriodo.getIdperiodo()));     
            txtEstado.setText(parPeriodo.getEstado());
            jdcFechaInicioPeriodo.setDate(parPeriodo.getFechainicioperiodo()); 
            jdcFechaFinPeriodo.setDate(parPeriodo.getFechafinperiodo());
        }  
    } 
   
   public void ObtenerDatos(){
        if(jdcFechaInicioPeriodo.getDate() != null && jdcFechaFinPeriodo.getDate() != null){        
            if(txtCodigo.getText().length() > 0){
                idPeriodo = Integer.parseInt(txtCodigo.getText().trim());
            }    
            estadoPeriodo = txtEstado.getText().toUpperCase();
            long date = 0;     
            Date Fechainicio = jdcFechaInicioPeriodo.getDate();                
            date = Fechainicio.getTime(); 
            fechainicioperiodo = new java.sql.Date(date);
            Date Fechafin = jdcFechaFinPeriodo.getDate();                
            date = Fechafin.getTime(); 
            fechafinperiodo = new java.sql.Date(date);     
        }
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnDatosPeriodo = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        lblInicioPeriodo = new javax.swing.JLabel();
        jdcFechaFinPeriodo = new com.toedter.calendar.JDateChooser();
        lblFinPeriodo = new javax.swing.JLabel();
        jdcFechaInicioPeriodo = new com.toedter.calendar.JDateChooser();
        txtEstado = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnDatosPeriodo.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jpnDatosPeriodo.setOpaque(false);
        jpnDatosPeriodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigo.setText("Codigo:");
        jpnDatosPeriodo.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jpnDatosPeriodo.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 119, -1));

        lblEstado.setText("Estado:");
        jpnDatosPeriodo.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        lblInicioPeriodo.setText("Inicio del Periodo:");
        jpnDatosPeriodo.add(lblInicioPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        jdcFechaFinPeriodo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaFinPeriodoPropertyChange(evt);
            }
        });
        jpnDatosPeriodo.add(jdcFechaFinPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 160, -1));

        lblFinPeriodo.setText("Fin del Periodo:");
        jpnDatosPeriodo.add(lblFinPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        jdcFechaInicioPeriodo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaInicioPeriodoPropertyChange(evt);
            }
        });
        jpnDatosPeriodo.add(jdcFechaInicioPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, -1));
        jpnDatosPeriodo.add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 160, -1));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jpnDatosPeriodo.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 80, -1));

        getContentPane().add(jpnDatosPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 190));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 80, -1));
        btnRegistrar.getAccessibleContext().setAccessibleName("vdv");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 80, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:   
        ObtenerDatos();
      
        if(btnRegistrar.getText().equalsIgnoreCase("Registrar")) {
            if(jdcFechaInicioPeriodo.getDate() != null && jdcFechaFinPeriodo.getDate() != null){
                try {                 
                    periodo = new Periodo(fechainicioperiodo, fechafinperiodo);
                    ServGestionarPeriodo gestionarPeriodo = new ServGestionarPeriodo();
                    int registros_afectados = gestionarPeriodo.IngresarPeriodo(periodo);
                    
                   if(registros_afectados == 1){
                        JOptionPane.showMessageDialog(null,"Periodo registrada");
                        EstadoControles(false);
                        btnRegistrar.setText("Actualizar");
                        btnCancelar.setText("Finalizar");
                        MostrarDatos(periodo);
                    }
                } catch (Exception e) {
                    Mensaje.mostrarError(this, "Error de Ingreso");
                }
            } else
                JOptionPane.showMessageDialog(null,"faltan llenar datos");
        } else {
            if(jdcFechaInicioPeriodo.getDate() != null && jdcFechaFinPeriodo.getDate() != null){
                    try{
                        periodo = new Periodo(idPeriodo, estadoPeriodo, fechainicioperiodo, fechafinperiodo);
                        ServGestionarPeriodo gestionarPeriodo = new ServGestionarPeriodo();                          
                        int registros_afectados =  gestionarPeriodo.ModificarPeriodo(periodo);

                        if(registros_afectados == 1){
                            JOptionPane.showMessageDialog(null,"Registro Actualizado");                                        
                            btnCancelar.setText("Terminar");   
                            MostrarDatos(periodo);
                        }
                    } catch (Exception e) {
                        Mensaje.mostrarError(this, "Error de Actualizacion");
                    } 

            } else
                JOptionPane.showMessageDialog(null,"Faltan llenar datos");
            }       
        
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if(btnCancelar.getText().equalsIgnoreCase("Terminar")){
            dispose();
        }else if(btnCancelar.getText().equalsIgnoreCase("Cancelar")){
            Inicio();
            }else{
                MostrarDatos(periodo);
                EstadoDefault();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jdcFechaInicioPeriodoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcFechaInicioPeriodoPropertyChange
        // TODO add your handling code here:
        jdcFechaInicioPeriodo.getJCalendar().setMinSelectableDate(new Date());
        ((JTextFieldDateEditor)jdcFechaFinPeriodo.getDateEditor()).setEditable(false);
    }//GEN-LAST:event_jdcFechaInicioPeriodoPropertyChange

    private void jdcFechaFinPeriodoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcFechaFinPeriodoPropertyChange
        // TODO add your handling code here:
         jdcFechaFinPeriodo.getJCalendar().setMinSelectableDate(new Date());
        ((JTextFieldDateEditor)jdcFechaFinPeriodo.getDateEditor()).setEditable(false);
    }//GEN-LAST:event_jdcFechaFinPeriodoPropertyChange

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        //&& !listaPeriodo.isEmpty()  
        Estado(true);

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        ActivarEntradas(true);
        jdcFechaInicioPeriodo.setEnabled(true);
        ActivarControles(true);
        btnRegistrar.setText("Actualizar");
        EstadoControles(true); 
    }//GEN-LAST:event_btnModificarActionPerformed

    void Estilo(){
        Object[ ] etiquetas = {lblCodigo, lblEstado, lblInicioPeriodo, lblFinPeriodo};
        EstiloEtiqueta estilo = new EstiloEtiqueta(etiquetas);
        jpnDatosPeriodo.setFont(new Font("Arial", 1, 12));
    }
    
    void EventoTecla(){
        jpnDatosPeriodo.addKeyListener(new AtajosTecladoDlg(this));
        txtCodigo.addKeyListener(new AtajosTecladoDlg(this));
        jdcFechaInicioPeriodo.addKeyListener(new AtajosTecladoDlg(this));
        jdcFechaFinPeriodo.addKeyListener(new AtajosTecladoDlg(this));
        txtEstado.addKeyListener(new AtajosTecladoDlg(this));
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
            java.util.logging.Logger.getLogger(DlgPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgPeriodo dialog = new DlgPeriodo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private com.toedter.calendar.JDateChooser jdcFechaFinPeriodo;
    private com.toedter.calendar.JDateChooser jdcFechaInicioPeriodo;
    private javax.swing.JPanel jpnDatosPeriodo;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFinPeriodo;
    private javax.swing.JLabel lblInicioPeriodo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEstado;
    // End of variables declaration//GEN-END:variables
}
