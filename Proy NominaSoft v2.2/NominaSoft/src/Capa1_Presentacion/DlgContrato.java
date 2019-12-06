/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarAfp;
import Capa2_Aplicacion.ServGestionarContrato;
import Capa3_Dominio.Afp;
import Capa3_Dominio.Contrato;
import Capa3_Dominio.Empleado;
import Capa1_Presentacion.Utilidades.EstructuraAbs;
import Capa5_Excepcion.ExcepcionSinSoporteOperacion;
import Capa5_Excepcion.Mensaje;
import Capa1_Presentacion.Utilidades.AtajosTecladoDlg;
import Capa1_Presentacion.Utilidades.EstiloEtiqueta;
import Capa6_Globales.Objeto;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author lucab
 */
public class DlgContrato extends EstructuraAbs {

    /**
     * Creates new form DlgContrato
     */
    private transient Contrato contrato, contratoNuevo, contratoActualizado, contratoPrevio;
    private transient Empleado empleadoRecibido; 
    private transient Afp afp;
    private transient List<Afp> listaAfps;
    private transient List<Contrato>  listaContratos;
    private transient Objeto datosAfp;
    private transient ServGestionarAfp gestionarAfp;
    private transient ServGestionarContrato gestionarContrato;
        
    private DefaultComboBoxModel modeloAfp;
    private boolean asignacion, estado, estadoRecibido;
    private String cargo, condicion; 
    private int horasSemanas, idContrato, idContratoRecibido;
    private double valorHora;
    private java.sql.Date fechaInicio, fechaFin;
    private transient EstiloEtiqueta estilo;  
    
    public DlgContrato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(346,408);
        setLocationRelativeTo(null); 
        setTitle("Contrato");  
        estadoDefault();       
    }
    
    void obtenerEstado(boolean parEstado){
        estadoRecibido = parEstado;
        estadoInicio();
    }
    
    void obtenerEmpleado(Empleado parEmpleado){
        empleadoRecibido = parEmpleado;
    }
    
    void obtenerIdContrato(int parIdContrato){
        idContratoRecibido = parIdContrato;
    }
    
    private void llenarCombo(){  
        buscarAfps();
        cboAfp.removeAllItems();
        if(!listaAfps.isEmpty()){         
            modeloAfp = new DefaultComboBoxModel();
            cboAfp.setModel(modeloAfp);           
            modeloAfp.addElement(new Objeto("<Seleccionar>", 0));
            for(Afp objetoAfp : listaAfps){
                datosAfp = new Objeto(objetoAfp.getNombre(), objetoAfp.getIdafp());
                modeloAfp.addElement(datosAfp);
            }
        }else
            Mensaje.mostrarAdvertencia(this, Mensaje.LISTA_VACIA);   
    }

    public void seleccionarValorCombo(JComboBox combo, String valor){
        String item;
        for (int i = 0; i < combo.getModel().getSize(); i++)
        {
            item = combo.getModel().getElementAt(i).toString();
            if (item.equalsIgnoreCase(valor))
            {
                combo.setSelectedIndex(i);
                break;
            }
        }
    } 
 
    public void verificarAfp(int parIdAfp){             
        try{
            gestionarAfp = new ServGestionarAfp();
            afp = gestionarAfp.verificarAfp(parIdAfp);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "VerificarAfp()");            
        } 
        if (afp == null){
            Mensaje.mostrarAfirmacion(this, Mensaje.OBJETO_NULO + "AFP");
        }
    }
    
    public void buscarAfps(){ 
        try{
            gestionarAfp = new ServGestionarAfp();
            listaAfps = gestionarAfp.buscarAfps();               
        } catch (Exception e) {
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "ConsultaAfp()");   
        }
        if (listaAfps.isEmpty()){
            Mensaje.mostrarAfirmacion(this, Mensaje.LISTA_VACIA + "AFPs");
        }        
    }
    
    public void verificarContrato(int parIdContrato){
        try{
            gestionarContrato = new ServGestionarContrato();
            contrato = gestionarContrato.verificarContrato(parIdContrato);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "VerificarContrato()");            
        } 
        if (contrato == null){
            Mensaje.mostrarAfirmacion(this, Mensaje.OBJETO_NULO + "Contrato");
        }
    }
    
    public void buscarContratos(int parIdEmpleado){           
        try{
            gestionarContrato = new ServGestionarContrato();
            listaContratos = gestionarContrato.buscarContratos(parIdEmpleado);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "BuscarContratos()");            
        }
        if (listaContratos.isEmpty()){
            Mensaje.mostrarAfirmacion(this, Mensaje.LISTA_VACIA + "Contratos");
        }
    }  

    public void mostrarDatos(Contrato parContrato){
        java.util.Date fechaActual = new java.util.Date();
        if(parContrato != null){
        txtCodigo.setText(String.valueOf(parContrato.getIdcontrato()));
        if(parContrato.getFechainiciocontrato().getTime() < fechaActual.getTime()){
            jdcFechaInicioContrato.setEnabled(false);
        }
        jdcFechaInicioContrato.setDate(parContrato.getFechainiciocontrato());
        jdcFechaFinContrato.setDate(parContrato.getFechafincontrato());               
        txtCargo.setText(parContrato.getCargo()); 
        int idAfp = parContrato.getAfp().getIdafp();
        verificarAfp(idAfp);
        if(afp != null){
            String Afp = afp.getNombre(); 
            seleccionarValorCombo(cboAfp, Afp); 
        }
        if(parContrato.getAsignacionfamiliar() == true) chkAsignacion.setSelected(true);
        else chkAsignacion.setSelected(false); 
        
        txtHorasSemana.setText(String.valueOf(parContrato.getTotalhoras()));        
        txtValorHora.setText(String.valueOf(parContrato.getValorhora()));      
        txtCondicion.setText(parContrato.getCondicion());
        }
    }
    
    public void verificarContratoPrevio(int parIdEmpleado){
        buscarContratos(parIdEmpleado); 
        int codigomayor = 0;
        if(!listaContratos.isEmpty()){
            for(Contrato objetoContrato : listaContratos){
                int codigo = objetoContrato.getIdcontrato();
                if(codigo > codigomayor)
                    codigomayor = codigo;
            }
        verificarContrato(codigomayor);
        contratoPrevio = contrato;
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
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFechaInicio.setText("Fecha Inicio:");
        getContentPane().add(lblFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, 20));

        jdcFechaInicioContrato.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaInicioContratoPropertyChange(evt);
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

        getContentPane().add(cboAfp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 140, -1));

        lblAsignacion.setText("Asignacion Familiar:");
        getContentPane().add(lblAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        chkAsignacion.setText("Si");
        getContentPane().add(chkAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        lblHorasSemana.setText("Horas/Semana:");
        getContentPane().add(lblHorasSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));

        txtHorasSemana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHorasSemanaKeyTyped(evt);
            }
        });
        getContentPane().add(txtHorasSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 60, -1));

        lblValorHora.setText("Valor/Hora:");
        getContentPane().add(lblValorHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));

        txtValorHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorHoraKeyTyped(evt);
            }
        });
        getContentPane().add(txtValorHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 60, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 100, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 100, -1));

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
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        obtenerDatos();

        if(estado){
            contratoNuevo = new Contrato(empleadoRecibido, afp, fechaInicio, fechaFin, cargo, asignacion, horasSemanas, valorHora); 
            try {                                           
                gestionarContrato = new ServGestionarContrato();
                int registros_afectados = gestionarContrato.ingresarContrato(contratoNuevo);              

                if(registros_afectados == 1){
                    Mensaje.mostrarAfirmacion(this, Mensaje.REGISTRO_CREADO);
                    btnRegistrar.setText("Actualizar");
                    btnCancelar.setText("Finalizar");
                    verificarContratoPrevio(contratoNuevo.getEmpleado().getIdempleado());
                    mostrarDatos(contratoPrevio);
                }
            } catch (Exception e) {
                Mensaje.mostrarError(this, Mensaje.ERROR_INGRESO);
            }
        }else{
            contratoActualizado = new Contrato(idContrato, empleadoRecibido, afp, fechaInicio, fechaFin, cargo, asignacion, horasSemanas, valorHora, condicion); 
            try {                                           
                gestionarContrato = new ServGestionarContrato();
                int registros_afectados = gestionarContrato.modificarContrato(contratoActualizado);              

                if(registros_afectados == 1){
                    Mensaje.mostrarAfirmacion(this, Mensaje.REGISTRO_ACTUALIZADO);
                    btnCancelar.setText("Finalizar");  
                    mostrarDatos(contratoActualizado);  
                }
            } catch (Exception e) {
                Mensaje.mostrarError(this, Mensaje.ERROR_ACTUALIZACION);
            } 
        }     
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarEntradas();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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

    private void txtHorasSemanaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasSemanaKeyTyped
        // TODO add your handling code here:
        String patron_de_entrada = "0123456789";
        if (txtHorasSemana.getText().length() == 2 || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))  
            evt.consume();
    }//GEN-LAST:event_txtHorasSemanaKeyTyped

    private void txtValorHoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorHoraKeyTyped
        // TODO add your handling code here:
        String patron_de_entrada = "0123456789.";
        if (txtValorHora.getText().length() == 5 || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar())))  
            evt.consume();
    }//GEN-LAST:event_txtValorHoraKeyTyped


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
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblSoles;
    private javax.swing.JLabel lblValorHora;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCondicion;
    private javax.swing.JTextField txtHorasSemana;
    private javax.swing.JTextField txtValorHora;
    // End of variables declaration//GEN-END:variables

    @Override
    public void estadoInicio() {
        estadoEntradas(true);
        estado = estadoRecibido;
        if(estadoRecibido){
            limpiarEntradas();
            btnRegistrar.setText("Guardar");    
        }else{
            verificarContrato(idContratoRecibido);
            mostrarDatos(contrato);
            btnRegistrar.setText("Actualizar");   
        }
    }

    @Override
    public void estado(boolean parEstado) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }
    
    @Override
    public void estadoDefault() {        
        jdcFechaFinContrato.requestFocus();
        btnRegistrar.setText("Registrar");
        btnCancelar.setText("Cancelar");
        llenarCombo();
        setMnemonic();
        estilo();
        eventoTecla();
    }

    @Override
    public void limpiarEntradas() {
        txtCodigo.setText("");     
        jdcFechaInicioContrato.setCalendar(null);
        jdcFechaFinContrato.setCalendar(null);
        txtCargo.setText("");
        if(cboAfp.getItemCount() != 0) cboAfp.setSelectedIndex(0);
        chkAsignacion.setSelected(false);
        txtHorasSemana.setText("");
        txtValorHora.setText("");
        txtCondicion.setText("");
    }

    @Override
    public void activarControles(boolean sw) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void activarEntradas(boolean sw) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void estadoControles(boolean sw) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void estadoEntradas(boolean sw) {
        txtCodigo.setEditable(false);
        txtCondicion.setEditable(false);
        cboAfp.setEditable(sw);
        txtCargo.setEditable(sw);
        txtHorasSemana.setEditable(sw);
        txtValorHora.setEditable(sw);
    }

    @Override
    public void visibilidadControles(boolean sw) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void obtenerDatos() {
        valorHora = 0.0;
        if(txtCargo.getText().compareTo("")!=0 && txtHorasSemana.getText().compareTo("")!=0 && txtValorHora.getText().compareTo("")!=0
            &&   jdcFechaInicioContrato.getDate() != null && jdcFechaFinContrato.getDate() != null && cboAfp.getSelectedIndex() > 0){
            if(txtCodigo.getText().compareTo("")!=0) idContrato = Integer.parseInt(txtCodigo.getText().trim());      
            long dateInicio;
            Date fechaInicioObtenida = jdcFechaInicioContrato.getDate();
            dateInicio = fechaInicioObtenida.getTime();
            fechaInicio = new java.sql.Date(dateInicio); 

            long dateFin;
            Date fechaFinObtenida = jdcFechaFinContrato.getDate(); 
            dateFin = fechaFinObtenida.getTime();
            fechaFin = new java.sql.Date(dateFin);   
            cargo = txtCargo.getText();                                           
            asignacion = chkAsignacion.isSelected(); 
            horasSemanas = Integer.parseInt(txtHorasSemana.getText()); 
            valorHora = Double.valueOf(txtValorHora.getText()); 
            condicion = txtCondicion.getText();

            if(cboAfp.getSelectedIndex() > 0){
                int indice = cboAfp.getSelectedIndex();
                Objeto ObjAfp = (Objeto)modeloAfp.getElementAt(indice);
                int idAfp =ObjAfp.getCodigo();            
                verificarAfp(idAfp);  
            }
        } else
            Mensaje.mostrarAdvertencia(this, Mensaje.DATOS_INCOMPLETOS);
    }

    @Override
    public void setMnemonic() {
        btnCancelar.setMnemonic(KeyEvent.VK_C);    
        if(estado == true){
            btnRegistrar.setMnemonic(KeyEvent.VK_G);
        }else{
            btnRegistrar.setMnemonic(KeyEvent.VK_A);   
        }
    }

    @Override
    public void estilo() { 
        estilo = this.setEstiloEtiquetas(new Object[]{lblCodigo, lblFechaInicio, lblFechaFin, lblCargo, lblAfp, lblAsignacion,
            lblHorasSemana, lblValorHora, lblCondicion, lblHoras, lblSoles});
        chkAsignacion.setFont(estilo.getEstiloFuenteLabel());
        chkAsignacion.setForeground(new Color(255,255,255));
        this.setEstiloBotones(new Object[]{btnRegistrar, btnCancelar});
        this.setImagenFondo(lblImagen, getPreferredSize().height, getPreferredSize().width);

    }

    @Override
    public void eventoTecla() {
        jdcFechaFinContrato.addKeyListener(new AtajosTecladoDlg(this));
        jdcFechaFinContrato.addKeyListener(new AtajosTecladoDlg(this));
        txtCargo.addKeyListener(new AtajosTecladoDlg(this));
        cboAfp.addKeyListener(new AtajosTecladoDlg(this));
        chkAsignacion.addKeyListener(new AtajosTecladoDlg(this));
        txtHorasSemana.addKeyListener(new AtajosTecladoDlg(this));
        txtValorHora.addKeyListener(new AtajosTecladoDlg(this));
        btnRegistrar.addKeyListener(new AtajosTecladoDlg(this));
        btnCancelar.addKeyListener(new AtajosTecladoDlg(this));
    }

}
