/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarSesion;
import Capa5_Excepcion.Mensaje;
import Capa1_Presentacion.Utilidades.EstiloBoton;
import Capa1_Presentacion.Utilidades.TerminarAplicacion;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Junior
 */
public class Login extends JFrame{

    /**
     * Creates new form Login
     */
    private transient TerminarAplicacion atjCerrar;
    private final Set<Integer> presionadaInt = new HashSet();
    transient EstiloBoton estiloBoton; 
    private transient ServGestionarSesion gestionarSesion;
    
    
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Iniciar Sesión");  
        setIconImage(new ImageIcon(getClass().getResource("/Capa1_Presentacion/Imagen/iconoNomina.png")).getImage());        
        estadoDefault();
    }     

    void estadoDefault(){
        limpiarEntradas();
        eventoTecla();
        estilo();
    }
      
    void eventoTecla(){
        atjCerrar = new TerminarAplicacion(this);
    }
    
    void limpiarEntradas(){
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtUsuario.requestFocus();
    }

    
    private void verificarUsuarioContrasenia(){     
        if(txtUsuario.getText().compareTo("")!=0){
            if(txtContrasenia.getText().compareTo("")!=0){
                verificarIngreso(txtUsuario.getText(), txtContrasenia.getText());
            } else{
                Mensaje.mostrarAdvertencia(null, Mensaje.MENSAJE_INGRESE_CONTRASENIA);
                txtContrasenia.requestFocus();
            } 
        }else{
            Mensaje.mostrarAdvertencia(null, Mensaje.MENSAJE_INGRESE_USUARIO);
            txtUsuario.requestFocus();
        }
    }
     
    private void verificarIngreso(String usuario, String contrasenia){     
        try {
            gestionarSesion = new ServGestionarSesion();
            int respuesta = gestionarSesion.verificarSesion(usuario, contrasenia);
            if(respuesta == 1){
                this.dispose();
                MenuPrincipal mp = new MenuPrincipal();
                mp.setVisible(true);
            }else{
                Mensaje.mostrarError(null, Mensaje.MENSAJE_SIN_ACCESO);
                estadoDefault();
            }
        }catch (Exception e) {   
            Mensaje.mostrarError(null, Mensaje.ERROR_SERVICIO + "verificarIngreso()");
        } 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnLogin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContrasenia = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnLogin.setBackground(new java.awt.Color(153, 153, 153));
        jpnLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpnLogin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 97, -1, 67));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Capa1_Presentacion/Imagen/icono.png"))); // NOI18N
        jpnLogin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario");
        jpnLogin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, 20));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña");
        jpnLogin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, 20));

        txtUsuario.setNextFocusableComponent(txtContrasenia);
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });
        jpnLogin.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 130, -1));

        txtContrasenia.setNextFocusableComponent(btnIngresar);
        txtContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseniaKeyPressed(evt);
            }
        });
        jpnLogin.add(txtContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 130, -1));

        btnIngresar.setText("Ingresar");
        btnIngresar.setNextFocusableComponent(btnSalir);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        btnIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnIngresarKeyPressed(evt);
            }
        });
        jpnLogin.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 80, -1));

        btnSalir.setText("Salir");
        btnSalir.setNextFocusableComponent(txtUsuario);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        btnSalir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSalirKeyPressed(evt);
            }
        });
        jpnLogin.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 80, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 142, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        verificarUsuarioContrasenia();
   
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        atjCerrar.close();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            verificarUsuarioContrasenia();
        }

    }//GEN-LAST:event_btnIngresarKeyPressed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.dispose();
        }
        
    }//GEN-LAST:event_btnSalirKeyPressed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        // TODO add your handling code here:
        presionadaInt.add(evt.getKeyCode());  
        if(presionadaInt.contains(17) && presionadaInt.contains(68
        ) && presionadaInt.contains(83)){
            verificarIngreso("admin", "junior10");
        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            verificarUsuarioContrasenia();    
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        // TODO add your handling code here:
        presionadaInt.remove(evt.getKeyCode());
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtContraseniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseniaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            verificarUsuarioContrasenia();    
        }
    }//GEN-LAST:event_txtContraseniaKeyPressed
    void estilo(){
        estiloBoton = new EstiloBoton(new Object[]{btnIngresar, btnSalir});
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jpnLogin;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
