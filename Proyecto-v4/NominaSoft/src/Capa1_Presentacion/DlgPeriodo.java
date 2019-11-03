/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;


import Capa2_Aplicacion.ServGestionarPeriodo;
import Capa3_Dominio.Periodo;
import Capa5_Excepcion.Mensaje;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
//import java.sql.Date;
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
    
    public DlgPeriodo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this .getContentPane().setBackground(new Color(255,255,255));
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Periodo");
        EstadoDefault();
    }
  
    void EstadoDefault(){
        LimpiarEntradas();
        ActivarControles(false);
        ActivarEntradas(false);
        txtCodigo.setEditable(false);
        txtEstado.setEditable(false);
        btnRegistrar.setText("Registrar");
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
            jdcFechaInicioPeriodo.requestFocus();     
            EstadoControles(true);
            ActivarEntradas(true);
            btnRegistrar.setText("Registrar");
        }else{
        //Estado Modificar
            ActivarEntradas(true);
            jdcFechaInicioPeriodo.setEnabled(true);
            ActivarControles(true);
            btnRegistrar.setText("Actualizar");
            EstadoControles(true);    
        }  
    }

    void ActivarControles(boolean sw){
        btnNuevo.setEnabled(!sw);
        btnRegistrar.setEnabled(sw);
        btnModificar.setEnabled(!sw);
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
   
    public void MostrarDatos(Periodo parPeriodo){  
        if(parPeriodo != null){
            txtCodigo.setText(String.valueOf(periodo.getIdperiodo()));     
            txtEstado.setText(periodo.getEstado());
            jdcFechaInicioPeriodo.setDate(periodo.getFechainicioperiodo()); 
            jdcFechaFinPeriodo.setDate(periodo.getFechafinperiodo());
        }  
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdcFechaFinPeriodo = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jdcFechaInicioPeriodo = new com.toedter.calendar.JDateChooser();
        txtEstado = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Periodo"));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 119, -1));

        jLabel2.setText("Estado:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel3.setText("Inicio del Periodo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jdcFechaFinPeriodo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaFinPeriodoPropertyChange(evt);
            }
        });
        jPanel1.add(jdcFechaFinPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 130, -1));

        jLabel4.setText("Fin del Periodo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jdcFechaInicioPeriodo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaInicioPeriodoPropertyChange(evt);
            }
        });
        jPanel1.add(jdcFechaInicioPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 130, -1));
        jPanel1.add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 119, -1));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 170));
        jPanel1.getAccessibleContext().setAccessibleName("Datos");

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 80, -1));
        btnRegistrar.getAccessibleContext().setAccessibleName("vdv");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 80, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:   
        int idPeriodo = 0;

        if(txtCodigo.getText().length() > 0){
            idPeriodo = Integer.parseInt(txtCodigo.getText().trim());
        }
        String EstadoPeriodo = (txtEstado.getText()).toUpperCase();
        long date = 0;     
        Date Fechainicio = jdcFechaInicioPeriodo.getDate();                
        date = Fechainicio.getTime(); 
        java.sql.Date fechainicioperiodo= new java.sql.Date(date);
        Date Fechafin = jdcFechaFinPeriodo.getDate();                
        date = Fechafin.getTime(); 
        java.sql.Date fechafinperiodo= new java.sql.Date(date); 
      
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
                    }
                } catch (Exception e) {
                    Mensaje.mostrarError(this, "Error de Ingreso");
                }
            } else
                JOptionPane.showMessageDialog(null,"faltan llenar datos");
        } else {
            if(jdcFechaFinPeriodo.getDate() != null){
                    try{
                        periodo = new Periodo(idPeriodo, EstadoPeriodo, fechainicioperiodo, fechafinperiodo);
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
         EstadoDefault();
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
        ConsultarPeriodos(); 
        if(listaPeriodo != null && !listaPeriodo.isEmpty()){
            for(Periodo objetoPeriodo:listaPeriodo){                                
                if(objetoPeriodo.verificarPeriodoVigente()){        
                    JOptionPane.showMessageDialog(null,"Ya existe periodo en vigencia");
                    EstadoDefault();    
                }else
                    Estado(true);
            }
        }else
            Estado(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        ConsultarPeriodos();
        Periodo periodovigente  = null;
        if(listaPeriodo != null){
            for(int i = 0; i <  listaPeriodo.size(); i++){                                
                periodo = (Periodo) listaPeriodo.get(i); 
                if(periodo.verificarPeriodoVigente()){
                    MostrarDatos(periodo);
                    periodovigente = periodo;
                    btnRegistrar.setText("Actualizar");
                    Estado(false);
                    break;
                }  
            }
            if(periodovigente == null)
                JOptionPane.showMessageDialog(null,"Sin periodo vigente");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcFechaFinPeriodo;
    private com.toedter.calendar.JDateChooser jdcFechaInicioPeriodo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEstado;
    // End of variables declaration//GEN-END:variables
}
