/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarEmpleado;
import Capa3_Dominio.Empleado;
import Capa5_Excepcion.Mensaje;
import Capa1_Presentacion.Utilidades.EstiloBoton;
import Capa1_Presentacion.Utilidades.EstiloEtiqueta;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author lucab
 */
public class DlgVerificarEmpleado extends JDialog {

    private transient Empleado empleado = null;
    private String  obtenerDni = "";
    private boolean estado; 
    transient EstiloEtiqueta estilo; 
    transient EstiloBoton estiloBoton;
    
    /**
     * Creates new form DlgVerificarEmpleado
     */
    public DlgVerificarEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.getContentPane().setBackground(new Color(153,153,153));  
        initComponents();
        setResizable(false);
        setTitle("Verificar DNI");
        setSize(240, 126);          
        setLocationRelativeTo(null);    
        estadoDefault();
    }

    private void verificarEmpleado(String parDni){             
        try{
            ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();
            empleado = gestionarEmpleado.verificarEmpleado(parDni);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        }
    } 

    private void verificarDni(){
        if(txtVerificaDni.getText().matches("([0-9]|\\s)+") && txtVerificaDni.getText().length() == 8){                         
            obtenerDni = txtVerificaDni.getText();  
            verificarEmpleado(obtenerDni);
            if (empleado == null){
                Mensaje.mostrarAfirmacion(this, "No exite empleado");
                int seleccion = JOptionPane.showConfirmDialog(this,"¿Deseas agregar empleado? ","!Atencion!",JOptionPane.YES_NO_OPTION );
                if(seleccion == JOptionPane.YES_OPTION) { 
                    DlgEmpleado DE = new DlgEmpleado(null, true);
                    DE.obtenerDni(obtenerDni);
                    DE.setVisible(true); 
                }else
                    dispose();
            }else{
                if(estado){
                    dispose();
                    DlgEmpleado DE = new DlgEmpleado(null, true);
                    DE.obtenerEmpleado(empleado);
                    DE.setVisible(true);                     
                }else{
                    dispose();
                    DlgGestionarContrato DGC = new DlgGestionarContrato(null, true);
                    DGC.obtenerEmpleado(empleado);
                    DGC.setVisible(true);
                }
            }
            dispose(); 
        }else{
            Mensaje.mostrarAdvertencia(this, Mensaje.ERROR_DNI); 
            estadoDefault();
        }
    } 
    
    public void obtenerEstado(boolean parEstado){
        estado = parEstado;
    }

    public String retornaDni(){
        return obtenerDni;
    }
    
    public Empleado retornaEmpleado(){
        Empleado obtenerEmpleado = empleado;
        return obtenerEmpleado;
    }
    
    private void estadoDefault(){
        estilo();
        txtVerificaDni.setText("");
        txtVerificaDni.requestFocus();
    }
    
    public void estilo() {
        Object[ ] etiquetas = {lblDniAqui};
        estilo = new EstiloEtiqueta(etiquetas);
        estiloBoton = new EstiloBoton(new Object[]{btnAceptar, btnCancelar});
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDniAqui = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtVerificaDni = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDniAqui.setText("Dni aqui:");
        getContentPane().add(lblDniAqui, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 50, 30));

        btnAceptar.setText("Verificar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 90, -1));

        txtVerificaDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVerificaDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVerificaDniKeyTyped(evt);
            }
        });
        getContentPane().add(txtVerificaDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 116, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        verificarDni();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtVerificaDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVerificaDniKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){    
             verificarDni();  
        }
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
      
    }//GEN-LAST:event_txtVerificaDniKeyPressed

    private void txtVerificaDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVerificaDniKeyTyped
        // TODO add your handling code here:
        String patron_de_entrada = "0123456789";
        if (txtVerificaDni.getText().length() == 8 || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))){
        evt.consume();
        }
    }//GEN-LAST:event_txtVerificaDniKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblDniAqui;
    private javax.swing.JTextField txtVerificaDni;
    // End of variables declaration//GEN-END:variables
}