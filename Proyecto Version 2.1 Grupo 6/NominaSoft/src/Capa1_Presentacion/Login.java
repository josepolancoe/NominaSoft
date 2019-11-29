/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa6_Globales.TerminarAplicacion;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Junior
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    TerminarAplicacion atjCerrar;
  
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/Capa7_Util/Images/icono.png")).getImage());
        setTitle("Iniciar Sesión");    
        EstadoDefault();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    void EstadoDefault(){
        LimpiarEntradas();
        EventoTecla();
    }
    
    
    void EventoTecla(){
        atjCerrar = new TerminarAplicacion(this);
        AtajosTeclado();
    }
    
    void LimpiarEntradas(){
        txtUsuario.setText("");
        txtContraseña.setText("");
        txtUsuario.requestFocus();
    }

    private void VerificarIngreso(String usuario, String contraseña){
        String Usuario ="admin";
        String Contraseña ="1234";
     
        if (Usuario.equals(usuario) && Contraseña.equals(contraseña)){
            dispose();
            MenuPrincipal mp = new MenuPrincipal();
            mp.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
            LimpiarEntradas();
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
        txtContraseña = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnLogin.setBackground(new java.awt.Color(153, 153, 153));
        jpnLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpnLogin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 97, -1, 67));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Capa7_Util/Images/icono.png"))); // NOI18N
        jpnLogin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario");
        jpnLogin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña");
        jpnLogin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        txtUsuario.setNextFocusableComponent(txtContraseña);
        jpnLogin.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 130, -1));

        txtContraseña.setNextFocusableComponent(btnIngresar);
        jpnLogin.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 130, -1));

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
        jpnLogin.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

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
        jpnLogin.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 73, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel5.setText("Saltar Login: Ctrl + S + D");
        jpnLogin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        String Usuario = txtUsuario.getText();
        String Contraseña = txtContraseña.getText();
        VerificarIngreso(Usuario, Contraseña);
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        atjCerrar.close();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            VerificarIngreso(txtUsuario.getText(), txtContraseña.getText());
        }

    }//GEN-LAST:event_btnIngresarKeyPressed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.dispose();
        }
        
    }//GEN-LAST:event_btnSalirKeyPressed

    private void AtajosTeclado(){
        Set<Integer> presionadaInt = new HashSet<Integer>(); 
	KeyListener eventoTeclado = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }
            @Override
            public void keyPressed(KeyEvent ke) {
                presionadaInt.add(ke.getKeyCode());
                if(presionadaInt.contains(17) && presionadaInt.contains(68
                ) && presionadaInt.contains(83)){
                    VerificarIngreso("admin", "1234");
                }else if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                    VerificarIngreso(txtUsuario.getText(), txtContraseña.getText());    
                }           
            }
            @Override
            public void keyReleased(KeyEvent ke) {
                presionadaInt.remove(ke.getKeyCode());
            }
        };
        txtUsuario.addKeyListener(eventoTeclado);
        txtContraseña.addKeyListener(eventoTeclado);
                
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jpnLogin;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}