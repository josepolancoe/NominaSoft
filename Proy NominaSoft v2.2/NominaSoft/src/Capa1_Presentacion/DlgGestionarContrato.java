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
import Capa1_Presentacion.Utilidades.EstructuraAbs;
import Capa5_Excepcion.ExcepcionSinSoporteOperacion;
import Capa5_Excepcion.Mensaje;
import Capa1_Presentacion.Utilidades.AtajosTecladoDlg;
import Capa6_Globales.VariablesGlobales;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lucab
 */
public class DlgGestionarContrato extends EstructuraAbs {

    /**
     * Creates new form DlgContrato
     */
    private transient Empleado empleado, empleadoRecibido;
    private transient Contrato contrato, contratoPrevio, contratoVigente;
    private transient List<Contrato> listaContrato;    
    private transient ServGestionarEmpleado gestionarEmpleado;
    private transient ServGestionarContrato gestionarContrato;    
    private String dniRecibido;
    private boolean estado; 
        
    public DlgGestionarContrato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(516,528);
        setLocationRelativeTo(null);
        setTitle("Gestionar Contrato");
        estadoDefault();
    }
    
    void obtenerEmpleado(Empleado parEmpleado){    
        empleadoRecibido = parEmpleado;
        estadoInicio();
    }

    void obtenerDni(String parDni){    
        dniRecibido = parDni;
        estadoInicio();
    }


    public void verificarEmpleado(String parDni){             
        try{
            gestionarEmpleado = new ServGestionarEmpleado();
            empleado = gestionarEmpleado.verificarEmpleado(parDni);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "VerificarEmpleado()");            
        }                               
    }  
       
    public void buscarContratos(int parIdEmpleado){
        try{
            gestionarContrato = new ServGestionarContrato();
            listaContrato = gestionarContrato.buscarContratos(parIdEmpleado);     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "BuscarContratos()");            
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
    
    public void verificarContratoVigente(int parIdEmpleado){ 
        contratoVigente = null;
        buscarContratos(parIdEmpleado); 
        if(listaContrato != null){
            for(Contrato objetoContrato : listaContrato){         
                if(objetoContrato.verificarContratoVigente() == true){
                    contratoVigente = objetoContrato;
                    break;
                }                               
            }   
        }
    }
    
    public void verificarContratoPrevio(int parIdEmpleado){
        contratoPrevio = null;
        buscarContratos(parIdEmpleado); 
        int codigomayor = 0;
        if(!listaContrato.isEmpty()){
            for(Contrato objetoContrato : listaContrato){
                int codigo = objetoContrato.getIdcontrato();
                if(codigo > codigomayor)
                    codigomayor = codigo;
            }
        verificarContrato(codigomayor);
        contratoPrevio = contrato;
        }
    }
    
    private void verificaContratoDni(){
        if(txtDni.getText().compareTo("")!=0 && txtDni.getText().length() == 8){  
            verificarEmpleado(txtDni.getText());
            if(empleado != null){
                obtenerEmpleado(empleado);
            }else 
                obtenerDni(txtDni.getText());
        } else
            Mensaje.mostrarAdvertencia(this, Mensaje.INGRESE_DNI);
    }

    public void mostrarDatosContrato(Contrato parContrato){ 
        if(parContrato != null){
            lblVerCodigoContrato.setText(String.valueOf(parContrato.getIdcontrato()));
            lblVerCondicion.setText(parContrato.getCondicion());                           
        }
    }
    
    public void mostrarDatosEmpleado(Empleado parEmpleado){
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
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
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

        getContentPane().add(jpnDatosEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 470, 240));

        btnCrearContrato.setText("Crear Contrato");
        btnCrearContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearContratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrearContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, -1));

        btnEditarContrato.setText("Editar Contrato");
        btnEditarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarContratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 110, -1));

        jpnDatosContrato.setBorder(javax.swing.BorderFactory.createTitledBorder("Ultimo Contrato"));
        jpnDatosContrato.setOpaque(false);
        jpnDatosContrato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigoContrato.setText("Codigo:");
        jpnDatosContrato.add(lblCodigoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        lblVerCodigoContrato.setText("...");
        jpnDatosContrato.add(lblVerCodigoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 70, 20));

        lblCondicion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCondicion.setText("Condicion:");
        jpnDatosContrato.add(lblCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, 20));

        lblVerCondicion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblVerCondicion.setText("...");
        jpnDatosContrato.add(lblVerCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 90, 20));

        getContentPane().add(jpnDatosContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 470, 70));

        btnAnularContrato.setText("Anular Contrato");
        btnAnularContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularContratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnularContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 110, -1));

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

        getContentPane().add(jpnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, 80));
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 500));

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
        verificaContratoDni();
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        estadoDefault();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCrearContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearContratoActionPerformed
        // TODO add your handling code here:
        DlgContrato DC = new DlgContrato(null, true);
        DC.obtenerEmpleado(empleadoRecibido);
        DC.obtenerEstado(true);
        DC.setVisible(true); 
    }//GEN-LAST:event_btnCrearContratoActionPerformed

    private void btnEditarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarContratoActionPerformed
        // TODO add your handling code here:
        DlgContrato DC = new DlgContrato(null, true);
        DC.obtenerEmpleado(empleadoRecibido);
        DC.obtenerIdContrato(Integer.parseInt(lblVerCodigoContrato.getText()));
        DC.obtenerEstado(false);
        DC.setVisible(true);
    }//GEN-LAST:event_btnEditarContratoActionPerformed

    private void btnAnularContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularContratoActionPerformed
        // TODO add your handling code here:   
        if(btnAnularContrato.getText().equalsIgnoreCase("Anular Contrato")){
            int seleccion = JOptionPane.showConfirmDialog(this,"¿Deseas anular el Contrato? ","!Atencion!",JOptionPane.YES_NO_OPTION );
            if(seleccion == JOptionPane.YES_OPTION) {                
                try {                
                    gestionarContrato = new ServGestionarContrato();                
                    int registros_afectados = gestionarContrato.anularContrato(contratoVigente);

                    if(registros_afectados == 1){
                        Mensaje.mostrarAfirmacion(this, Mensaje.REGISTRO_ANULADO);         
                        btnAnularContrato.setText(VariablesGlobales.ETIQUETA_BOTON_TERMINAR);                
                        obtenerEmpleado(empleadoRecibido);
                    }            
                } catch (Exception e) {
                    Mensaje.mostrarError(this, Mensaje.ERROR_ANULAR);
                }    
            } 
        }else{
            dispose();
        }

    }//GEN-LAST:event_btnAnularContratoActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){    
            verificaContratoDni();    
        }
        
    }//GEN-LAST:event_txtDniKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if(txtDni.getText().compareTo("")!=0 && txtDni.getText().length() == 8){
            obtenerDni(txtDni.getText());
        }
    }//GEN-LAST:event_formWindowActivated


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
    private javax.swing.JLabel lblImagen;
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

    @Override
    public void estadoInicio() {       
        activarControles(true);
        visibilidadControles(true); 
              if(empleadoRecibido != null){
                  mostrarDatosEmpleado(empleadoRecibido);
                  buscarContratos(empleadoRecibido.getIdempleado());
                  if (!listaContrato.isEmpty()){
                      estado = false; 
                      verificarContratoVigente(empleadoRecibido.getIdempleado());
                      if(contratoVigente != null){
                          mostrarDatosContrato(contratoVigente);
                          estadoControles(true);
                      }else{
                          verificarContratoPrevio(empleadoRecibido.getIdempleado());
                              if(contratoPrevio != null){
                                  mostrarDatosContrato(contratoPrevio);
                                  estadoControles(false);
                              }  
                      }        
                  }else{
                      lblVerCondicion.setText("Sin Contratos");
                      estadoControles(false);
                  }  
              }else if(dniRecibido != null){
                  estado = true;
                  txtDni.setText(dniRecibido); 
                  verificarEmpleado(dniRecibido);
              } 
    }

    @Override
    public void estado(boolean parEstado) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }
    
    @Override
    public void estadoDefault() {
        limpiarEntradas();
        activarControles(false);
        txtDni.requestFocus();
        btnAnularContrato.setText(VariablesGlobales.ETIQUETA_BOTON_ANULAR);
        visibilidadControles(false);
        setMnemonic();
        estilo();
        eventoTecla();
    }

    @Override
    public void limpiarEntradas() {
        txtDni.setText("");
        lblVerCodigoEmpleado.setText("");        
        lblVerNombre.setText("");
        lblVerNacimiento.setText("");
        lblVerEstadoCivil.setText("");
        lblVerGrado.setText("");
        lblVerTelefono.setText("");
        lblVerDireccion.setText("");
    }

    @Override
    public void activarControles(boolean sw) {
        btnVerificar.setEnabled(!sw);
        btnCrearContrato.setEnabled(sw);
        btnEditarContrato.setEnabled(sw);
        btnAnularContrato.setEnabled(sw);
    }

    @Override
    public void activarEntradas(boolean sw) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void estadoControles(boolean sw) {
        btnVerificar.setEnabled(false);
        btnCrearContrato.setEnabled(!sw);
        btnEditarContrato.setEnabled(sw);
        btnAnularContrato.setEnabled(sw);
    }

    @Override
    public void estadoEntradas(boolean sw) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void visibilidadControles(boolean sw) {
        this.jpnBuscar.setVisible(true);
        this.jpnDatosEmpleado.setVisible(sw);
        this.jpnDatosContrato.setVisible(sw);
        this.btnCrearContrato.setVisible(sw);
        this.btnEditarContrato.setVisible(sw);
        this.btnAnularContrato.setVisible(sw);
    }

    @Override
    public void obtenerDatos() {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void setMnemonic() {
        btnVerificar.setMnemonic(KeyEvent.VK_B);
        btnLimpiar.setMnemonic(KeyEvent.VK_L);
        btnCrearContrato.setMnemonic(KeyEvent.VK_C);
        btnEditarContrato.setMnemonic(KeyEvent.VK_E);
        btnAnularContrato.setMnemonic(KeyEvent.VK_A);
    }

    @Override
    public void estilo() {    
        this.setEstiloEtiquetas(new Object[]{lblDni, lblCodigoEmpleado, lblNombre, lblNacimiento, lblEstadoCivil, lblGrado, lblTelefono,
            lblDireccion, lblVerCodigoEmpleado, lblVerNombre, lblVerNacimiento, lblVerEstadoCivil, lblVerGrado,
            lblVerTelefono, lblVerDireccion, lblCodigoContrato, lblVerCodigoContrato});  //lblCondicion, lblVerCondicion
        this.setEstiloBotones(new Object[]{btnVerificar, btnLimpiar, btnCrearContrato, btnEditarContrato, btnAnularContrato});
        this.setImagenFondo(lblImagen, getPreferredSize().height, getPreferredSize().width);
    }

    @Override
    public void eventoTecla() {
        jpnDatosEmpleado.addKeyListener(new AtajosTecladoDlg(this));
        jpnDatosContrato.addKeyListener(new AtajosTecladoDlg(this));
        txtDni.addKeyListener(new AtajosTecladoDlg(this));
        btnVerificar.addKeyListener(new AtajosTecladoDlg(this));
        btnLimpiar.addKeyListener(new AtajosTecladoDlg(this));
        btnCrearContrato.addKeyListener(new AtajosTecladoDlg(this));
        btnEditarContrato.addKeyListener(new AtajosTecladoDlg(this));
        btnAnularContrato.addKeyListener(new AtajosTecladoDlg(this));
    }

}
