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
import Capa6_Globales.EstiloEtiqueta;
import Capa6_Globales.Objeto;
import Capa6_Globales.VGlobales;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
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
    private Contrato contrato, contratonuevo, contratoactualizado;
    private Empleado empleado;
    private Afp afp;
    private List<Afp> listaAfps;
    private List<Contrato> listaContratos;
    private DefaultComboBoxModel modeloAfp;
    private Objeto datosAfp; 
    private boolean estado;

    //        System.out.println("");
    public DlgContrato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(new Color(153,153,153));
        setLocationRelativeTo(null);
        setResizable(false); 
        setTitle("Contrato");  
        EstadoInicio();       
    }
    
    void EstadoInicio(){
        Estilo();
        if(VGlobales.globalDNI.compareTo("")!=0){
        VerificarEmpleado(VGlobales.globalDNI);
        }      
        LlenarCombo();
        txtCodigo.setEditable(false);
        txtCondicion.setEditable(false);
        jdcFechaInicioContrato.requestFocus();
        if(VGlobales.globalEstadoContrato.compareTo("Crear") ==0){
            LimpiarEntradas();
            estado = true;
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
        
    public java.sql.Date DeterminarFechaContratoPrevio(String parDni, Contrato parContrato){  
        java.sql.Date fechaRetorno = null;
        int codigomayor = 0;
        java.sql.Date fechaDefecto = java.sql.Date.valueOf("2000-01-01");    
        Date fechaInicioContrato = parContrato.getFechainiciocontrato();
        
        VerificarEmpleado(parDni);
        if(empleado != null){ 
            BuscarContratos(empleado.getIdempleado());
            if(listaContratos == null || listaContratos.isEmpty()){     
                fechaRetorno = fechaDefecto;
            }else{
                for(Contrato objetoContrato : listaContratos){
                    int codigo = objetoContrato.getIdcontrato();
                    if(codigo > codigomayor)
                        codigomayor = codigo;
                }
                VerificarContrato(codigomayor);
                Date fechaFinUtimoContrato = contrato.getFechafincontrato(); 
                //if(fechaInicioContrato.getTime() == fechaFinUtimoContrato.getTime()){
                if(parContrato.getIdcontrato() == contrato.getIdcontrato()){    
                    fechaRetorno = fechaDefecto;
                }else
                    fechaRetorno = contrato.getFechafincontrato();
            }
        }
    return fechaRetorno;
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
    
        public String VerificarRangoSueldo(String parGrado){
        String respuesta = "";
	switch (parGrado) {
		case "Primaria":                       
                            respuesta = "de 5 a 10"; 
                            break;             
		case "Secundaria":
                            respuesta = "de 5 a 10";  
                            break;
		case "Bachiller":
                            respuesta = "de 11 a 20";
                            break;
		case "Profesional":
                            respuesta = "de 21 a 30";
                            break;
		case "Magister":
                            respuesta = "de 31 a 40";
                            break;
                case "Doctor":
                            respuesta = "de 41 a 60";
                            break;
                default:
                        respuesta = null;
			break;
	}
        return respuesta;
  
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFechaInicio = new javax.swing.JLabel();
        jdcFechaInicioContrato = new com.toedter.calendar.JDateChooser();
        lblFechaFin = new javax.swing.JLabel();
        jdcFechaFinContrato = new com.toedter.calendar.JDateChooser();
        lblCargo = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        lblAfp = new javax.swing.JLabel();
        cboAfp = new javax.swing.JComboBox<>();
        lblAsignacion = new javax.swing.JLabel();
        chkAsignacion = new javax.swing.JCheckBox();
        lblHorasSemana = new javax.swing.JLabel();
        txtHorasSemana = new javax.swing.JTextField();
        lblValorHora = new javax.swing.JLabel();
        txtValorHora = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblCondicion = new javax.swing.JLabel();
        txtCondicion = new javax.swing.JTextField();
        lblHoras = new javax.swing.JLabel();
        lblSoles = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFechaInicio.setText("Fecha Inicio:");
        getContentPane().add(lblFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, 20));

        jdcFechaInicioContrato.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaInicioContratoPropertyChange(evt);
            }
        });
        jdcFechaInicioContrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdcFechaInicioContratoKeyPressed(evt);
            }
        });
        getContentPane().add(jdcFechaInicioContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 160, -1));

        lblFechaFin.setText("Fecha Fin:");
        getContentPane().add(lblFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jdcFechaFinContrato.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaFinContratoPropertyChange(evt);
            }
        });
        getContentPane().add(jdcFechaFinContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 160, -1));

        lblCargo.setText("Cargo:");
        getContentPane().add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));
        getContentPane().add(txtCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 160, -1));

        lblAfp.setText("Afp Asociada:");
        getContentPane().add(lblAfp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        cboAfp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAfpActionPerformed(evt);
            }
        });
        getContentPane().add(cboAfp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 140, -1));

        lblAsignacion.setText("Asignacion Familiar:");
        getContentPane().add(lblAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        chkAsignacion.setText("Si");
        getContentPane().add(chkAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        lblHorasSemana.setText("Horas/Semana:");
        getContentPane().add(lblHorasSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));
        getContentPane().add(txtHorasSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 60, -1));

        lblValorHora.setText("Valor/Hora:");
        getContentPane().add(lblValorHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));
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

        lblCodigo.setText("Codigo:");
        getContentPane().add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 20));
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 140, -1));

        lblCondicion.setText("Condicion:");
        getContentPane().add(lblCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 20));
        getContentPane().add(txtCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 140, -1));

        lblHoras.setText("Horas.");
        getContentPane().add(lblHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, 20));

        lblSoles.setText("Nuevos Soles.");
        getContentPane().add(lblSoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        String Cargo, Condicion, gradoEmpleado = ""; 
        int HorasSemana;
        boolean Asignacion;
        double ValorHora;
        int idContrato = 0;
        
        if(txtCodigo.getText().length() > 0){
            idContrato = Integer.parseInt(txtCodigo.getText().trim());
        }
              
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
        
        gradoEmpleado = VerificarRangoSueldo(empleado.getGradoacademico());
                     
////////CREAR CONTRATO        
        if( VGlobales.globalEstadoContrato.compareTo("Crear")==0){   
                           
        //if(estado){               
            if(txtCargo.getText().compareTo("")!=0 && txtHorasSemana.getText().compareTo("")!=0 && txtValorHora.getText().compareTo("")!=0
                 &&   jdcFechaInicioContrato.getDate() != null && jdcFechaFinContrato.getDate() != null){      
                    contratonuevo = new Contrato(empleado, afp, FechaInicio, FechaFin, Cargo, Asignacion, HorasSemana, ValorHora); 
////////////////////////////VERIFICA FECHA INICIO         
                    if(contratonuevo.verificarFechaInicioContratoNuevo(DeterminarFechaContratoPrevio(VGlobales.globalDNI, contratonuevo))== true){                     
////////////////////////////////VERIFICA FECHA FIN                     
                        if(contratonuevo.verificarFechaFinContratoNuevo() == true){
////////////////////////////////////VERIFICA HORAS CONTRATADAS                                    
                            if(contratonuevo.verificarHorasContratadas()== true){
////////////////////////////////////////VERIFICAR SUELDO                                
                                if(contratonuevo.verificarValorHora() == true){
                                    try {                                           
                                        ServGestionarContrato gestionarContrato = new ServGestionarContrato();
                                        int registros_afectados = gestionarContrato.IngresarContrato(contratonuevo);              

                                        if(registros_afectados == 1){
                                            JOptionPane.showMessageDialog(null,"Contrato registrado");
                                            btnRegistrar.setText("Actualizar");
                                            btnCancelar.setText("Finalizar");  
                                            //VerificarContrato(VGlobales.globalIdContrato);
                                            //MostrarDatos(contrato);
                                            MostrarDatos(contratonuevo);
                                            //this.dispose();
                                        }
                                    } catch (Exception e) {
                                        Mensaje.mostrarError(this, "Error de Ingreso Registro");
                                    }                                            
                                }else;
                                    
                                    JOptionPane.showMessageDialog(null, "Valor sueldo no valido, ingrese valores entre: " + gradoEmpleado);
                            }else
                                JOptionPane.showMessageDialog(null,"Horas no validas, el tiempo es de 8 a 40 horas");                                   
                        }else
                            JOptionPane.showMessageDialog(null,"Fecha no valida, el periodo es de 3 a 12 meses");       
                    }else
                        JOptionPane.showMessageDialog(null,"Fecha Inicio no valida");
            } else
                JOptionPane.showMessageDialog(null,"Faltan llenar datos");
////////EDITAR CONTRATO            
        }else if(VGlobales.globalEstadoContrato.compareTo("Editar")==0){
        //}else{
            if(txtCargo.getText().compareTo("")!=0 && txtHorasSemana.getText().compareTo("")!=0 && txtValorHora.getText().compareTo("")!=0     
                 &&   jdcFechaInicioContrato.getDate() != null && jdcFechaFinContrato.getDate() != null){   

                contratoactualizado = new Contrato(idContrato, empleado, afp, FechaInicio, FechaFin, Cargo, Asignacion, HorasSemana, ValorHora, Condicion); 
            ////////////////////////////VERIFICA FECHA INICIO
           
                    if(contratoactualizado.verificarFechaInicioContratoNuevo(DeterminarFechaContratoPrevio(VGlobales.globalDNI, contratoactualizado))== true){                     
////////////////////////////////VERIFICA FECHA FIN                     
                        if(contratoactualizado.verificarFechaFinContratoNuevo() == true){
////////////////////////////////////VERIFICA HORAS CONTRATADAS                                    
                            if(contratoactualizado.verificarHorasContratadas()== true){
////////////////////////////////////////VERIFICAR SUELDO
                                if(contratoactualizado.verificarValorHora() == true){
                                    try {                                           
                                        ServGestionarContrato gestionarContrato = new ServGestionarContrato();
                                        int registros_afectados = gestionarContrato.ModificarContrato(contratoactualizado);              

                                        if(registros_afectados == 1){
                                            JOptionPane.showMessageDialog(null,"Contrato actualizado");
                                            btnRegistrar.setText("Actualizar");
                                            btnCancelar.setText("Finalizar");  
//                                            VerificarContrato(VGlobales.globalIdContrato);
//                                            MostrarDatos(contrato);
                                              MostrarDatos(contratoactualizado);  
                                            //this.dispose();
                                        }
                                    } catch (Exception e) {
                                        Mensaje.mostrarError(this, "Error de Ingreso Registro");
                                    }                                            
                                }else
                                    JOptionPane.showMessageDialog(null,"Valor sueldo no valido, ingrese valores entre: " + gradoEmpleado);
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

    private void jdcFechaInicioContratoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcFechaInicioContratoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jdcFechaInicioContratoKeyPressed
   
    void setMnemonic(){  
        btnCancelar.setMnemonic(KeyEvent.VK_C);    
        if(estado == true){
            btnRegistrar.setMnemonic(KeyEvent.VK_G);
        }else{
            btnRegistrar.setMnemonic(KeyEvent.VK_A);   
        }
    }
  
    void Estilo(){
        Object[ ] etiquetas = {lblCodigo, lblFechaInicio, lblFechaFin, lblCargo, lblAfp, lblAsignacion,
            lblHorasSemana, lblValorHora, lblCondicion, lblHoras, lblSoles};
        EstiloEtiqueta estilo = new EstiloEtiqueta(etiquetas);
        chkAsignacion.setFont(new Font("Arial", 1, 12));
        chkAsignacion.setForeground(new Color(255,255,255));
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
    private com.toedter.calendar.JDateChooser jdcFechaFinContrato;
    private com.toedter.calendar.JDateChooser jdcFechaInicioContrato;
    private javax.swing.JLabel lblAfp;
    private javax.swing.JLabel lblAsignacion;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCondicion;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblHoras;
    private javax.swing.JLabel lblHorasSemana;
    private javax.swing.JLabel lblSoles;
    private javax.swing.JLabel lblValorHora;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCondicion;
    private javax.swing.JTextField txtHorasSemana;
    private javax.swing.JTextField txtValorHora;
    // End of variables declaration//GEN-END:variables
}
