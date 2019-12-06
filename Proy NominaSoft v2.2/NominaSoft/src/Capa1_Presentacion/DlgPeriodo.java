/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa1_Presentacion;


import Capa2_Aplicacion.ServGestionarPeriodo;
import Capa1_Presentacion.Utilidades.EstructuraAbs;
import Capa3_Dominio.Periodo;
import Capa5_Excepcion.ExcepcionSinSoporteOperacion;
import Capa5_Excepcion.Mensaje;
import Capa1_Presentacion.Utilidades.AtajosTecladoDlg;
import Capa1_Presentacion.Utilidades.EstiloEtiqueta;
import Capa6_Globales.VariablesGlobales;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Jgarc
 */
public class DlgPeriodo extends EstructuraAbs {

    /**
     * Creates new form DlgPeriodo
     */
    private transient List<Periodo> listaPeriodo;
    private transient Periodo periodo, periodoVigente;
    private transient ServGestionarPeriodo gestionarPeriodo;

    private int idPeriodo;
    private String estadoPeriodo = "";
    private java.sql.Date fechainicioperiodo;
    private java.sql.Date fechafinperiodo;
    private boolean estado;
    private transient EstiloEtiqueta estilo;
    
    public DlgPeriodo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(360,340);
        setLocationRelativeTo(null);
        setTitle("Periodo");
        estadoDefault();
        estadoInicio();
    }
  
   public void devolverPeriodo(int parIdPeriodo){             
        try{
            gestionarPeriodo = new ServGestionarPeriodo();
            periodo = gestionarPeriodo.verificarPeriodo(parIdPeriodo);  
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "VerificarPeriodo()");            
        } 
        if (periodo == null){
            Mensaje.mostrarAfirmacion(this, Mensaje.OBJETO_NULO + "Periodo");
        }
    }
    
    public void consultarPeriodos(){     
        try{
            gestionarPeriodo = new ServGestionarPeriodo();
            listaPeriodo = gestionarPeriodo.buscarPeriodos();     
        }catch (Exception e) {   
            Mensaje.mostrarError(this, Mensaje.ERROR_SERVICIO + "ConsultarPeriodos()");            
        }
        if (listaPeriodo.isEmpty()){
            Mensaje.mostrarAfirmacion(this, Mensaje.LISTA_VACIA + "Periodos");
        }
    } 
   
    public void buscarPeriodoVigente(){
        consultarPeriodos();
        if(listaPeriodo != null){
            for(Periodo objetoPeriodo : listaPeriodo){
                if(objetoPeriodo.verificarPeriodoVigente()){
                    mostrarDatos(objetoPeriodo);
                    periodoVigente = objetoPeriodo;
                    estado(false);
                    break;
                }
            }
            if(periodoVigente == null){
                estadoDefault();
                btnNuevo.setEnabled(true);                
            }
        }
    }
  
    public void mostrarDatos(Periodo parPeriodo){  
        if(parPeriodo != null){
            txtCodigo.setText(String.valueOf(parPeriodo.getIdperiodo()));     
            txtEstado.setText(parPeriodo.getEstado());
            jdcFechaInicioPeriodo.setDate(parPeriodo.getFechainicioperiodo()); 
            jdcFechaFinPeriodo.setDate(parPeriodo.getFechafinperiodo());
        }  
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnDatosPeriodo = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        lblInicioPeriodo = new javax.swing.JLabel();
        jdcFechaFinPeriodo = new com.toedter.calendar.JDateChooser();
        lblFinPeriodo = new javax.swing.JLabel();
        jdcFechaInicioPeriodo = new com.toedter.calendar.JDateChooser();
        txtEstado = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnDatosPeriodo.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jpnDatosPeriodo.setOpaque(false);
        jpnDatosPeriodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigo.setText("Codigo:");
        jpnDatosPeriodo.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));
        jpnDatosPeriodo.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 119, -1));

        lblEstado.setText("Estado:");
        jpnDatosPeriodo.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        lblInicioPeriodo.setText("Inicio del Periodo:");
        jpnDatosPeriodo.add(lblInicioPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        jdcFechaFinPeriodo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaFinPeriodoPropertyChange(evt);
            }
        });
        jpnDatosPeriodo.add(jdcFechaFinPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 160, -1));

        lblFinPeriodo.setText("Fin del Periodo:");
        jpnDatosPeriodo.add(lblFinPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        jdcFechaInicioPeriodo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaInicioPeriodoPropertyChange(evt);
            }
        });
        jpnDatosPeriodo.add(jdcFechaInicioPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, -1));
        jpnDatosPeriodo.add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 160, -1));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jpnDatosPeriodo.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 80, -1));

        getContentPane().add(jpnDatosPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 190));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 80, -1));
        btnRegistrar.getAccessibleContext().setAccessibleName("vdv");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 80, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 80, -1));
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:   
        obtenerDatos();     
        if(estado) {
            try {                 
                periodo = new Periodo(fechainicioperiodo, fechafinperiodo);
                gestionarPeriodo = new ServGestionarPeriodo();
                int registros_afectados = gestionarPeriodo.ingresarPeriodo(periodo);                   
                if(registros_afectados == 1){
                    Mensaje.mostrarAfirmacion(this, Mensaje.REGISTRO_CREADO);
                    estadoControles(false);
                    btnRegistrar.setText(VariablesGlobales.ETIQUETA_BOTON_ACTUALIZAR);
                    btnCancelar.setText(VariablesGlobales.ETIQUETA_BOTON_FINALIZAR);
                    devolverPeriodo(periodo.getIdperiodo());
                    mostrarDatos(periodo);
                }
            } catch (Exception e) {
                Mensaje.mostrarError(this, Mensaje.ERROR_INGRESO);
            }
        }else {
            try{
                periodo = new Periodo(idPeriodo, estadoPeriodo, fechainicioperiodo, fechafinperiodo);
                gestionarPeriodo = new ServGestionarPeriodo();                          
                int registros_afectados =  gestionarPeriodo.modificarPeriodo(periodo);

                if(registros_afectados == 1){
                    Mensaje.mostrarAfirmacion(this, Mensaje.REGISTRO_ACTUALIZADO);                                      
                    btnCancelar.setText(VariablesGlobales.ETIQUETA_BOTON_TERMINAR);   
                    mostrarDatos(periodo);
                }
            } catch (Exception e) {
                Mensaje.mostrarError(this, Mensaje.ERROR_ACTUALIZACION);
            } 
        }     
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if(btnCancelar.getText().equalsIgnoreCase("Terminar")){
            dispose();
        }else if(btnCancelar.getText().equalsIgnoreCase("Cancelar") && periodoVigente != null){
            dispose();
        }else{
            mostrarDatos(periodo);
            estadoDefault();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jdcFechaInicioPeriodoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcFechaInicioPeriodoPropertyChange
        // TODO add your handling code here:
        jdcFechaInicioPeriodo.getJCalendar().setMinSelectableDate(new Date());
        ((JTextFieldDateEditor)jdcFechaFinPeriodo.getDateEditor()).setEditable(false);
    }//GEN-LAST:event_jdcFechaInicioPeriodoPropertyChange

    private void jdcFechaFinPeriodoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcFechaFinPeriodoPropertyChange
        // TODO add your handling code here:
         jdcFechaFinPeriodo.getJCalendar().setMinSelectableDate(new Date());
        ((JTextFieldDateEditor)jdcFechaFinPeriodo.getDateEditor()).setEditable(false);
    }//GEN-LAST:event_jdcFechaFinPeriodoPropertyChange

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        //&& !listaPeriodo.isEmpty()  
        estado(true);

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        activarEntradas(true);
        jdcFechaInicioPeriodo.setEnabled(true);
        activarControles(true);
        btnRegistrar.setText(VariablesGlobales.ETIQUETA_BOTON_ACTUALIZAR);
        estadoControles(true); 
    }//GEN-LAST:event_btnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private com.toedter.calendar.JDateChooser jdcFechaFinPeriodo;
    private com.toedter.calendar.JDateChooser jdcFechaInicioPeriodo;
    private javax.swing.JPanel jpnDatosPeriodo;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFinPeriodo;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblInicioPeriodo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEstado;
    // End of variables declaration//GEN-END:variables

    @Override
    public void estadoInicio() {
        buscarPeriodoVigente();
    }

    @Override
    public void estado(boolean parEstado) {
        estado = parEstado;
        if(parEstado == true){ 
            limpiarEntradas();
            activarEntradas(true);
            estadoControles(true);
            jdcFechaInicioPeriodo.requestFocus();     
            btnRegistrar.setText(VariablesGlobales.ETIQUETA_BOTON_REGISTRAR);
        }else{
            activarEntradas(false);
            estadoControles(false);
            jdcFechaInicioPeriodo.setEnabled(false);
            jdcFechaFinPeriodo.setEnabled(false);
        }    }

    @Override
    public void estadoDefault() {
        estilo();
        limpiarEntradas();
        txtCodigo.setEditable(false);
        txtEstado.setEditable(false);
        btnNuevo.requestFocus();
        btnRegistrar.setText(VariablesGlobales.ETIQUETA_BOTON_REGISTRAR);       
        activarControles(false);
        activarEntradas(false);
        eventoTecla();  
    }

    @Override
    public void limpiarEntradas() {
        txtCodigo.setText("");
        txtEstado.setText("");
        jdcFechaInicioPeriodo.setDate(null);
        jdcFechaFinPeriodo.setDate(null);
    }

    @Override
    public void activarControles(boolean sw) {
        btnNuevo.setEnabled(!sw);
        btnRegistrar.setEnabled(sw);
        btnModificar.setEnabled(sw);
        btnCancelar.setEnabled(sw);
    }

    @Override
    public void activarEntradas(boolean sw) {
        txtCodigo.setEnabled(sw);
        txtEstado.setEnabled(sw);
        jdcFechaInicioPeriodo.setEnabled(sw);       
        jdcFechaFinPeriodo.setEnabled(sw);
    }

    @Override
    public void estadoControles(boolean sw) {
        btnNuevo.setEnabled(false);
        btnRegistrar.setEnabled(sw);
        btnModificar.setEnabled(!sw);
        btnCancelar.setEnabled(true);
    }

    @Override
    public void estadoEntradas(boolean sw) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void visibilidadControles(boolean sw) {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void obtenerDatos() {
        if(jdcFechaInicioPeriodo.getDate() != null && jdcFechaFinPeriodo.getDate() != null){        
            if(txtCodigo.getText().length() > 0){
                idPeriodo = Integer.parseInt(txtCodigo.getText().trim());
            }    
            estadoPeriodo = txtEstado.getText().toUpperCase(Locale.ENGLISH);
            long date = 0;     
            Date Fechainicio = jdcFechaInicioPeriodo.getDate();                
            date = Fechainicio.getTime(); 
            fechainicioperiodo = new java.sql.Date(date);
            Date Fechafin = jdcFechaFinPeriodo.getDate();                
            date = Fechafin.getTime(); 
            fechafinperiodo = new java.sql.Date(date);     
        }else{
                Mensaje.mostrarAdvertencia(this, Mensaje.DATOS_INCOMPLETOS);
            }   
    }

    @Override
    public void setMnemonic() {
        this.excepcionNoSoporte(ExcepcionSinSoporteOperacion.crearNoSoportado());
    }

    @Override
    public void estilo() {
        estilo = this.setEstiloEtiquetas(new Object[]{lblCodigo, lblEstado, lblInicioPeriodo, lblFinPeriodo});
        jpnDatosPeriodo.setFont(estilo.getEstiloFuenteLabel());
        this.setEstiloBotones(new Object[]{btnNuevo, btnRegistrar, btnModificar, btnCancelar});
        this.setImagenFondo(lblImagen, getPreferredSize().height, getPreferredSize().width);
    }

    @Override
    public void eventoTecla() {
        jpnDatosPeriodo.addKeyListener(new AtajosTecladoDlg(this));
        txtCodigo.addKeyListener(new AtajosTecladoDlg(this));
        jdcFechaInicioPeriodo.addKeyListener(new AtajosTecladoDlg(this));
        jdcFechaFinPeriodo.addKeyListener(new AtajosTecladoDlg(this));
        txtEstado.addKeyListener(new AtajosTecladoDlg(this));
        btnNuevo.addKeyListener(new AtajosTecladoDlg(this));
        btnRegistrar.addKeyListener(new AtajosTecladoDlg(this));
        btnModificar.addKeyListener(new AtajosTecladoDlg(this));
        btnCancelar.addKeyListener(new AtajosTecladoDlg(this));
    }

}
