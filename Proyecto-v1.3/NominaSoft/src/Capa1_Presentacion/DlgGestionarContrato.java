/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarContrato;
import Capa2_Aplicacion.ServGestionarEmpleado;
import Capa3_Dominio.Contrato;
import Capa3_Dominio.Empleado;
import Capa5_Excepcion.Mensaje;
import Capa6_Globales.VGlobales;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lucab
 */
public class DlgGestionarContrato extends javax.swing.JDialog {

    /**
     * Creates new form DlgContrato
     */
    private Empleado empleado;
    private Contrato contrato;
    private Contrato objetoContrato;
    private List<Contrato> listaContrato;
    
    
    public DlgGestionarContrato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this .getContentPane().setBackground(new Color(255,255,255));
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Gestionar Contrato");
        EstadoDefault();
    }
    
    void EstadoDefault(){
        LimpiarEntradas();
        ActivarControles(false);
        txtDni.requestFocus();
        this.jpnContrato.setVisible(false);
        this.jpnEmpleado.setVisible(false);
        this.btnLimpiarDni.setVisible(false);
    }
    
    void LimpiarEntradas(){
        txtDni.setText("");
        lblVerCodigo.setText("");        
        lblVerNombre.setText("");
        lblVerNacimiento.setText("");
        lblVerEstadoCivil.setText("");
        lblVerGrado.setText("");
        lblVerTelefono.setText("");
        lblVerDireccion.setText("");
    }
    
    void ActivarControles(boolean sw){
        btnBuscarEmpleado.setEnabled(!sw);
        btnCrearContrato.setEnabled(sw);
        btnEditarContrato.setEnabled(sw);
        btnAnularContrato.setEnabled(sw);
    }
    
    void EstadoControles(boolean sw){
        btnBuscarEmpleado.setEnabled(false);
        btnCrearContrato.setEnabled(!sw);
        btnEditarContrato.setEnabled(sw);
        btnAnularContrato.setEnabled(sw);
    }
    
    public void VerificarEmpleado(String parDni){      
        
        if(parDni.length() == 8){
            try{
                ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();
                empleado = gestionarEmpleado.VerificarEmpleado(parDni);  
            }catch (Exception e) {   
                Mensaje.mostrarError(this, "Error de Registro Empleado");            
            } 
                if (empleado == null){
                Mensaje.mostrarAfirmacion(this, "No exite empleado");
                int seleccion = JOptionPane.showConfirmDialog(this,"¿Deseas Agregar Empleado? ","!Atencion!",JOptionPane.YES_NO_OPTION );
                if(seleccion == JOptionPane.YES_OPTION) { 
                    VGlobales.globalDNI = parDni;
                    DlgEmpleado DE = new DlgEmpleado(null, true);             
                    DE.setVisible(true); 
                }
            }
                                           
        }else
            JOptionPane.showMessageDialog(null,"Ingrese DNI");
    }
        
    public void ConsultarContratos(int parIdEmpleado){     
        try{
            ServGestionarContrato gestionarContrato = new ServGestionarContrato();
            listaContrato = gestionarContrato.BuscarContratos(parIdEmpleado);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro Contrato");            
        }
        if (listaContrato == null){
            Mensaje.mostrarAfirmacion(this, "Sin Contrato Registrado");
        }
    }   
    
    public void MostrarContrato(){   
        if(listaContrato != null){
            for(Contrato objetoContrato : listaContrato){         
                if(objetoContrato.verificarContratoVigente() == true){   
                    lblCodigoContrato.setText(String.valueOf(objetoContrato.getIdcontrato()));     
                    lblCondicionContrato.setText("VIGENTE");
                    EstadoControles(true);
                    break;
                }else{
                    lblCodigoContrato.setText(String.valueOf(objetoContrato.getIdcontrato()));
                    lblCondicionContrato.setText(objetoContrato.getCondicion());
                    EstadoControles(false);
                }                               
            } 
        }
    }
        
    public void MostrarDatos(Empleado parEmpleado){
        if(parEmpleado != null){
            lblVerCodigo.setText(String.valueOf(parEmpleado.getIdempleado()));
            lblVerNombre.setText(parEmpleado.getNombre());
            txtDni.setText(parEmpleado.getDni());        
            lblVerNacimiento.setText(parEmpleado.getFechanacimiento().toString());      
            lblVerEstadoCivil.setText(parEmpleado.getEstadocivil()); 
            lblVerGrado.setText(parEmpleado.getGradoacademico()); 
            lblVerTelefono.setText(parEmpleado.getTelefono());               
            lblVerDireccion.setText(parEmpleado.getDireccion()); 
            this.jpnContrato.setVisible(true);
            this.jpnEmpleado.setVisible(true);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnEmpleado = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblVerCodigo = new javax.swing.JLabel();
        lblVerNombre = new javax.swing.JLabel();
        lblVerNacimiento = new javax.swing.JLabel();
        lblVerEstadoCivil = new javax.swing.JLabel();
        lblVerGrado = new javax.swing.JLabel();
        lblVerTelefono = new javax.swing.JLabel();
        lblVerDireccion = new javax.swing.JLabel();
        btnCrearContrato = new javax.swing.JButton();
        btnEditarContrato = new javax.swing.JButton();
        jpnContrato = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblCodigoContrato = new javax.swing.JLabel();
        lblCondicion = new javax.swing.JLabel();
        lblCondicionContrato = new javax.swing.JLabel();
        btnAnularContrato = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnBuscarEmpleado = new javax.swing.JButton();
        btnLimpiarDni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));
        jpnEmpleado.setOpaque(false);
        jpnEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Codigo:");
        jpnEmpleado.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("Nombre:");
        jpnEmpleado.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel5.setText("Fecha de Nacimineto:");
        jpnEmpleado.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        jLabel11.setText("Estado Civil:");
        jpnEmpleado.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel7.setText("Grado Academico:");
        jpnEmpleado.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel8.setText("Telefono:");
        jpnEmpleado.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel9.setText("Direccion:");
        jpnEmpleado.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        lblVerCodigo.setText("...");
        jpnEmpleado.add(lblVerCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 270, -1));

        lblVerNombre.setText("...");
        lblVerNombre.setToolTipText("");
        jpnEmpleado.add(lblVerNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 270, -1));

        lblVerNacimiento.setText("...");
        jpnEmpleado.add(lblVerNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 270, -1));

        lblVerEstadoCivil.setText("...");
        jpnEmpleado.add(lblVerEstadoCivil, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 270, -1));

        lblVerGrado.setText("...");
        jpnEmpleado.add(lblVerGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 270, -1));

        lblVerTelefono.setText("...");
        jpnEmpleado.add(lblVerTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 270, -1));

        lblVerDireccion.setText("...");
        jpnEmpleado.add(lblVerDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 270, -1));

        getContentPane().add(jpnEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 470, 240));

        btnCrearContrato.setText("Crear Contrato");
        btnCrearContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearContratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrearContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 110, -1));

        btnEditarContrato.setText("Editar Contrato");
        btnEditarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarContratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 110, -1));

        jpnContrato.setBorder(javax.swing.BorderFactory.createTitledBorder("Contrato"));
        jpnContrato.setOpaque(false);
        jpnContrato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigo.setText("Codigo:");
        jpnContrato.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblCodigoContrato.setText("...");
        jpnContrato.add(lblCodigoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 70, -1));

        lblCondicion.setText("Condicion:");
        jpnContrato.add(lblCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        lblCondicionContrato.setText("...");
        jpnContrato.add(lblCondicionContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 60, -1));

        getContentPane().add(jpnContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 470, 60));

        btnAnularContrato.setText("Anular Contrato");
        btnAnularContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularContratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnularContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 110, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("DNI:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 100, -1));

        btnBuscarEmpleado.setText("Buscar");
        btnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 70, -1));

        btnLimpiarDni.setText("Reset");
        btnLimpiarDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarDniActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiarDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 70, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 350, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
        String patron_de_entrada = "0123456789.";
        if (txtDni.getText().length() == 8 || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))  
            evt.consume();
        
        if(txtDni.getText().length() > 0)
            this.btnLimpiarDni.setVisible(true);
        else
            this.btnLimpiarDni.setVisible(false);
    }//GEN-LAST:event_txtDniKeyTyped

    private void btnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
        String verDni;
 
        if(txtDni.getText().compareTo("")!=0 && txtDni.getText().length() == 8){
            verDni = txtDni.getText();
            VerificarEmpleado(verDni);
            
            if(empleado != null){ 
                MostrarDatos(empleado);    
                ConsultarContratos(empleado.getIdempleado());
                if(listaContrato != null && listaContrato.size() > 0){ 
                    MostrarContrato(); 
                }else{
                    lblCodigoContrato.setText("");
                    this.lblCodigo.setVisible(false);
                    lblCondicionContrato.setText("");
                    this.lblCondicion.setVisible(false);
                    EstadoControles(false);
                }
            } 
        } else
        JOptionPane.showMessageDialog(null,"Ingrese DNI");
        verDni = "";

    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    private void btnLimpiarDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarDniActionPerformed
        // TODO add your handling code here:
        ActivarControles(false);
        this.jpnContrato.setVisible(false);
        this.jpnEmpleado.setVisible(false);
        this.btnLimpiarDni.setVisible(false);
        this.lblCondicion.setVisible(true);
        this.lblCodigo.setVisible(true);
        LimpiarEntradas();
        txtDni.requestFocus();
 
    }//GEN-LAST:event_btnLimpiarDniActionPerformed

    private void btnCrearContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearContratoActionPerformed
        // TODO add your handling code here:
        VGlobales.globalDNI = txtDni.getText();
        VGlobales.globalEstadoContrato = "Crear";
        DlgContrato DC = new DlgContrato(null, true);             
        DC.setVisible(true); 
    }//GEN-LAST:event_btnCrearContratoActionPerformed

    private void btnEditarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarContratoActionPerformed
        // TODO add your handling code here:
        VGlobales.globalDNI = txtDni.getText();
        VGlobales.globalIdContrato = Integer.valueOf(lblCodigoContrato.getText());
        VGlobales.globalEstadoContrato = "Editar";
        DlgContrato DC = new DlgContrato(null, true);             
        DC.setVisible(true);
    }//GEN-LAST:event_btnEditarContratoActionPerformed

    private void btnAnularContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularContratoActionPerformed
        // TODO add your handling code here:
        int seleccion = JOptionPane.showConfirmDialog(this,"¿Deseas anular el Contrato? ","!Atencion!",JOptionPane.YES_NO_OPTION );
        if(seleccion == JOptionPane.YES_OPTION) { 
            try {                
                ServGestionarContrato gestionarContrato = new ServGestionarContrato();                
                int registros_afectados = gestionarContrato.AnularContrato(objetoContrato);

                if(registros_afectados == 1){
                    JOptionPane.showMessageDialog(this,"Contrato Anulado");
                    EstadoDefault();
                }
            } catch (Exception e) {
                Mensaje.mostrarError(this, "Error al Anular");
            }
        }
  
    }//GEN-LAST:event_btnAnularContratoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        VGlobales.globalDNI = "";
        VGlobales.globalEstadoContrato = "";
        VGlobales.globalIdContrato = 0;
    }//GEN-LAST:event_formWindowClosed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        // TODO add your handling code here:
        String Dni;
        Dni = txtDni.getText();
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){    
            VerificarEmpleado(Dni);     
            if(empleado != null){ 
                MostrarDatos(empleado);    
                ConsultarContratos(empleado.getIdempleado());
                if(listaContrato != null){
                    MostrarContrato();
                    ActivarControles(false);
                }
            }    
        }

    }//GEN-LAST:event_txtDniKeyPressed

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
            java.util.logging.Logger.getLogger(DlgGestionarContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgGestionarContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgGestionarContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgGestionarContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgGestionarContrato dialog = new DlgGestionarContrato(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAnularContrato;
    private javax.swing.JButton btnBuscarEmpleado;
    private javax.swing.JButton btnCrearContrato;
    private javax.swing.JButton btnEditarContrato;
    private javax.swing.JButton btnLimpiarDni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpnContrato;
    private javax.swing.JPanel jpnEmpleado;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoContrato;
    private javax.swing.JLabel lblCondicion;
    private javax.swing.JLabel lblCondicionContrato;
    private javax.swing.JLabel lblVerCodigo;
    private javax.swing.JLabel lblVerDireccion;
    private javax.swing.JLabel lblVerEstadoCivil;
    private javax.swing.JLabel lblVerGrado;
    private javax.swing.JLabel lblVerNacimiento;
    private javax.swing.JLabel lblVerNombre;
    private javax.swing.JLabel lblVerTelefono;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
