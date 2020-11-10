
package com.vistas;

import com.dao.DaoFacultad;
import com.modelo.Facultad;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmFacultad
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class FrmFacultad extends javax.swing.JInternalFrame {

    Facultad f = new Facultad();
    DaoFacultad daof = new DaoFacultad();
    public FrmFacultad() {
        initComponents();
        tablaFacultad();
        rol();
    }
    
    public void tablaFacultad(){
        String[] columnas = {"Codigo","Nombre","Telefono"};
        Object[] obj = new Object[3];
        DefaultTableModel tabla = new DefaultTableModel(null,columnas);
        List ls;
        try {
            ls = daof.mostrarFacultad();
            for(int i=0; i<ls.size();i++){
                f=(Facultad)ls.get(i);
                obj[0]= f.getCodigo();
                obj[1]= f.getNombre();
                obj[2]= f.getTelefono();
                tabla.addRow(obj);
            } 
            
            ls= daof.mostrarFacultad();
            this.jTable1.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar "+e.getMessage());
        }   
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
    
    public void limpiar(){
        this.txtNombre.setText("");
        this.txtTelefono.setText("");
        this.txtCodigo.setText("");
    }
    
    public void insertar() throws ClassNotFoundException{
        try {
            f.setNombre(this.txtNombre.getText());
            f.setTelefono(this.txtTelefono.getText());
            if(daof.insertarFacultad(f)){
                JOptionPane.showMessageDialog(null, "Se ingreso correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al ingresar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        limpiar();
        tablaFacultad();
    }
    
    public void modificar(){
        try {
            f.setNombre(this.txtNombre.getText());
            f.setTelefono(this.txtTelefono.getText());
            f.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            int sino = JOptionPane.showConfirmDialog(this, "Desea modificar?","Se modificara", JOptionPane.YES_NO_OPTION);
                if(sino==0){

                    JOptionPane.showMessageDialog(rootPane, "modificado con exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                    daof.modificarFacultad(f);
                    limpiar();
                    tablaFacultad();
                }
                else{
                    limpiar();
                }
        } catch (Exception e) {
            e.printStackTrace(); 
        }  
    }
    
    public void eliminar(){
        try {
           f.setCodigo(Integer.parseInt(this.txtCodigo.getText())); 
           int sino = JOptionPane.showConfirmDialog(this, "Desea eliminar?","Se eliminara", JOptionPane.YES_NO_OPTION);
                if(sino==0){

                    JOptionPane.showMessageDialog(rootPane, "eliminado con exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                    daof.eliminarFacultad(f);
                    limpiar();
                    tablaFacultad();
                }
                else{
                    limpiar();
                }
        } catch (Exception e){
            e.printStackTrace();
        }   
    }
    
    public void llenarTabla(){
        int fila = this.jTable1.getSelectedRow();
        this.txtCodigo.setText(String.valueOf(this.jTable1.getValueAt(fila, 0)));
        this.txtNombre.setText(String.valueOf(this.jTable1.getValueAt(fila, 1)));
        this.txtTelefono.setText(String.valueOf(this.jTable1.getValueAt(fila, 2)));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JFormattedTextField();
        btnInsertar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("Facultad");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Telefono");

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
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

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

        btnLimpiar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Codigo");

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(txtNombre)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        try {
            insertar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmFacultad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
