/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarAfp;
import Capa3_Dominio.Afp;
import Capa5_Excepcion.Mensaje;
import Capa6_Globales.AtajosTecladoDlg;
import Capa6_Globales.EstiloEtiqueta;
import Capa6_Globales.Objeto;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author Junior
 */
public class DlgAfp extends javax.swing.JDialog {

    /**
     * Creates new form DlgAfp
     */
    private Afp afp;
    private List<Afp> listaAfp;
    private Objeto datosAfp;
    DefaultListModel modeloLista;
    private boolean estado;
    
    public DlgAfp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(new Color(153,153,153));
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Gestionar AFP");
        EstadoDefault();   
    }

    void EstadoDefault(){
        Estilo();
        LimpiarEntradas();
        ActivarControles(false);
        ActivarEntradas(false);
        txtCodigo.setEditable(false);
        txtNombre.requestFocus();    
        //jpnPanel.grabFocus();
        btnRegistrar.setText("Registrar");
        LlenarLista();
        ListModel model = lstAfp.getModel();
        int ultimo = model.getSize();
        lstAfp.setSelectedIndex(ultimo -1);
        EventoTecla();
    }
  
    void Estado(boolean parEstado){
        txtNombre.requestFocus();    
        if(parEstado == true){
        //Estado Registrar 
            estado = parEstado;
            LimpiarEntradas();            
            LlenarLista();   
            EstadoControles(true);
            ActivarEntradas(true);
            btnRegistrar.setText("Registrar");           
            setMnemonic();
        }else{
        //Estado Actualizar 
            estado = parEstado;
            ActivarEntradas(true);                
            ActivarControles(true);
            btnRegistrar.setText("Actualizar");
            EstadoControles(true);    
            setMnemonic();
        }    
    }
    
    void LimpiarEntradas(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPorcentaje.setText("");   
        lstAfp.removeAll();
    }
    
    void ActivarEntradas(boolean sw)
    {
        txtCodigo.setEditable(false);
        txtNombre.setEditable(sw);
        txtPorcentaje.setEditable(sw);
        lstAfp.setEnabled(!sw);
    }
    
    void ActivarControles(boolean sw)
    {
        btnNuevo.setEnabled(!sw);
        btnRegistrar.setEnabled(sw);    
        ListModel model = lstAfp.getModel();
        if(model.getSize() > 0){
            btnModificar.setEnabled(!sw);
            btnEliminar.setEnabled(!sw);
        }
        btnTerminar.setEnabled(sw);
    }    
          
    void EstadoControles(boolean sw)
    {    
        btnRegistrar.setEnabled(sw);
        btnModificar.setEnabled(!sw);
        btnEliminar.setEnabled(!sw);
        btnTerminar.setEnabled(true);
    }
    
    public void VerificarNombreAfp(String parNombre){      
        
        try{
            ServGestionarAfp gestionarAfp = new ServGestionarAfp();
            afp = gestionarAfp.VerificarNombreAfp(parNombre);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        } 
        if (afp == null){
            Mensaje.mostrarAfirmacion(this, "No exite registro");
        }
    }             
            
    public void ConsultarAfps(){  
        
        try{
            ServGestionarAfp gestionarAfp = new ServGestionarAfp();
            listaAfp = gestionarAfp.BuscarAfps();  
        } catch (Exception e) {
            Mensaje.mostrarError(this, "Error consulta Afp");   
        }
        if (listaAfp == null){
            Mensaje.mostrarAfirmacion(this, "Sin Afps Registradas");
        }
    } 

    public void LlenarLista(){
        ConsultarAfps();
        lstAfp.removeAll();
        if(listaAfp != null){
                modeloLista = new DefaultListModel();
                lstAfp.setModel(modeloLista);
                for(Afp objetoAfp : listaAfp){
                    datosAfp = new Objeto(objetoAfp.getNombre(), objetoAfp.getIdafp(), objetoAfp.getPorcentaje());
                    modeloLista.addElement(datosAfp); 
                }    
            }else
                Mensaje.mostrarAdvertencia(this, "Lista vacia");   
    }   
   
    public void MostrarDatos(Afp parAfp){
        if(parAfp != null){
        txtCodigo.setText(String.valueOf(parAfp.getIdafp()));
        txtNombre.setText(parAfp.getNombre());
        txtPorcentaje.setText(String.valueOf(parAfp.getPorcentaje()));        
        btnEliminar.setEnabled(true);
        btnTerminar.setEnabled(true);
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

        jpnDatosAfp = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblPorcentaje = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        lblAfps = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstAfp = new javax.swing.JList<>();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnDatosAfp.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jpnDatosAfp.setName(""); // NOI18N
        jpnDatosAfp.setOpaque(false);
        jpnDatosAfp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigo.setText("Codigo:");
        jpnDatosAfp.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        lblNombre.setText("Nombre:");
        jpnDatosAfp.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        lblPorcentaje.setText("Porcentaje:");
        jpnDatosAfp.add(lblPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jpnDatosAfp.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 80, -1));
        jpnDatosAfp.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 180, -1));
        jpnDatosAfp.add(txtPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 80, -1));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jpnDatosAfp.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 80, -1));

        getContentPane().add(jpnDatosAfp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 140));
        jpnDatosAfp.getAccessibleContext().setAccessibleName("AFP");

        lblAfps.setText("Registro AFP:");
        getContentPane().add(lblAfps, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 80, 20));

        lstAfp.setEnabled(false);
        lstAfp.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstAfpValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstAfp);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 210, 110));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 80, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 80, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 80, -1));

        btnTerminar.setText("Terminar");
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnTerminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
            String patron_de_entrada = "0123456789.";
        if (!patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))
        evt.consume();
       
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        Estado(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void lstAfpValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstAfpValueChanged
        // TODO add your handling code here:
        int indice = lstAfp.getSelectedIndex();
        
        if(indice > -1){
            Objeto ObjAfp = (Objeto)modeloLista.getElementAt(indice);              
            txtCodigo.setText(String.valueOf(ObjAfp.getCodigo()));
            txtNombre.setText(ObjAfp.getDenominacion());
            txtPorcentaje.setText(String.valueOf(ObjAfp.getNumero()));                  
        }
    }//GEN-LAST:event_lstAfpValueChanged

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        int idAfp = 0;
        if(txtCodigo.getText().length() > 0){
            idAfp = Integer.parseInt(txtCodigo.getText().trim());
        }
        String Nombre = txtNombre.getText();
        double Porcentaje = (Double.parseDouble(txtPorcentaje.getText()));
        
        if(btnRegistrar.getText().equalsIgnoreCase("Registrar")) {
            if(txtNombre.getText().compareTo("")!=0 && txtPorcentaje.getText().compareTo("")!=0)
            {
                try {
                    afp = new Afp(Nombre, Porcentaje);                    
                    ServGestionarAfp gestionarAfp = new ServGestionarAfp();
                    int registros_afectados = gestionarAfp.IngresarAfp(afp);       

                    if(registros_afectados == 1){
                        JOptionPane.showMessageDialog(null,"Afp registrada");
                        EstadoDefault();
                    }
                 }catch (Exception e) {
                    Mensaje.mostrarError(this, "Error de Ingreso");
                }
            } else
            JOptionPane.showMessageDialog(null,"Complete los campos");
        } else {
            if(txtNombre.getText().compareTo("")!=0 && txtPorcentaje.getText().compareTo("")!=0 )
            {        
                try {
                    afp = new Afp(idAfp, Nombre, Porcentaje);
                    ServGestionarAfp gestionarAfp = new ServGestionarAfp();
                    int registros_afectados = gestionarAfp.ModificarAfp(afp);
                    if(registros_afectados == 1){
                        JOptionPane.showMessageDialog(null,"Afp Actualizada");
                        EstadoDefault(); 
                    }
                } catch (Exception e) {
                    Mensaje.mostrarError(this, "Error de Actualizacion");
                }

            } else
            JOptionPane.showMessageDialog(null,"Complete los campos");
        }
        //Estado(false);          
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if(!lstAfp.isSelectionEmpty()){
            Estado(false);
       }else
        JOptionPane.showMessageDialog(null,"Seleccione Afp de la Lista");
        

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here: 
        int idAfp = 0;
        if(txtCodigo.getText().length() > 0){
            idAfp = Integer.parseInt(txtCodigo.getText().trim());
        }
        String Nombre = txtNombre.getText();
        Double Porcentaje = Double.parseDouble(txtPorcentaje.getText());
        
        int seleccion = JOptionPane.showConfirmDialog(this,"Deseas Eliminar Afp","Eliminar Registro",JOptionPane.YES_NO_OPTION );
        if( seleccion == JOptionPane.YES_OPTION ) {           
            try {
                afp = new Afp(idAfp, Nombre, Porcentaje);
                ServGestionarAfp gestionarAfp = new ServGestionarAfp();
                int registros_afectados = gestionarAfp.EliminarAfp(afp);
                
                if(registros_afectados == 1){
                    JOptionPane.showMessageDialog(this,"Afp Eliminada");
                    EstadoDefault();
                }   
            } catch (Exception e) {
                Mensaje.mostrarError(this, "Error al Eliminar");
            }
        }
     
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        // TODO add your handling code here:
        EstadoDefault();
        //Estado(true);
        btnRegistrar.setText("Registrar");
    }//GEN-LAST:event_btnTerminarActionPerformed

    void setMnemonic(){  
        btnNuevo.setMnemonic(KeyEvent.VK_N);
        btnModificar.setMnemonic(KeyEvent.VK_M);
        btnEliminar.setMnemonic(KeyEvent.VK_E);
        btnTerminar.setMnemonic(KeyEvent.VK_T);    
        if(estado == true){
            btnRegistrar.setMnemonic(KeyEvent.VK_R);
        }else{
            btnRegistrar.setMnemonic(KeyEvent.VK_A);   
        }
    }
  
    void Estilo(){
        Object[ ] etiquetas = {lblCodigo, lblNombre, lblPorcentaje, lblAfps};
        EstiloEtiqueta estilo = new EstiloEtiqueta(etiquetas);
    }
    
    void EventoTecla(){
        jpnDatosAfp.addKeyListener(new AtajosTecladoDlg(this));
        txtCodigo.addKeyListener(new AtajosTecladoDlg(this));
        txtNombre.addKeyListener(new AtajosTecladoDlg(this));
        txtPorcentaje.addKeyListener(new AtajosTecladoDlg(this));
        lstAfp.addKeyListener(new AtajosTecladoDlg(this));
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
            java.util.logging.Logger.getLogger(DlgAfp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgAfp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgAfp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgAfp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgAfp dialog = new DlgAfp(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnDatosAfp;
    private javax.swing.JLabel lblAfps;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JList<String> lstAfp;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}
