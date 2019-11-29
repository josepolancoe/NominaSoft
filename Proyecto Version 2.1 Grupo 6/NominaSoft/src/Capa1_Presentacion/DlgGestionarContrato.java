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
import Capa6_Globales.AtajosTecladoDlg;
import Capa6_Globales.EstiloEtiqueta;
import Capa6_Globales.VGlobales;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Date;
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
    private Contrato contratoPrevio;
    private Contrato contratoVigente;
    private List<Contrato> listaContrato;    
    private String Dni;
    
    public DlgGestionarContrato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(new Color(153,153,153));
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Gestionar Contrato");
        EstadoDefault();
    }
       
    void EstadoDefault(){
        LimpiarEntradas();
//        ActivarControles(false);
        txtDni.requestFocus();
        btnAnularContrato.setText("Anular Contrato");
//        this.jpnDatosContrato.setVisible(false);
//        this.jpnDatosEmpleado.setVisible(false);
        this.btnLimpiar.setVisible(false);
        setMnemonic();
        Estilo();
        EventoTecla();
    }
    //        System.out.println("");    
    void EstadoVerificar(){
        ActivarControles(true);
        VisibilidadControles(true);
        VerificarEmpleado(Dni);        
        if(empleado != null){
            MostrarDatosEmpleado(empleado);
            VerificarContratoVigente(empleado.getIdempleado());
            if(contratoVigente != null){
                MostrarDatosContrato(contratoVigente);
                EstadoControles(true);
            }else{
                VerificarContratoPrevio(empleado.getIdempleado());
                MostrarDatosContrato(contratoPrevio);
                EstadoControles(false);
            } 
        }else
            JOptionPane.showMessageDialog(null,"No existe empleado");      
    }
    
    
    public void ObtenerDni(String parDni){    
        Dni = parDni;
        txtDni.setText(parDni);
        //ObtenerContratoEmpleado();
        EstadoVerificar();
    }
    
    
    void LimpiarEntradas(){
        txtDni.setText("");
        lblVerCodigoEmpleado.setText("");        
        lblVerNombre.setText("");
        lblVerNacimiento.setText("");
        lblVerEstadoCivil.setText("");
        lblVerGrado.setText("");
        lblVerTelefono.setText("");
        lblVerDireccion.setText("");
    }
    
    void ActivarControles(boolean sw){
        btnVerificar.setEnabled(!sw);
        btnCrearContrato.setEnabled(sw);
        btnEditarContrato.setEnabled(sw);
        btnAnularContrato.setEnabled(sw);
    }
    
    void EstadoControles(boolean sw){
        btnVerificar.setEnabled(false);
        btnCrearContrato.setEnabled(!sw);
        btnEditarContrato.setEnabled(sw);
        btnAnularContrato.setEnabled(sw);
    }
    
    void VisibilidadControles(boolean sw){
        this.jpnBuscar.setVisible(true);
        this.jpnDatosEmpleado.setVisible(sw);
        this.jpnDatosContrato.setVisible(sw);
        this.btnCrearContrato.setVisible(sw);
        this.btnEditarContrato.setVisible(sw);
        this.btnAnularContrato.setVisible(sw);
    }
    
    public void VerificarEmpleado(String parDni){             
        if((parDni.compareTo("")!=0 && parDni.length() == 8)){
            try{
                ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();
                empleado = gestionarEmpleado.VerificarEmpleado(parDni);  
            }catch (Exception e) {   
                Mensaje.mostrarError(this, "Error de Registro Empleado");            
            } 
//            if (empleado == null){
//                Mensaje.mostrarAfirmacion(this, "No exite empleado");
//                int seleccion = JOptionPane.showConfirmDialog(this,"¿Deseas Agregar Empleado? ","!Atencion!",JOptionPane.YES_NO_OPTION );
//                if(seleccion == JOptionPane.YES_OPTION) { 
//                    //VGlobales.globalDNI = parDni;
//                    DlgEmpleado DE = new DlgEmpleado(null, true);
//                    DE.ObtenerDni(parDni);
//                    DE.setVisible(true); 
//                }else
//                    this.dispose();
//            }                               
        }else
            JOptionPane.showMessageDialog(null,"Ingrese DNI");
    }  
    
    public void BuscarContratos(int parIdEmpleado){
        try{
            ServGestionarContrato gestionarContrato = new ServGestionarContrato();
            listaContrato = gestionarContrato.BuscarContratos(parIdEmpleado);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de busqueda contrato");            
        }
    }
    
    public void VerificarContratoVigente(int parIdEmpleado){ 
        BuscarContratos(parIdEmpleado); 
        if(listaContrato != null){
            for(Contrato objetoContrato : listaContrato){         
                if(objetoContrato.verificarContratoVigente() == true){
                    contratoVigente = objetoContrato;
                    break;
                }                               
            }   
        }
    }
    
    public void VerificarContratoPrevio(int parIdEmpleado){
        BuscarContratos(parIdEmpleado); 
        int codigomayor = 0;
        if(!listaContrato.isEmpty()){
            for(Contrato objetoContrato : listaContrato){
                int codigo = objetoContrato.getIdcontrato();
                if(codigo > codigomayor)
                    codigomayor = codigo;
            }
        VerificarContrato(codigomayor);
        contratoPrevio = contrato;
        }
    }
    
    public void VerificarContrato(int parIdContrato){ 
        try{
            ServGestionarContrato gestionarContrato = new ServGestionarContrato();
            contrato = gestionarContrato.VerificarContrato(parIdContrato);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro Contrato");            
        }
        if (contrato == null){
            Mensaje.mostrarAfirmacion(this, "Sin Registro Contrato");
        }
    }
    
    public void ObtenerDatosContratoMostrar(){
//        if(txtDni.getText().compareTo("")!=0 && txtDni.getText().length() == 8){
//            VerificarEmpleado(txtDni.getText());
//            if(empleado != null){   
//                MostrarDatosEmpleado(empleado); 
//                BuscarContratos(empleado.getIdempleado());
//                if (listaContrato == null || listaContrato.isEmpty()){
//                    this.jpnDatosContrato.setVisible(false);
//                    Mensaje.mostrarAfirmacion(this, "Sin Contratos Registrados");
//                    EstadoControles(false);
//                    VerificarContratoVigente(empleado.getIdempleado());
//                }else if(contratoVigente != null){ 
//                        MostrarDatosContrato(contratoVigente);
//                        EstadoControles(true);
//                    }else{     
//                        VerificarContratoPrevio(empleado.getIdempleado());
//                        MostrarDatosContrato(contratoPrevio);
//                        EstadoControles(false);
//                    }            
//            }    
//        } else
//        JOptionPane.showMessageDialog(null,"Ingrese DNI");
   }  
    
    
    public void MostrarDatosContrato(Contrato parContrato){ 
        if(parContrato != null){
            lblVerCodigoContrato.setText(String.valueOf(parContrato.getIdcontrato()));
            lblVerCondicion.setText(parContrato.getCondicion());                           
        }
    }
    
    public void MostrarDatosEmpleado(Empleado parEmpleado){
        if(parEmpleado != null){
            lblVerCodigoEmpleado.setText(String.valueOf(parEmpleado.getIdempleado()));
            lblVerNombre.setText(parEmpleado.getNombre());
            txtDni.setText(parEmpleado.getDni());        
            lblVerNacimiento.setText(parEmpleado.getFechanacimiento().toString());      
            lblVerEstadoCivil.setText(parEmpleado.getEstadocivil()); 
            lblVerGrado.setText(parEmpleado.getGradoacademico()); 
            lblVerTelefono.setText(parEmpleado.getTelefono());               
            lblVerDireccion.setText(parEmpleado.getDireccion()); 
            this.jpnDatosContrato.setVisible(true);
            this.jpnDatosEmpleado.setVisible(true);
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

        jpnDatosEmpleado = new javax.swing.JPanel();
        lblCodigoEmpleado = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblNacimiento = new javax.swing.JLabel();
        lblEstadoCivil = new javax.swing.JLabel();
        lblGrado = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblVerCodigoEmpleado = new javax.swing.JLabel();
        lblVerNombre = new javax.swing.JLabel();
        lblVerNacimiento = new javax.swing.JLabel();
        lblVerEstadoCivil = new javax.swing.JLabel();
        lblVerGrado = new javax.swing.JLabel();
        lblVerTelefono = new javax.swing.JLabel();
        lblVerDireccion = new javax.swing.JLabel();
        btnCrearContrato = new javax.swing.JButton();
        btnEditarContrato = new javax.swing.JButton();
        jpnDatosContrato = new javax.swing.JPanel();
        lblCodigoContrato = new javax.swing.JLabel();
        lblVerCodigoContrato = new javax.swing.JLabel();
        lblCondicion = new javax.swing.JLabel();
        lblVerCondicion = new javax.swing.JLabel();
        btnAnularContrato = new javax.swing.JButton();
        jpnBuscar = new javax.swing.JPanel();
        lblDni = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnVerificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnDatosEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));
        jpnDatosEmpleado.setOpaque(false);
        jpnDatosEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigoEmpleado.setText("Codigo:");
        jpnDatosEmpleado.add(lblCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblNombre.setText("Nombre:");
        jpnDatosEmpleado.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        lblNacimiento.setText("Fecha de Nacimineto:");
        jpnDatosEmpleado.add(lblNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        lblEstadoCivil.setText("Estado Civil:");
        jpnDatosEmpleado.add(lblEstadoCivil, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        lblGrado.setText("Grado Academico:");
        jpnDatosEmpleado.add(lblGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        lblTelefono.setText("Telefono:");
        jpnDatosEmpleado.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        lblDireccion.setText("Direccion:");
        jpnDatosEmpleado.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        lblVerCodigoEmpleado.setText("...");
        jpnDatosEmpleado.add(lblVerCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 270, -1));

        lblVerNombre.setText("...");
        lblVerNombre.setToolTipText("");
        jpnDatosEmpleado.add(lblVerNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 270, -1));

        lblVerNacimiento.setText("...");
        jpnDatosEmpleado.add(lblVerNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 270, -1));

        lblVerEstadoCivil.setText("...");
        jpnDatosEmpleado.add(lblVerEstadoCivil, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 270, -1));

        lblVerGrado.setText("...");
        jpnDatosEmpleado.add(lblVerGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 270, -1));

        lblVerTelefono.setText("...");
        jpnDatosEmpleado.add(lblVerTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 270, -1));

        lblVerDireccion.setText("...");
        jpnDatosEmpleado.add(lblVerDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 270, -1));

        getContentPane().add(jpnDatosEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 470, 240));

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

        jpnDatosContrato.setBorder(javax.swing.BorderFactory.createTitledBorder("Contrato"));
        jpnDatosContrato.setOpaque(false);
        jpnDatosContrato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigoContrato.setText("Codigo:");
        jpnDatosContrato.add(lblCodigoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        lblVerCodigoContrato.setText("...");
        jpnDatosContrato.add(lblVerCodigoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 70, 20));

        lblCondicion.setText("Condicion:");
        jpnDatosContrato.add(lblCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, 20));

        lblVerCondicion.setText("...");
        jpnDatosContrato.add(lblVerCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 60, 20));

        getContentPane().add(jpnDatosContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 470, 70));

        btnAnularContrato.setText("Anular Contrato");
        btnAnularContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularContratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnularContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 110, -1));

        jpnBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jpnBuscar.setOpaque(false);
        jpnBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDni.setText("DNI:");
        jpnBuscar.add(lblDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

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
        jpnBuscar.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 100, -1));

        btnVerificar.setText("Buscar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        jpnBuscar.add(btnVerificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 70, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jpnBuscar.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 70, -1));

        getContentPane().add(jpnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 350, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
        String patron_de_entrada = "0123456789";
        if (txtDni.getText().length() == 8 || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))  
            evt.consume();
        
        if(txtDni.getText().length() > 0)
            this.btnLimpiar.setVisible(true);
        else
            this.btnLimpiar.setVisible(false);
    }//GEN-LAST:event_txtDniKeyTyped

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        // TODO add your handling code here:
       ObtenerDatosContratoMostrar();
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        EstadoDefault();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
        VGlobales.globalIdContrato = Integer.valueOf(lblVerCodigoContrato.getText());
        VGlobales.globalEstadoContrato = "Editar";
        DlgContrato DC = new DlgContrato(null, true);
        DC.setVisible(true);
    }//GEN-LAST:event_btnEditarContratoActionPerformed

    private void btnAnularContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularContratoActionPerformed
        // TODO add your handling code here:   
        if(btnAnularContrato.getText().equalsIgnoreCase("Anular Contrato")){
            int seleccion = JOptionPane.showConfirmDialog(this,"¿Deseas anular el Contrato? ","!Atencion!",JOptionPane.YES_NO_OPTION );
            if(seleccion == JOptionPane.YES_OPTION) {                
                try {                
                    ServGestionarContrato gestionarContrato = new ServGestionarContrato();                
                    int registros_afectados = gestionarContrato.AnularContrato(contratoVigente);

                    if(registros_afectados == 1){
                        JOptionPane.showMessageDialog(this,"Contrato Anulado");         
                        VerificarContrato(contratoVigente.getIdcontrato());
                        MostrarDatosContrato(contrato);
                        EstadoControles(true);
                        btnAnularContrato.setText("Terminar");                
                        btnEditarContrato.setEnabled(false);
                    }            
                } catch (Exception e) {
                    Mensaje.mostrarError(this, "Error al Anular");
                }    
            } 
        }else{
            dispose();
            //EstadoDefault();
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
                MostrarDatosEmpleado(empleado);    
                if(listaContrato != null){
                    VerificarContratoVigente(empleado.getIdempleado());
                    ActivarControles(false);
                }
            }    
        }
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }

    }//GEN-LAST:event_txtDniKeyPressed

    void setMnemonic(){  
        btnVerificar.setMnemonic(KeyEvent.VK_B);
        btnLimpiar.setMnemonic(KeyEvent.VK_L);
        btnCrearContrato.setMnemonic(KeyEvent.VK_C);
        btnEditarContrato.setMnemonic(KeyEvent.VK_E);
        btnAnularContrato.setMnemonic(KeyEvent.VK_A);     
    }
  
    void Estilo(){
        Object[ ] etiquetas = {lblDni, lblCodigoEmpleado, lblNombre, lblNacimiento, lblEstadoCivil, lblGrado, lblTelefono,
            lblDireccion, lblVerCodigoEmpleado, lblVerNombre, lblVerNacimiento, lblVerEstadoCivil, lblVerGrado,
            lblVerTelefono, lblVerDireccion, lblCodigoContrato, lblVerCodigoContrato, lblCondicion, lblVerCondicion};
        EstiloEtiqueta estilo = new EstiloEtiqueta(etiquetas);
    }
    
    void EventoTecla(){
        jpnDatosEmpleado.addKeyListener(new AtajosTecladoDlg(this));
        jpnDatosContrato.addKeyListener(new AtajosTecladoDlg(this));
        txtDni.addKeyListener(new AtajosTecladoDlg(this));
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
    private javax.swing.JButton btnCrearContrato;
    private javax.swing.JButton btnEditarContrato;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JPanel jpnBuscar;
    private javax.swing.JPanel jpnDatosContrato;
    private javax.swing.JPanel jpnDatosEmpleado;
    private javax.swing.JLabel lblCodigoContrato;
    private javax.swing.JLabel lblCodigoEmpleado;
    private javax.swing.JLabel lblCondicion;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblEstadoCivil;
    private javax.swing.JLabel lblGrado;
    private javax.swing.JLabel lblNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblVerCodigoContrato;
    private javax.swing.JLabel lblVerCodigoEmpleado;
    private javax.swing.JLabel lblVerCondicion;
    private javax.swing.JLabel lblVerDireccion;
    private javax.swing.JLabel lblVerEstadoCivil;
    private javax.swing.JLabel lblVerGrado;
    private javax.swing.JLabel lblVerNacimiento;
    private javax.swing.JLabel lblVerNombre;
    private javax.swing.JLabel lblVerTelefono;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
