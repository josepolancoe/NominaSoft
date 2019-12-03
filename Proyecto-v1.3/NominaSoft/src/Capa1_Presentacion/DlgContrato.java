/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarAfp;
import Capa2_Aplicacion.ServGestionarContrato;
import Capa2_Aplicacion.ServGestionarEmpleado;
import Capa3_Dominio.Afp;
import Capa3_Dominio.Contrato;
import Capa3_Dominio.Empleado;
import Capa5_Excepcion.Mensaje;
import Capa6_Globales.Objeto;
import Capa6_Globales.VGlobales;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author lucab
 */
public class DlgContrato extends javax.swing.JDialog {

    /**
     * Creates new form DlgContrato
     */
    private Contrato contrato;
    private Contrato ultimocontrato;
    private Empleado empleado;
    private Afp afp;
    private List<Afp> listaAfps;
    private List<Contrato> listaContratos;
    private DefaultComboBoxModel modeloAfp;
    private Objeto datosAfp;
    
    public DlgContrato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this .getContentPane().setBackground(new Color(255,255,255));
        setLocationRelativeTo(null);
        setResizable(false); 
        setTitle("Contrato");  
        EstadoInicio();
    }

    void EstadoInicio(){
        if(VGlobales.globalDNI.compareTo("")!=0){
        VerificarEmpleado(VGlobales.globalDNI);
        }
        LlenarCombo();
        txtCodigo.setEditable(false);
        txtCondicion.setEditable(false);
        jdcFechaInicioContrato.requestFocus();
        if(VGlobales.globalEstadoContrato.compareTo("Crear") ==0){
            LimpiarEntradas();
            btnRegistrar.setText("Guardar");        
        }else{
            VerificarContrato(VGlobales.globalIdContrato);
            MostrarDatos(contrato);
            btnRegistrar.setText("Actualizar"); 
            
        }
    }
    
    void LimpiarEntradas(){
        txtCodigo.setText("");     
        jdcFechaInicioContrato.setCalendar(null);
        jdcFechaFinContrato.setCalendar(null);
        txtCargo.setText("");
        cboAfp.setSelectedIndex(0);
        chkAsignacion.setSelected(false);
        txtHorasSemana.setText("");
        txtValorHora.setText("");
        txtCondicion.setText("");
    }      
    
    private void LlenarCombo(){  
        ConsultaAfp();
        cboAfp.removeAllItems();
        if(listaAfps != null){         
            modeloAfp = new DefaultComboBoxModel();
            cboAfp.setModel(modeloAfp);           
            modeloAfp.addElement(new Objeto("<Seleccionar>", 0));
            for(Afp objetoAfp : listaAfps){
                datosAfp = new Objeto(objetoAfp.getNombre(), objetoAfp.getIdafp());
                modeloAfp.addElement(datosAfp);
            }
        }else
                Mensaje.mostrarAdvertencia(this, "Lista vacia");   
    }
    
    public void seleccionarValorCombo(JComboBox combo, String valor){
        String item;
        for (int i = 0; i < combo.getModel().getSize(); i++)
        {
            item = combo.getModel().getElementAt(i).toString();
            if (item.equalsIgnoreCase(valor))
            {
                combo.setSelectedItem(item);
                //OJOOOO!!!!
                combo.setSelectedIndex(i);
                break;
            }
        }
    }  
    
    public void VerificarEmpleado(String parDni){           
        try{
            ServGestionarEmpleado gestionarEmpleado = new ServGestionarEmpleado();
            empleado = gestionarEmpleado.VerificarEmpleado(parDni);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro Empleado");            
        } 
        if (empleado == null){
            Mensaje.mostrarAfirmacion(this, "No exite empleado");
        }
    }
    
    public void VerificarAfp(int parIdAfp){             
        try{
            ServGestionarAfp gestionarAfp = new ServGestionarAfp();
            afp = gestionarAfp.VerificarAfp(parIdAfp);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro Afp");            
        } 
        if (afp == null){
            Mensaje.mostrarAfirmacion(this, "No exite Afp");
        }
    }
    
    public void ConsultaAfp(){ 
        try{
            ServGestionarAfp gestionarAfp = new ServGestionarAfp();
            listaAfps = gestionarAfp.BuscarAfps();               
        } catch (Exception e) {
            Mensaje.mostrarError(this, e.getMessage());   
        }
        
    }
    
    public void VerificarContrato(int parIdContrato){
        try{
            ServGestionarContrato gestionarContrato = new ServGestionarContrato();
            contrato = gestionarContrato.VerificarContrato(parIdContrato);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error de Registro");            
        } 
//        if (contrato == null){
//            Mensaje.mostrarAfirmacion(this, "No exite Contrato");
//        }
    }
    
    public void BuscarContratos(int parIdEmpleado){           
        try{
            ServGestionarContrato gestionarContrato = new ServGestionarContrato();
            listaContratos = gestionarContrato.BuscarContratos(parIdEmpleado);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, "Error dr Registro");            
        }
        if (listaContratos == null){
            Mensaje.mostrarAfirmacion(this, "No exite contratos");
        }
    }  
        
    public void VerificarFechaUltimoContrato(String parDni){
        java.util.Date fechaActual = new java.util.Date();
        long lonActual = fechaActual.getTime();      
        java.sql.Date FechaFin = new java.sql.Date(lonActual);           
        int codigomayor = 0;
        VerificarEmpleado(parDni);
        if(empleado != null){
            int idEmpleado = empleado.getIdempleado();
            BuscarContratos(idEmpleado);
            if(listaContratos == null || listaContratos.isEmpty()){
                ultimocontrato = new Contrato();
                ultimocontrato.setFechafincontrato(FechaFin);
            }else{
                for(Contrato objetoContrato : listaContratos){
                    int codigo = objetoContrato.getIdcontrato();
                    if(codigo > codigomayor)
                        codigomayor = codigo;
                }
                VerificarContrato(codigomayor);
                ultimocontrato = contrato;
            }
        }
    }    
    
    public void MostrarDatos(Contrato parContrato){
        if(parContrato != null){
        txtCodigo.setText(String.valueOf(parContrato.getIdcontrato()));
        jdcFechaInicioContrato.setDate(parContrato.getFechainiciocontrato());
        jdcFechaFinContrato.setDate(parContrato.getFechafincontrato());               
        txtCargo.setText(parContrato.getCargo()); 
        int idAfp = parContrato.getAfp().getIdafp();
        VerificarAfp(idAfp);
        String Afp = afp.getNombre(); 
        seleccionarValorCombo(cboAfp, Afp);
      
        if(parContrato.getAsignacionfamiliar() == true)
            chkAsignacion.setSelected(true);
        else
            chkAsignacion.setSelected(false);        
            txtHorasSemana.setText(String.valueOf(parContrato.getTotalhoras()));        
            txtValorHora.setText(String.valueOf(parContrato.getValorhora()));      
            txtCondicion.setText(parContrato.getCondicion());
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

        jLabel1 = new javax.swing.JLabel();
        jdcFechaInicioContrato = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jdcFechaFinContrato = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboAfp = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        chkAsignacion = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        txtHorasSemana = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtValorHora = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCondicion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Fecha Inicio:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, 20));

        jdcFechaInicioContrato.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaInicioContratoPropertyChange(evt);
            }
        });
        getContentPane().add(jdcFechaInicioContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 160, -1));

        jLabel2.setText("Fecha Fin:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jdcFechaFinContrato.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaFinContratoPropertyChange(evt);
            }
        });
        getContentPane().add(jdcFechaFinContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 160, -1));

        jLabel3.setText("Cargo:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));
        getContentPane().add(txtCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, -1));

        jLabel4.setText("Afp Asociada:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        cboAfp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAfpActionPerformed(evt);
            }
        });
        getContentPane().add(cboAfp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 140, -1));

        jLabel5.setText("Asignacion Familiar:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        chkAsignacion.setText("Si");
        getContentPane().add(chkAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        jLabel6.setText("Horas/Semana:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));
        getContentPane().add(txtHorasSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 60, -1));

        jLabel7.setText("Valor/Hora:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));
        getContentPane().add(txtValorHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 60, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 100, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 100, -1));

        jLabel8.setText("Codigo:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 20));
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 140, -1));

        jLabel9.setText("Condicion:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 20));
        getContentPane().add(txtCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 140, -1));

        jLabel10.setText("Horas.");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, 20));

        jLabel11.setText("Nuevos Soles.");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        String Cargo, Condicion, Dni;
        int HorasSemana;
        boolean Asignacion;
        double ValorHora;
        int idContrato = 0;

//        if(txtCodigo.getText().length() > 0){
//            idContrato = Integer.parseInt(txtCodigo.getText().trim());
//        }
              
        long dateInicio = 0;
        Date fechaInicio = jdcFechaInicioContrato.getDate();
        dateInicio = fechaInicio.getTime();
        java.sql.Date FechaInicio = new java.sql.Date(dateInicio); 

        long dateFin = 0;
        Date fechaFin = jdcFechaFinContrato.getDate(); 
        dateFin = fechaFin.getTime();
        java.sql.Date FechaFin = new java.sql.Date(dateFin);   
        Cargo = (txtCargo.getText());                                           
        Asignacion = chkAsignacion.isSelected(); 
        HorasSemana = Integer.valueOf(txtHorasSemana.getText()); 
        ValorHora = Double.valueOf(txtValorHora.getText()); 
        Condicion = txtCondicion.getText();
        
        if(cboAfp.getSelectedIndex() > 0){
            int indice = cboAfp.getSelectedIndex();
            Objeto ObjAfp = (Objeto)modeloAfp.getElementAt(indice);
            int idAfp =ObjAfp.getCodigo();            
            VerificarAfp(idAfp);  
        }

                            
////////CREAR CONTRATO        
        if( VGlobales.globalEstadoContrato.compareTo("Crear")==0){     
            if(txtCargo.getText().compareTo("")!=0 && txtHorasSemana.getText().compareTo("")!=0 && txtValorHora.getText().compareTo("")!=0
                 &&   jdcFechaInicioContrato.getDate() != null && jdcFechaFinContrato.getDate() != null){      
                        Contrato contratonuevo = new Contrato(empleado, afp, FechaInicio, FechaFin, Cargo, Asignacion, HorasSemana, ValorHora); 
////////////////////////////VERIFICA FECHA INICIO 
                     VerificarFechaUltimoContrato(VGlobales.globalDNI);        
                    if(contratonuevo.verificarFechaInicioContratoNuevo(ultimocontrato.getFechafincontrato())== true){                     
////////////////////////////////VERIFICA FECHA FIN                     
                        if(contratonuevo.verificarFechaFinContratoNuevo() == true){
////////////////////////////////////VERIFICA HORAS CONTRATADAS                                    
                            if(contratonuevo.verificarHorasContratadas()== true){
////////////////////////////////////////VERIFICAR SUELDO
                                if(contratonuevo.verificarValorHora()== true){
                                    try {   
                                        
                                        ServGestionarContrato gestionarContrato = new ServGestionarContrato();
                                        int registros_afectados = gestionarContrato.IngresarContrato(contratonuevo);              

                                        if(registros_afectados == 1){
                                            JOptionPane.showMessageDialog(null,"Contrato registrado");
                                            btnRegistrar.setText("Actualizar");
                                            btnCancelar.setText("Finalizar");  
//                                            VerificarContrato(Dni);
//                                            MostrarDatos(contrato);
                                            this.dispose();
                                        }
                                    } catch (Exception e) {
                                        Mensaje.mostrarError(this, "Error de Ingreso Registro");
                                    }                                            
                                }else
                                    JOptionPane.showMessageDialog(null,"Valor sueldo no valido");
                            }else
                                JOptionPane.showMessageDialog(null,"Horas Contratadas no validas");                                   
                        }else
                            JOptionPane.showMessageDialog(null,"Fecha fin no valida");       
                    }else
                        JOptionPane.showMessageDialog(null,"Fecha Inicio no valida");
            } else
                JOptionPane.showMessageDialog(null,"Faltan llenar datos");
////////EDITAR CONTRATO            
        }else if(VGlobales.globalEstadoContrato.compareTo("Editar")==0){
            if(txtCargo.getText().compareTo("")!=0 && txtHorasSemana.getText().compareTo("")!=0 && txtValorHora.getText().compareTo("")!=0     
                 &&   jdcFechaInicioContrato.getDate() != null && jdcFechaFinContrato.getDate() != null){   

                Contrato contratoactualizado = new Contrato(idContrato, empleado, afp, FechaInicio, FechaFin, Cargo, Asignacion, HorasSemana, ValorHora, Condicion); 
            ////////////////////////////VERIFICA FECHA INICIO  
                    if(contratoactualizado.verificarFechaInicioContratoNuevo(ultimocontrato.getFechafincontrato())== true){                     
////////////////////////////////VERIFICA FECHA FIN                     
                        if(contratoactualizado.verificarFechaFinContratoNuevo() == true){
////////////////////////////////////VERIFICA HORAS CONTRATADAS                                    
                            if(contratoactualizado.verificarHorasContratadas()== true){
////////////////////////////////////////VERIFICAR SUELDO
                                if(contratoactualizado.verificaEstadoContrato()== true){
                                    try {                                           
                                        ServGestionarContrato gestionarContrato = new ServGestionarContrato();
                                        int registros_afectados = gestionarContrato.ModificarContrato(contratoactualizado);              

                                        if(registros_afectados == 1){
                                            JOptionPane.showMessageDialog(null,"Contrato registrado");
                                            btnRegistrar.setText("Actualizar");
                                            btnCancelar.setText("Finalizar");  
//                                            VerificarContrato(Dni);
//                                            MostrarDatos(contrato);
                                            this.dispose();
                                        }
                                    } catch (Exception e) {
                                        Mensaje.mostrarError(this, "Error de Ingreso Registro");
                                    }                                            
                                }else
                                    JOptionPane.showMessageDialog(null,"Valor sueldo no valido");
                            }else
                                JOptionPane.showMessageDialog(null,"Horas Contratadas no validas");                                   
                        }else
                            JOptionPane.showMessageDialog(null,"Fecha fin no valida");       
                    }else
                        JOptionPane.showMessageDialog(null,"Fecha Inicio no valida");
            } else
                JOptionPane.showMessageDialog(null,"Faltan llenar datos"); 
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        VGlobales.globalIdContrato = 0;
        VGlobales.globalEstadoContrato = "";
        VGlobales.globalDNI = "";
        LimpiarEntradas();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cboAfpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAfpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAfpActionPerformed

    private void jdcFechaInicioContratoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcFechaInicioContratoPropertyChange
        // TODO add your handling code here:
        jdcFechaInicioContrato.getJCalendar().setMinSelectableDate(new Date());
        ((JTextFieldDateEditor)jdcFechaInicioContrato.getDateEditor()).setEditable(false);
        
        
    }//GEN-LAST:event_jdcFechaInicioContratoPropertyChange

    private void jdcFechaFinContratoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcFechaFinContratoPropertyChange
        // TODO add your handling code here:
        jdcFechaFinContrato.getJCalendar().setMinSelectableDate(new Date());
        ((JTextFieldDateEditor)jdcFechaFinContrato.getDateEditor()).setEditable(false);
    }//GEN-LAST:event_jdcFechaFinContratoPropertyChange

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
            java.util.logging.Logger.getLogger(DlgContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgContrato dialog = new DlgContrato(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cboAfp;
    private javax.swing.JCheckBox chkAsignacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JDateChooser jdcFechaFinContrato;
    private com.toedter.calendar.JDateChooser jdcFechaInicioContrato;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCondicion;
    private javax.swing.JTextField txtHorasSemana;
    private javax.swing.JTextField txtValorHora;
    // End of variables declaration//GEN-END:variables
}
