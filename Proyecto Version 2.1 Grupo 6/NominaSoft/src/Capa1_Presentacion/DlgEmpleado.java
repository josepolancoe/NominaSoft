/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarEmpleado;
import Capa3_Dominio.Empleado;
import Capa5_Excepcion.Mensaje;
import Capa6_Globales.EstiloEtiqueta;
import Capa6_Globales.IngresoDni;
import Capa6_Globales.VGlobales;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author lucab
 */
public class DlgEmpleado extends javax.swing.JDialog {

    /**
     * Creates new form DlgEmpleado
     */
    private Empleado empleado;
    private boolean estado, inicio = true; 
    private String Nombre, Dni, EstadoCivil, GradoAcademico, Telefono, Direccion;
    private int idEmpleado = 0;
    private java.sql.Date FechaNacimiento;
    //IngresoDni ingresoDni;
   
    public DlgEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(new Color(153,153,153));
        setLocationRelativeTo(null);
        setResizable(false); 
        setTitle("Empleado");
        EstadoDefault();
        ObtenerDniContrato();
    }
    
    private void EstadoVerificar(){       
        VerificarEmpleado(txtDni.getText());
        ActivarControles(true);
        ActivarEntradas(true); 
        EstadoEntradas(true);
        VisibilidadControles(true);
        if(empleado != null){
            estado = false;        
            MostrarDatos(empleado);                
            btnRegistrar.setText("Actualizar");
            btnCancelar.setText("Terminar");   
            setMnemonic();
        }else{
            estado = true;                   
            EstadoControles(false);  
            btnEliminar.setEnabled(false);
            txtDni.requestFocus();
            btnCancelar.setText("Cancelar");
            btnRegistrar.setText("Registrar"); 
            setMnemonic();                             
        }
        txtNombre.requestFocus();     
    }
    
    public void ObtenerDni(String parDni){    
        Dni = parDni;
        txtDni.setText(parDni);
        EstadoVerificar();
    }

    void ObtenerDniContrato(){ 
        String Dni = VGlobales.globalDNI;
        if(Dni.compareTo("")!=0){
            txtDni.setText(Dni);
            estado = true;
            ActivarEntradas(true);
            EstadoEntradas(true);
            ActivarControles(true);
            EstadoControles(false);  
            btnEliminar.setEnabled(false);
            txtNombre.requestFocus();
        }
    }  
    
    void EstadoDefault(){
        LimpiarEntradas();
        Estilo();
//        ActivarControles(false);
//        ActivarEntradas(false);
//        EstadoEntradas(false);
//        VisibilidadControles(false);
        txtDni.requestFocus();
        btnCancelar.setText("Cancelar");
        btnRegistrar.setText("Registrar");
    }
    
    void EstadoControles(boolean sw){
        btnVerificaEmpleado.setEnabled(false);
        btnRegistrar.setEnabled(!sw);
        btnEliminar.setEnabled(!sw);
        btnCancelar.setEnabled(true);     
    }
  
    void LimpiarEntradas(){
        txtCodigo.setText("");
        txtDni.setText("");
        txtNombre.setText("");
        jdcNacimiento.setDate(null);
        cboEsCivil.setSelectedIndex(0);
        cboGrado.setSelectedIndex(0);
        txtTelefono.setText("");
        txtDireccion.setText("");
    }
    
    void ActivarControles(boolean sw){
        btnVerificaEmpleado.setEnabled(!sw);
        btnRegistrar.setEnabled(sw);
        btnEliminar.setEnabled(sw);
        btnCancelar.setEnabled(sw);
    }
    
    void ActivarEntradas(boolean sw){       
        txtCodigo.setEnabled(sw);
        txtNombre.setEnabled(sw);
        jdcNacimiento.setEnabled(sw);
        cboEsCivil.setEnabled(sw);
        cboGrado.setEnabled(sw);       
        txtTelefono.setEnabled(sw);       
        txtDireccion.setEnabled(sw);
    } 
    
    void EstadoEntradas(boolean sw){
        txtCodigo.setEditable(false);
        txtDni.setEditable(!sw);
        txtNombre.setEditable(sw);
        cboEsCivil.setEditable(sw);
        cboGrado.setEditable(sw);
        txtTelefono.setEditable(sw);
        txtDireccion.setEditable(sw);
    }
    
    void VisibilidadControles(boolean sw){
        this.jpnIdentidad.setVisible(true);
        this.jpnDatos.setVisible(sw);
        this.jpnContacto.setVisible(sw);
        this.btnVerificaEmpleado.setVisible(!sw);
        this.btnRegistrar.setVisible(sw);
        this.btnEliminar.setVisible(sw);
        this.btnCancelar.setVisible(sw); 
    }
    
 

    private void VerificarEmpleado(String parDni){             
        try{
            ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();
            empleado = gestionarEmpleado.VerificarEmpleado(parDni);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        } 
        if (empleado == null){
            Mensaje.mostrarAfirmacion(this, "No exite Empleado");
        }
    }
                   
    private void seleccionarValorCombo(JComboBox combo, String valor){
        String item;
        for (int i = 0; i < combo.getModel().getSize(); i++)
        {
            item = combo.getModel().getElementAt(i).toString();
            if (item.equalsIgnoreCase(valor))
            {
                combo.setSelectedItem(item);               
                break;
            }
        }
    } 

    private void MostrarDatos(Empleado parEmpleado){
        if(parEmpleado != null){
            txtCodigo.setText(String.valueOf(parEmpleado.getIdempleado()));
            txtNombre.setText(parEmpleado.getNombre());
            txtDni.setText(parEmpleado.getDni());        
            jdcNacimiento.setDate(parEmpleado.getFechanacimiento());             
            String estadoCivil = (parEmpleado.getEstadocivil());
            seleccionarValorCombo(cboEsCivil, estadoCivil);       
            String gradoAcademico = (parEmpleado.getGradoacademico());
            seleccionarValorCombo(cboGrado, gradoAcademico);       
            txtTelefono.setText(parEmpleado.getTelefono());               
            txtDireccion.setText(parEmpleado.getDireccion());
            btnEliminar.setEnabled(true);
            btnCancelar.setEnabled(true);
            this.jpnDatos.setVisible(true);
            this.jpnContacto.setVisible(true);
        }
    }
    
    private void CompruebaDni(){
        if(txtDni.getText().compareTo("")!=0 && txtDni.getText().length() == 8){  
            EstadoVerificar();
        } else
            JOptionPane.showMessageDialog(null,"Ingrese DNI");
    }

    private void ObtenerDatos(){
        if(txtNombre.getText().compareTo("")!=0 && txtDni.getText().compareTo("")!=0 && txtTelefono.getText().compareTo("")!=0  && txtDireccion.getText().compareTo("")!=0)
            {  
            if(txtCodigo.getText().length() > 0){
                idEmpleado = Integer.parseInt(txtCodigo.getText().trim());
            }
            Dni = txtDni.getText();
            Nombre = txtNombre.getText();                  
            long date = 0;     
            Date Fecha = jdcNacimiento.getDate();                
            date = Fecha.getTime(); 
            FechaNacimiento = new java.sql.Date(date); 
            GradoAcademico = String.valueOf(cboGrado.getSelectedItem());
            EstadoCivil = String.valueOf(cboEsCivil.getSelectedItem());
            Telefono = txtTelefono.getText();           
            Direccion = txtDireccion.getText();       
        } else
            JOptionPane.showMessageDialog(null,"faltan llenar datos");
    }
    
    private void RegistraActualiza(){
        ObtenerDatos();
        if(estado) {
            if(txtNombre.getText().compareTo("")!=0 && txtDni.getText().compareTo("")!=0 && txtTelefono.getText().compareTo("")!=0  && txtDireccion.getText().compareTo("")!=0)
            {  
                try {                 
                    empleado = new Empleado(Dni, Nombre, FechaNacimiento, GradoAcademico, EstadoCivil, Telefono, Direccion);                    
                    ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();
                    int registros_afectados = gestionarEmpleado.IngresarEmpleado(empleado);
                    
                    if(registros_afectados == 1){
                        JOptionPane.showMessageDialog(null,"Empleado registrado");
                        if(VGlobales.globalDNI.compareTo("")!=0){
                            this.dispose();
                        }
                        estado = false;
                        EstadoControles(false);
                        btnRegistrar.setText("Actualizar");
                        btnCancelar.setText("Finalizar");  
                        VerificarEmpleado(Dni);
                        MostrarDatos(empleado);
                        
                    }    
                } catch (Exception e) {
                    Mensaje.mostrarError(this, "Error de Ingreso");
                }
            } else
                JOptionPane.showMessageDialog(null,"faltan llenar datos");
        } else {
            if(txtNombre.getText().compareTo("")!=0 && txtDni.getText().compareTo("")!=0 && txtTelefono.getText().compareTo("")!=0  && txtDireccion.getText().compareTo("")!=0){                
                try {
                    empleado = new Empleado(idEmpleado, Dni, Nombre, FechaNacimiento, GradoAcademico, EstadoCivil, Telefono, Direccion);                                      
                    ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();                          
                    int registros_afectados = gestionarEmpleado.ModificarEmpleado(empleado);
                    
                    if(registros_afectados == 1){
                        JOptionPane.showMessageDialog(null,"Empleado Actualizado");                    
                        ActivarControles(true);
                        ActivarEntradas(true);                    
                        EstadoControles(false);
                        btnCancelar.setText("Terminar");   
                        MostrarDatos(empleado);
                    }     
                } catch (Exception e) {
                    Mensaje.mostrarError(this, "Error de Actualizacion");
                }
            } else
                JOptionPane.showMessageDialog(null,"faltan llenar datos");
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

        jpnContacto = new javax.swing.JPanel();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jpnIdentidad = new javax.swing.JPanel();
        lblDni = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnVerificaEmpleado = new javax.swing.JButton();
        jpnDatos = new javax.swing.JPanel();
        cboEsCivil = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        lblNacimiento = new javax.swing.JLabel();
        lblGrado = new javax.swing.JLabel();
        jdcNacimiento = new com.toedter.calendar.JDateChooser();
        cboGrado = new javax.swing.JComboBox<>();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnContacto.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacto"));
        jpnContacto.setOpaque(false);
        jpnContacto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTelefono.setText("Telefono:");
        jpnContacto.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));
        jpnContacto.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 340, -1));

        lblDireccion.setText("Direccion:");
        jpnContacto.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));
        jpnContacto.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 340, -1));

        getContentPane().add(jpnContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 450, 100));

        jpnIdentidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Identidad"));
        jpnIdentidad.setOpaque(false);
        jpnIdentidad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDni.setText("DNI:");
        jpnIdentidad.add(lblDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 30, 20));

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        jpnIdentidad.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 100, -1));

        btnVerificaEmpleado.setText("Verificar");
        btnVerificaEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificaEmpleadoActionPerformed(evt);
            }
        });
        jpnIdentidad.add(btnVerificaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        getContentPane().add(jpnIdentidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 340, 80));

        jpnDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jpnDatos.setOpaque(false);
        jpnDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboEsCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccionar>", "Casado(a)", "Soltero(a)", "Viudo(a)", "Divorsiado(a)", "Separados" }));
        cboEsCivil.setName("Paciente"); // NOI18N
        jpnDatos.add(cboEsCivil, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 100, -1));

        lblEstado.setText("Estado Civil:");
        jpnDatos.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 20));

        lblNacimiento.setText("Fecha de Nacimineto:");
        jpnDatos.add(lblNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        lblGrado.setText("Grado Academico:");
        jpnDatos.add(lblGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 20));
        jpnDatos.add(jdcNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 260, -1));

        cboGrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccionar>", "Primaria", "Secundaria", "Bachiller", "Profesional", "Magister", "Doctor", " " }));
        jpnDatos.add(cboGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 100, -1));

        lblNombre.setText("Nombre:");
        jpnDatos.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jpnDatos.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 340, -1));

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jpnDatos.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 100, -1));

        lblCodigo.setText("Codigo:");
        jpnDatos.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        getContentPane().add(jpnDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 450, 190));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 90, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 90, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
        String patron_de_entrada = "0123456789";
        if (txtDni.getText().length() == 8 ||
            !patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))
        evt.consume();
    }//GEN-LAST:event_txtDniKeyTyped

    private void btnVerificaEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificaEmpleadoActionPerformed
        // TODO add your handling code here:  
        CompruebaDni();
    }//GEN-LAST:event_btnVerificaEmpleadoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        if (txtCodigo.getText().length() == 5)
        evt.consume();
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if(!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_SPACE) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE))
        evt.consume();
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        RegistraActualiza();   
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if(btnCancelar.getText().equalsIgnoreCase("Finalizar")){
            this.dispose();
        }else{
            EstadoDefault();
            this.dispose();   
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:   
        if(txtDni.getText().compareTo("")!=0){    
            int seleccion = JOptionPane.showConfirmDialog(this,"Deseas Eliminarlo ","Eliminar Empleado",JOptionPane.YES_NO_OPTION );
            if( seleccion == JOptionPane.YES_OPTION ) {   
                try {                
                    ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();                
                    int registros_afectados = gestionarEmpleado.EliminarEmpleado(empleado);

                    if(registros_afectados == 1){
                        JOptionPane.showMessageDialog(this,"Empleado Eliminado");
                        EstadoDefault();
                        //this.dispose();
                    }
                } catch (Exception e) {
                    Mensaje.mostrarError(this, "Error al Eliminar");
                    Mensaje.mostrarAdvertencia(this, "El empleado se eliminara solo si no tiene ningun contrato activo");
                }       
            }          
        } else
            JOptionPane.showMessageDialog(null,"Sin datos para eliminar");
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        // TODO add your handling code here: 
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            CompruebaDni();
        }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_txtDniKeyPressed

    void setMnemonic(){  
        btnEliminar.setMnemonic(KeyEvent.VK_E);
        if(estado == true){
            btnRegistrar.setMnemonic(KeyEvent.VK_R);
            btnCancelar.setMnemonic(KeyEvent.VK_C);
        }else{
            btnRegistrar.setMnemonic(KeyEvent.VK_A);
            btnCancelar.setMnemonic(KeyEvent.VK_T);    
        }
    }
    
    void Estilo(){
        Object[ ] etiquetas = {lblDni, lblCodigo, lblNombre, lblNacimiento, lblEstado, lblGrado, lblTelefono,
            lblDireccion};
        EstiloEtiqueta estilo = new EstiloEtiqueta(etiquetas);
        jpnIdentidad.setFont(new Font("Arial", 1, 12));    
        jpnDatos.setFont(new Font("Arial", 1, 12));
        jpnContacto.setFont(new Font("Arial", 1, 12));
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
            java.util.logging.Logger.getLogger(DlgEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                   
                DlgEmpleado dialog = new DlgEmpleado(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVerificaEmpleado;
    private javax.swing.JComboBox<String> cboEsCivil;
    private javax.swing.JComboBox<String> cboGrado;
    private com.toedter.calendar.JDateChooser jdcNacimiento;
    private javax.swing.JPanel jpnContacto;
    private javax.swing.JPanel jpnDatos;
    private javax.swing.JPanel jpnIdentidad;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblGrado;
    private javax.swing.JLabel lblNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
