
package com.vistas;

import com.dao.DaoCarrera;
import com.modelo.Carrera;
import com.sun.glass.events.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmCarrera
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class FrmCarrera extends javax.swing.JInternalFrame {
    Carrera c = new Carrera();
    DaoCarrera daoc = new DaoCarrera();
    /**
     * Creates new form FrmCarrera
     */
    public FrmCarrera() throws ClassNotFoundException {
        initComponents();
        tablaCarrera();
        comboCarrera();
        rol();
    }
    
     public void rol(){
        if(FrmPrincipal.tipoUser==2){
            this.btnEliminar.setEnabled(false);
            this.btnModificar.setEnabled(false);
        }else{
            this.btnEliminar.setEnabled(true);
            this.btnModificar.setEnabled(true);
        }   
    }
    
    public void tablaCarrera() throws ClassNotFoundException{
        String [] columna = {"Codigo", "Nombre", "Cantidad", "Facultad"};
        Object[] obj = new Object[4];
        DefaultTableModel tabla = new DefaultTableModel(null, columna);
        List ls = daoc.mostrarCarrera();
        try {
            for(int i=0;i<ls.size();i++){
                c= (Carrera)ls.get(i);
                obj[0]= c.getCodigo();
                obj[1]= c.getNombre();
                obj[2]= c.getCantidad();
                obj[3]= c.getNombreFacultdad();
                tabla.addRow(obj);
            }
            ls= daoc.mostrarCarrera();
            this.jTable1.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tabla "+e.getMessage());
        }
    }
    
    public void comboCarrera() throws ClassNotFoundException{
        this.cmbFacultad.removeAllItems();
        ArrayList ls= daoc.mostrarCombo();
        try {
            for(int i=0;i<ls.size();i++){
                this.cmbFacultad.addItem((String) ls.get(i));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el comboBox "+e.getMessage());
        }
    }
    
    public void limpiar(){
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.sCantidad.setValue(1);
        this.cmbFacultad.setSelectedIndex(0);
    }
    
    public void insertar() throws ClassNotFoundException{
        try {
            c.setNombre(this.txtNombre.getText());
            c.setCantidad(Integer.parseInt(this.sCantidad.getValue().toString()));
            c.setNombreFacultdad(this.cmbFacultad.getSelectedItem().toString());
            if(daoc.insertarCarrera(c)){
               JOptionPane.showMessageDialog(null, "Se ingreso correctamente"); 
               tablaCarrera();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e.getMessage());
        }   
    }
    
    public void modificar(){
        try {
            c.setNombre(this.txtNombre.getText());
            c.setCantidad(Integer.parseInt(this.sCantidad.getValue().toString()));
            c.setNombreFacultdad(this.cmbFacultad.getSelectedItem().toString());
            c.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            int sino = JOptionPane.showConfirmDialog(this, "Desea modificar?","Se modificara", JOptionPane.YES_NO_OPTION);
                if(sino==0){
                    JOptionPane.showMessageDialog(rootPane, "modificado con exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                    daoc.modificarCarrera(c);
                    limpiar();
                    tablaCarrera();
                }
                else{
                    limpiar();
                }     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar");
        }
    }
    
    public void eliminar() throws ClassNotFoundException{
        try {
            c.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            int sino = JOptionPane.showConfirmDialog(this, "Desea eliminar?", "Se eliminara", JOptionPane.YES_NO_OPTION);
            if (sino == 0) {
                JOptionPane.showMessageDialog(rootPane, "eliminado con exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                daoc.eliminarCarrera(c);
                limpiar();
                tablaCarrera();
            } else {
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar "+e.getMessage());
        }
    }
    
    public void llenarTabla(){
        int fila = this.jTable1.getSelectedRow();
        this.txtCodigo.setText(String.valueOf(this.jTable1.getValueAt(fila, 0)));
        this.txtNombre.setText(String.valueOf(this.jTable1.getValueAt(fila, 1)));
        this.sCantidad.setValue(Integer.parseInt(String.valueOf(this.jTable1.getValueAt(fila, 2))));
        this.cmbFacultad.setSelectedItem(String.valueOf(this.jTable1.getValueAt(fila, 3)));
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        sCantidad = new javax.swing.JSpinner();
        cmbFacultad = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("Carrera ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Codigo");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Cantidad");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Facultdad");

        txtCodigo.setEditable(false);

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        sCantidad.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));

        cmbFacultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLimpiar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });

        btnInsertar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnInsertar.setText("Insertar");
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(243, 243, 243)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addGap(233, 233, 233)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(sCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addComponent(cmbFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        try {
            insertar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
        limpiar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        try {
            eliminar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        limpiar();
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        llenarTabla();
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        Character s = evt.getKeyChar();
        if(!Character.isLetter(s) && s!= KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbFacultad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JSpinner sCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
