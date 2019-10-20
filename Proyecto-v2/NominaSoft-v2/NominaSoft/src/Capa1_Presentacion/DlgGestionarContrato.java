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
    private List<Contrato> listaContratos;
    
    
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
        ActivarControles(true);
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
        btnBuscarEmpleado.setVisible(sw);
        btnCrearContrato.setVisible(!sw);
        btnEditarContrato.setVisible(!sw);
        btnAnularContrato.setVisible(!sw); 
        btnBuscarEmpleado.setEnabled(sw);
        btnCrearContrato.setEnabled(!sw);
        btnEditarContrato.setEnabled(!sw);
        btnAnularContrato.setEnabled(!sw);
    }
    
    void EstadoControles(boolean sw){
        btnBuscarEmpleado.setEnabled(false);
        btnCrearContrato.setEnabled(sw);
        btnEditarContrato.setEnabled(!sw);
        btnAnularContrato.setEnabled(!sw);
    }
    
    public void VerificarEmpleado(String dni){      
        
        try{
            ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();
            empleado = gestionarEmpleado.VerificarEmpleado(dni);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        } 
        if (empleado == null){
            Mensaje.mostrarAfirmacion(this, "No exite empleado");
        }
    }
        
    public void BuscarContratos(int idempleado){     
        
        try{
            ServGestionarContrato gestionarContrato = new ServGestionarContrato();
            listaContratos = gestionarContrato.BuscarContratos(idempleado);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error dr Registro");            
        }
        if (listaContratos == null){
            Mensaje.mostrarAfirmacion(this, "No exite contratos");
        }
    }   
        
    public void MostrarDatos(Empleado empleado){
        lblVerCodigo.setText(String.valueOf(empleado.getIdempleado()));
        lblVerNombre.setText(empleado.getNombre());
        txtDni.setText(empleado.getDni());        
        lblVerNacimiento.setText(empleado.getFechanacimiento().toString());      
        lblVerEstadoCivil.setText(empleado.getEstadocivil()); 
        lblVerGrado.setText(empleado.getGradoacademico()); 
        lblVerTelefono.setText(empleado.getTelefono());               
        lblVerDireccion.setText(empleado.getDireccion());
        
        BuscarContratos(empleado.getIdempleado());
        
        if(listaContratos != null && listaContratos.size() > 0){
            for(int i = 0; i <  listaContratos.size(); i++){                                
                contrato = (Contrato) listaContratos.get(i);
                if(contrato.verificarContratoVigente() == true){   
                    lblCodigoContrato.setText(String.valueOf(contrato.getIdcontrato()));     
                    lblEstadoContrato.setText("Un Contrato Vigente");
                    EstadoControles(false);
                    break;
                }else{
                    lblCodigoContrato.setText("...");
                    lblEstadoContrato.setText("Sin Contrato Vigente");
                    EstadoControles(true);
                }                 
            }        
        }else{
            lblCodigoContrato.setText("...");
            lblEstadoContrato.setText("Sin Contrato Registrado");
            EstadoControles(true);
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
        jLabel4 = new javax.swing.JLabel();
        lblCodigoContrato = new javax.swing.JLabel();
        lblEstadoContrato = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAnularContrato = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnBuscarEmpleado = new javax.swing.JButton();
        btnLimpiarDni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        getContentPane().add(btnEditarContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 110, -1));

        jpnContrato.setBorder(javax.swing.BorderFactory.createTitledBorder("Contrato"));
        jpnContrato.setOpaque(false);
        jpnContrato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Codigo:");
        jpnContrato.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblCodigoContrato.setText("...");
        jpnContrato.add(lblCodigoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 70, -1));

        lblEstadoContrato.setText("...");
        jpnContrato.add(lblEstadoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 180, -1));

        jLabel6.setText("Estado:");
        jpnContrato.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        getContentPane().add(jpnContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 470, 60));

        btnAnularContrato.setText("Anular Contrato");
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
                this.jpnContrato.setVisible(true);
                this.jpnEmpleado.setVisible(true); 
                ActivarControles(false);
                MostrarDatos(empleado);                 
            }else{
                int seleccion = JOptionPane.showConfirmDialog(this,"¿Deseas Agregar Empleado? ","!Atencion!",JOptionPane.YES_NO_OPTION );
                if( seleccion == JOptionPane.YES_OPTION ) { 
                    VGlobales.globalDNI = verDni;
                    DlgEmpleado DE = new DlgEmpleado(null, true);             
                    DE.setVisible(true);   
                }            
            }
        } else
        JOptionPane.showMessageDialog(null,"Ingrese DNI");

    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    private void btnLimpiarDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarDniActionPerformed
        // TODO add your handling code here:
        ActivarControles(true);
        this.jpnContrato.setVisible(false);
        this.jpnEmpleado.setVisible(false);
        this.btnLimpiarDni.setVisible(false);
        LimpiarEntradas();
        txtDni.requestFocus();
 
    }//GEN-LAST:event_btnLimpiarDniActionPerformed

    private void btnCrearContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearContratoActionPerformed
        // TODO add your handling code here:
        VGlobales.globalDNI = txtDni.getText();
        DlgContrato DC = new DlgContrato(null, true);             
        DC.setVisible(true); 
    }//GEN-LAST:event_btnCrearContratoActionPerformed

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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpnContrato;
    private javax.swing.JPanel jpnEmpleado;
    private javax.swing.JLabel lblCodigoContrato;
    private javax.swing.JLabel lblEstadoContrato;
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
