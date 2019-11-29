/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa6_Globales;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author lucab
 */
public class IngresoDni extends JDialog {    
    Set<Integer> ingresoTeclaInt = new HashSet<Integer>();
    Set<Character> ingresoTeclaChr = new HashSet<Character>();
    JLabel lblDni;
    JTextField txtDni;
    JButton btnAceptar, btnCancelar;
    String ingreso = "", respuesta = "",  tecla = "", dni = "", obtenerDni = "";
    int i = 0, j = 0; 
    boolean cancelar = false;
////////////////////////////////////////////////////////////////////////////////    
    public IngresoDni(java.awt.Frame parent, boolean modal) {    
        super(parent, modal);
        this.pack();
        this.setResizable(false);
        this.setTitle("Verificar DNI");
        this.setSize(240, 126);          
        this.setLocationRelativeTo(null);       
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(153,153,153));

////////////////////////////////////////////////////////////////////////////////  
        Font fuente = new Font("Arial", 1, 12);
        Color color = new Color(255,255,255);
        lblDni = new JLabel("DNI aqui: ");
        lblDni.setFont(fuente);
        lblDni.setForeground(color);
        txtDni = new JTextField();
        btnAceptar = new JButton("Verificar");
        btnCancelar = new JButton("Cancelar");
        btnAceptar.setBounds(22, 49, 89, 25);
        btnCancelar.setBounds(129, 49, 89, 25);
        lblDni.setBounds(34, 10, 99, 25);
        txtDni.setBounds(93, 11, 99, 25);
        this.getContentPane().add(lblDni);
        this.getContentPane().add(txtDni);
        this.getContentPane().add(btnAceptar);
        this.getContentPane().add(btnCancelar);

////////////////////////////////////////////////////////////////////////////////        
        btnAceptar.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e) {
                verificarDni();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e) { 
                cancelarDni();
                dispose();
            }	
        });
////////////////////////////////////////////////////////////////////////////////        
        KeyListener eventoTeclado;
        eventoTeclado = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                txtDni.setText(ingreso); 
            }
   
            @Override
            public void keyPressed(KeyEvent ke) {
                ingresoTeclaChr.add(ke.getKeyChar()); 
                
                if(ke.getKeyCode()==KeyEvent.VK_ESCAPE){
                    dispose();
                    
                }
                
                if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                    verificarDni();
                }
                
                
                if(ingresoTeclaChr.size() > 1){                                      
                    for (Iterator<Character> recorrer = ingresoTeclaChr.iterator(); recorrer.hasNext(); ) 
                    { 
                        tecla = String.valueOf(recorrer.next());
                        break;
                    }
                }else
                     tecla =  String.valueOf(ke.getKeyChar());         
            }
            
            @Override
            public void keyReleased(KeyEvent ke) {
                    String patron = "0123456789";                    
                    if(!patron.contains(String.valueOf(ke.getKeyChar())) || txtDni.getText().length() == 9){
                        if(ke.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                            txtDni.setText(ingreso);
                        }else{
                            if(ingreso.length() > 0)
                                j = ingreso.length() - 1;
                                ingreso = ingreso.substring(0,j);
                                txtDni.setText(ingreso);             
                        }
                    }else{
                        ingreso = ingreso + tecla;
                        j = ingreso.length() - 1;
                    }      
            tecla = "";    
            ingresoTeclaChr.remove(ke.getKeyChar());
            }
        };
        txtDni.addKeyListener(eventoTeclado);      
////////////////////////////////////////////////////////////////////////////////
        this.setVisible(true);  
    } 
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////    
    
    public void verificarDni(){
        if(txtDni.getText().matches("([0-9]|\\s)+") && txtDni.getText().length() == 8){                         
            obtenerDni = txtDni.getText(); 
            VGlobales.globalDNI = txtDni.getText(); 
            dispose(); 
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese DNI valido"); 
            txtDni.requestFocus(); 
        } 

    } 
    
    public String retornaDni(){
        return obtenerDni;
    }
    
    public Boolean cancelarDni(){
        return cancelar = true;
    }
    
    public static void main(String[] args){
        IngresoDni dialog = new IngresoDni(new JFrame(), true); 
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        //dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        

    }
}


