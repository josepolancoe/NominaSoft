/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;

import Capa2_Aplicacion.ServGestionarEmpleado;
import Capa3_Dominio.Empleado;
import Capa1_Presentacion.Utilidades.EstructuraAbs;
import Capa5_Excepcion.ExcepcionSinSoporteOperacion;
import Capa5_Excepcion.Mensaje;
import Capa1_Presentacion.Utilidades.AtajosTecladoDlg;
import Capa1_Presentacion.Utilidades.EstiloEtiqueta;
import Capa6_Globales.VariablesGlobales;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author lucab
 */
public class DlgEmpleado extends EstructuraAbs {

    /**
     * Creates new form DlgEmpleado
     */
    private transient Empleado empleado, empleadoRecibido;
    private transient ServGestionarEmpleado gestionarEmpleado;
    
    private boolean estado; 
    private String nombre, dni, estadoCivil, gradoAcademico, telefono, direccion, dniRecibido;
    private int idEmpleado = 0;
    private java.sql.Date fechaNacimiento;
    private transient EstiloEtiqueta estilo; 
    
    public DlgEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(496,498);
        setLocationRelativeTo(null);
        setTitle("Empleado");
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

    private void verificaEmpleadoDni(){
        if(txtDni.getText().compareTo("")!=0 && txtDni.getText().length() == 8){  
            verificarEmpleado(txtDni.getText());
            if(empleado != null){
                obtenerEmpleado(empleado);
            }else 
                obtenerDni(txtDni.getText());
        } else
            Mensaje.mostrarAdvertencia(this, Mensaje.INGRESE_DNI);
    }

    private void verificarEmpleado(String parDni){             
        try{
            gestionarEmpleado = new ServGestionarEmpleado();
            empleado = gestionarEmpleado.verificarEmpleado(parDni);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "VerificarEmpleado()");            
        } 
        if (empleado == null){
            Mensaje.mostrarAfirmacion(this, Mensaje.OBJETO_NULO + "Empleado");
        }
    }               

    private void mostrarDatos(Empleado parEmpleado){
        if(parEmpleado != null){
            txtCodigo.setText(String.valueOf(parEmpleado.getIdempleado()));
            txtNombre.setText(parEmpleado.getNombre());
            txtDni.setText(parEmpleado.getDni());        
            jdcNacimiento.setDate(parEmpleado.getFechanacimiento());             
            estadoCivil = (parEmpleado.getEstadocivil()); 
            cboEsCivil.setSelectedItem(estadoCivil);
            gradoAcademico = (parEmpleado.getGradoacademico());
            cboGrado.setSelectedItem(gradoAcademico);
            txtTelefono.setText(parEmpleado.getTelefono());               
            txtDireccion.setText(parEmpleado.getDireccion());
        }
    }
    
    private void registraActualiza(){
        obtenerDatos();
        if(estado) {
            try {                 
                empleado = new Empleado(dni, nombre, fechaNacimiento, gradoAcademico, estadoCivil, telefono, direccion);                    
                gestionarEmpleado = new ServGestionarEmpleado();
                int registros_afectados = gestionarEmpleado.ingresarEmpleado(empleado);                    
                if(registros_afectados == 1){
                    Mensaje.mostrarAfirmacion(this, Mensaje.REGISTRO_CREADO);
                    verificaEmpleadoDni();
                    btnCancelar.setText(VariablesGlobales.ETIQUETA_BOTON_TERMINAR);                         
                }    
            } catch (Exception e) {
                Mensaje.mostrarError(this, Mensaje.ERROR_INGRESO);
            }
        } else {
            try {
                empleado = new Empleado(idEmpleado, dni, nombre, fechaNacimiento, gradoAcademico, estadoCivil, telefono, direccion);                                      
                gestionarEmpleado = new ServGestionarEmpleado();                          
                int registros_afectados = gestionarEmpleado.modificarEmpleado(empleado);

                if(registros_afectados == 1){
                    Mensaje.mostrarAfirmacion(this, Mensaje.REGISTRO_ACTUALIZADO);
                    verificaEmpleadoDni();
                    btnCancelar.setText(VariablesGlobales.ETIQUETA_BOTON_TERMINAR);   
                }     
            } catch (Exception e) {
                Mensaje.mostrarError(this, Mensaje.ERROR_ACTUALIZACION);
            }
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
        lblImagen = new javax.swing.JLabel();

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

        getContentPane().add(jpnContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 450, 100));

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

        getContentPane().add(jpnIdentidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, 80));

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
        jpnDatos.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 100, -1));

        lblCodigo.setText("Codigo:");
        jpnDatos.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        getContentPane().add(jpnDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 450, 190));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 90, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 90, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 90, -1));
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
        String patron_de_entrada = "0123456789";
        if (txtDni.getText().length() == 8 || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))){
        evt.consume();
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void btnVerificaEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificaEmpleadoActionPerformed
        // TODO add your handling code here:  
        verificaEmpleadoDni();
    }//GEN-LAST:event_btnVerificaEmpleadoActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if(!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_SPACE) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
        evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        registraActualiza();   
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if(btnCancelar.getText().equalsIgnoreCase("Terminar")){
                int seleccion = JOptionPane.showConfirmDialog(this,"¿Deseas gestionar otro empleado? ","!Atencion!",JOptionPane.YES_NO_OPTION );
                if(seleccion == JOptionPane.YES_OPTION) { 
                    estadoDefault();
                    empleadoRecibido = null;
                    dniRecibido = null;
                    empleado = null;
                }else
                    this.dispose(); 
        }else{
            this.dispose();   
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:   
        if(txtDni.getText().compareTo("")!=0){    
            int seleccion = JOptionPane.showConfirmDialog(this,"Deseas Eliminarlo ","Eliminar Empleado",JOptionPane.YES_NO_OPTION );
            if( seleccion == JOptionPane.YES_OPTION ) {   
                try {                
                    gestionarEmpleado = new ServGestionarEmpleado();                
                    int registros_afectados = gestionarEmpleado.eliminarEmpleado(empleado);

                    if(registros_afectados == 1){
                        Mensaje.mostrarAfirmacion(this, Mensaje.REGISTRO_ELIMINADO);
                        estadoDefault();
                    }
                } catch (Exception e) {
                    Mensaje.mostrarError(this, Mensaje.ERROR_ELIMINAR);
                    Mensaje.mostrarAdvertencia(this, "El empleado se eliminara solo si no tiene ningun contrato activo");
                }       
            }          
        }else
            Mensaje.mostrarAdvertencia(this, "Sin datos para eliminar");
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        // TODO add your handling code here: 
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            verificaEmpleadoDni();
        }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_txtDniKeyPressed


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
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    @Override
    public void estadoInicio() {
        activarControles(true);
        activarEntradas(true); 
        estadoEntradas(true);
        visibilidadControles(true);
        if(empleadoRecibido != null){
            estado = false;          
            btnRegistrar.setText(VariablesGlobales.ETIQUETA_BOTON_ACTUALIZAR);
            btnCancelar.setText(VariablesGlobales.ETIQUETA_BOTON_CANCELAR);  
            estadoControles(true);
            mostrarDatos(empleadoRecibido);
            setMnemonic();           
        }else if(dniRecibido != null){
            estado = true;
            txtDni.setText(dniRecibido);                   
            estadoControles(true);  
            txtNombre.requestFocus(); 
            setMnemonic();  
        }    }

    @Override
    public void estado(boolean parEstado) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }
    
    @Override
    public void estadoDefault() {
        limpiarEntradas();
        estilo();
        activarControles(false);
        activarEntradas(false);
        estadoEntradas(false);
        visibilidadControles(false);
        txtDni.requestFocus();
        btnRegistrar.setText(VariablesGlobales.ETIQUETA_BOTON_REGISTRAR);        
        btnCancelar.setText(VariablesGlobales.ETIQUETA_BOTON_CANCELAR);
    }

    @Override
    public void limpiarEntradas() {
        txtCodigo.setText("");
        txtDni.setText("");
        txtNombre.setText("");
        jdcNacimiento.setDate(null);
        cboEsCivil.setSelectedIndex(0);
        cboGrado.setSelectedIndex(0);
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

    @Override
    public void activarControles(boolean sw) {
        btnVerificaEmpleado.setEnabled(!sw);
        btnRegistrar.setEnabled(sw);
        btnEliminar.setEnabled(sw);
        btnCancelar.setEnabled(sw);
    }

    @Override
    public void activarEntradas(boolean sw) {
        txtCodigo.setEnabled(sw);
        txtNombre.setEnabled(sw);
        jdcNacimiento.setEnabled(sw);
        cboEsCivil.setEnabled(sw);
        cboGrado.setEnabled(sw);       
        txtTelefono.setEnabled(sw);       
        txtDireccion.setEnabled(sw);
    }

    @Override
    public void estadoControles(boolean sw) {
        btnVerificaEmpleado.setEnabled(false);
        btnRegistrar.setEnabled(true);
        if(btnRegistrar.getText().equals("Registrar")) btnEliminar.setEnabled(false); else btnEliminar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    @Override
    public void estadoEntradas(boolean sw) {
        txtCodigo.setEditable(false);
        txtDni.setEditable(!sw);
        txtNombre.setEditable(sw);
        cboEsCivil.setEditable(sw);
        cboGrado.setEditable(sw);
        txtTelefono.setEditable(sw);
        txtDireccion.setEditable(sw);
    }

    @Override
    public void visibilidadControles(boolean sw) {
        this.jpnIdentidad.setVisible(true);
        this.jpnDatos.setVisible(sw);
        this.jpnContacto.setVisible(sw);
        this.btnVerificaEmpleado.setVisible(!sw);
        this.btnRegistrar.setVisible(sw);
        this.btnEliminar.setVisible(sw);
        this.btnCancelar.setVisible(sw); 
    }

    @Override
    public void obtenerDatos() {
        if(txtNombre.getText().compareTo("")!=0 && txtDni.getText().compareTo("")!=0 && txtTelefono.getText().compareTo("")!=0  && txtDireccion.getText().compareTo("")!=0)
            {  
            if(txtCodigo.getText().length() > 0){
                idEmpleado = Integer.parseInt(txtCodigo.getText().trim());
            }
            dni = txtDni.getText();
            nombre = txtNombre.getText();                  
            long date = 0;     
            Date Fecha = jdcNacimiento.getDate();                
            date = Fecha.getTime(); 
            fechaNacimiento = new java.sql.Date(date); 
            gradoAcademico = String.valueOf(cboGrado.getSelectedItem());
            estadoCivil = String.valueOf(cboEsCivil.getSelectedItem());
            telefono = txtTelefono.getText();           
            direccion = txtDireccion.getText();       
        } else
            Mensaje.mostrarAdvertencia(this, Mensaje.DATOS_INCOMPLETOS);
    }

    @Override
    public void setMnemonic() {
        btnEliminar.setMnemonic(KeyEvent.VK_E);
        if(estado == true){
            btnRegistrar.setMnemonic(KeyEvent.VK_R);
            btnCancelar.setMnemonic(KeyEvent.VK_C);
        }else{
            btnRegistrar.setMnemonic(KeyEvent.VK_A);
            btnCancelar.setMnemonic(KeyEvent.VK_T);    
        }
    }

    @Override
    public void estilo() {
        estilo = this.setEstiloEtiquetas(new Object[ ]{lblDni, lblCodigo, lblNombre, lblNacimiento, lblEstado, lblGrado, lblTelefono,
            lblDireccion});
        jpnIdentidad.setFont(estilo.getEstiloFuenteLabel());    
        jpnDatos.setFont(estilo.getEstiloFuenteLabel());
        jpnContacto.setFont(estilo.getEstiloFuenteLabel());
        this.setEstiloBotones(new Object[]{btnVerificaEmpleado, btnRegistrar, btnEliminar, btnCancelar});
        this.setImagenFondo(lblImagen, getPreferredSize().height, getPreferredSize().width);

    }

    @Override
    public void eventoTecla() {
        txtDni.addKeyListener(new AtajosTecladoDlg(this));
        txtCodigo.addKeyListener(new AtajosTecladoDlg(this));
        txtNombre.addKeyListener(new AtajosTecladoDlg(this));
        jdcNacimiento.addKeyListener(new AtajosTecladoDlg(this));
        cboEsCivil.addKeyListener(new AtajosTecladoDlg(this));
        cboGrado.addKeyListener(new AtajosTecladoDlg(this));
        txtDireccion.addKeyListener(new AtajosTecladoDlg(this));
        txtTelefono.addKeyListener(new AtajosTecladoDlg(this));
        btnVerificaEmpleado.addKeyListener(new AtajosTecladoDlg(this));
        btnRegistrar.addKeyListener(new AtajosTecladoDlg(this));
        btnEliminar.addKeyListener(new AtajosTecladoDlg(this));
        btnCancelar.addKeyListener(new AtajosTecladoDlg(this));
    }

}
