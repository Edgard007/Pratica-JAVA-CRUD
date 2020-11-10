
package com.vistas;

import com.dao.DaoEstudiante;
import com.modelo.Estudiante;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmEstudiante
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class FrmEstudiante extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmEstudiante
     */
    DaoEstudiante daoe = new DaoEstudiante();
    Estudiante e = new Estudiante();
    public FrmEstudiante() throws ClassNotFoundException {
        initComponents();
        tablaEstudiante();
        comboFacultad();
        comboCarrera();
        rol();
    }
    public void tablaEstudiante() throws ClassNotFoundException{
        String [] columna = {"Codigo","Nombre","Edad","Genero","CUM","Intereses","Facultad","Carrera"};
        Object[] obj = new Object[8];
        DefaultTableModel tabla = new DefaultTableModel(null,columna);
        List ls = daoe.mostrarEstudiante();
        try {
            for(int i=0;i<ls.size();i++){
                e=(Estudiante)ls.get(i);
                obj[0]=e.getCodigo();
                obj[1]=e.getNombre();
                obj[2]=e.getEdad();
                obj[3]=e.getGenero();
                obj[4]=e.getCum();
                obj[5]=e.getIntereses();
                obj[6]=e.getNombreFacultad();
                obj[7]=e.getNombreCarrera();
                tabla.addRow(obj);
            }
            ls = daoe.mostrarEstudiante();
            this.jTable1.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla "+e.getMessage());
        }
    }
    
    public void comboFacultad(){
        this.cmbFacultad.removeAllItems();
        ArrayList ls = daoe.comboFacultad();
        try {
            for(int i=0;i<ls.size();i++){
                this.cmbFacultad.addItem(ls.get(i).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar comboFacultad "+e.getMessage());
        }
    }
    
    public void comboCarrera(){
        this.cmbCarrera.removeAllItems();
        ArrayList ls = daoe.comboCarrera(this.cmbFacultad.getSelectedItem().toString());
        try {
            for(int i=0;i<ls.size();i++){
                this.cmbCarrera.addItem(ls.get(i).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar comboCarrera "+e.getMessage());
        }
    }
    
    public void rol(){
        if(FrmPrincipal.tipoUser==2){
            this.btnEliminarLogico.setEnabled(false);
            this.btnModificar.setEnabled(false);
        }else{
            this.btnEliminarLogico.setEnabled(true);
            this.btnModificar.setEnabled(true);
        }   
    }
    
    public void limpiar(){
        this.txtCodigo.setText("");
        this.txtCum.setText("");
        this.txtNombre.setText("");
        this.sEdad.setValue(18);
        this.chBailar.setSelected(false);
        this.chComer.setSelected(false);
        this.chLeer.setSelected(false);
        this.chMusica.setSelected(false);
        this.buttonGroup1.clearSelection();
        this.cmbFacultad.setSelectedIndex(0);
        this.cmbCarrera.setSelectedIndex(0);
    }
    
    public void insertar(){
        try {
            //e.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            e.setCum(Double.parseDouble(this.txtCum.getText()));
            e.setEdad(Integer.parseInt(this.sEdad.getValue().toString()));
            e.setNombre(this.txtNombre.getText());
            if(this.rFem.isSelected()){
                e.setGenero("femenino");
            }
            if(this.rMas.isSelected()){
                e.setGenero("masculino");
            }
            e.setNombreCarrera(this.cmbCarrera.getSelectedItem().toString());
            e.setNombreFacultad(this.cmbFacultad.getSelectedItem().toString());
            String acum ="";
            if(this.chBailar.isSelected()){
                acum+= " bailar, ";
            }else if(this.chComer.isSelected()){
                acum+= "comer ,";
            }else if(this.chLeer.isSelected()){
                acum+= "leer, ";
            }else if(this.chMusica.isSelected()){
                acum+= "musica ";
            }
            if(daoe.insertarEstudiante(e)){
                JOptionPane.showMessageDialog(null,"Ingresado correctamente ");
                tablaEstudiante();
                limpiar();
            }        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar el estudiante Formulario "+e.getLocalizedMessage());
        }
    }
    
    public void modificar(){
        try {
            e.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            e.setCum(Double.parseDouble(this.txtCum.getText()));
            e.setEdad(Integer.parseInt(this.sEdad.getValue().toString()));
            e.setNombre(this.txtNombre.getText());
            if(this.rFem.isSelected()){
                e.setGenero("femenino");
            }
            if(this.rMas.isSelected()){
                e.setGenero("masculino");
            }
            e.setNombreCarrera(this.cmbCarrera.getSelectedItem().toString());
            e.setNombreFacultad(this.cmbFacultad.getSelectedItem().toString());
            String acum ="";
            if(this.chBailar.isSelected()){
                acum+= " bailar, ";
            }else if(this.chComer.isSelected()){
                acum+= "comer ,";
            }else if(this.chLeer.isSelected()){
                acum+= "leer, ";
            }else if(this.chMusica.isSelected()){
                acum+= "musica ";
            }
            e.setIntereses(acum);
            int sino = JOptionPane.showInternalConfirmDialog(this, "Desea modicar", "Estudiante", JOptionPane.YES_NO_OPTION);
            if(sino == 0){
                if(daoe.modificarEstudiante(e)){
                    JOptionPane.showMessageDialog(null, "Modificado correctamente");
                    tablaEstudiante();
                    limpiar();
                }
            }else{
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar "+e.getMessage());
        }
    }
    
    public void eliminarFisico(){
        try {
            e.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            int sino = JOptionPane.showInternalConfirmDialog(this, "Desea eliminar", "Estudiante", JOptionPane.YES_NO_OPTION);
            if(sino == 0){
                if(daoe.eliminarFisicoEstudiante(e)){
                    JOptionPane.showMessageDialog(null, "Eliminado correctamente");
                    tablaEstudiante();
                    limpiar();
                }
            }else{
                limpiar();
            }
        } catch (Exception e) {
        }
    }
    
    public void eliminarLogico(){
        try {
            e.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            int sino = JOptionPane.showInternalConfirmDialog(this, "Desea eliminar", "Estudiante", JOptionPane.YES_NO_OPTION);
            if(sino == 0){
                if(daoe.eliminarLogicoEstudiante(e)){
                    JOptionPane.showMessageDialog(null, "Eliminado correctamente");
                    tablaEstudiante();
                    limpiar();
                }
            }else{
                limpiar();
            }
        } catch (Exception e) {
        }
    }
    
    public void llenarTabla(){
        int fila = this.jTable1.getSelectedRow();
        this.txtCodigo.setText(String.valueOf(this.jTable1.getValueAt(fila, 0)));
        this.txtNombre.setText(String.valueOf(this.jTable1.getValueAt(fila, 1)));
        this.sEdad.setValue(Integer.parseInt(String.valueOf(this.jTable1.getValueAt(fila, 2))));
        this.txtCum.setText(String.valueOf(this.jTable1.getValueAt(fila, 4)));
        if(String.valueOf(this.jTable1.getValueAt(fila, 3)).equals("masculino")){
            this.rMas.setSelected(true);
        }else{
            this.rFem.setSelected(true);
        }
        this.cmbCarrera.setSelectedItem(String.valueOf(this.jTable1.getValueAt(fila, 7)));
        this.cmbFacultad.setSelectedItem(String.valueOf(this.jTable1.getValueAt(fila, 6)));
        this.txtCum.setText(String.valueOf(this.jTable1.getValueAt(fila, 4)));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        sEdad = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        rMas = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        rFem = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        chMusica = new javax.swing.JCheckBox();
        chComer = new javax.swing.JCheckBox();
        chBailar = new javax.swing.JCheckBox();
        chLeer = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        cmbFacultad = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbCarrera = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminarLogico = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtCum = new javax.swing.JTextField();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 112, 192));
        jLabel1.setText("Estudiante");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Codigo");

        txtCodigo.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nombre");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Edad");

        sEdad.setModel(new javax.swing.SpinnerNumberModel(18, 18, 75, 1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("CUM");

        buttonGroup1.add(rMas);
        rMas.setText("masculino");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Genero");

        buttonGroup1.add(rFem);
        rFem.setText("femenino");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Intereses");

        chMusica.setText("musica");

        chComer.setText("comer");

        chBailar.setText("bailar");

        chLeer.setText("leer");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Facultad");

        cmbFacultad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFacultadItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Carrera");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });

        btnInsertar.setText("Insertar");
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnEliminarLogico.setText("Eliminar ");
        btnEliminarLogico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarLogicoMouseClicked(evt);
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

        txtCum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCumKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(134, 134, 134)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rMas)
                        .addGap(2, 2, 2)
                        .addComponent(rFem)
                        .addGap(18, 18, 18)
                        .addComponent(chMusica)
                        .addGap(4, 4, 4)
                        .addComponent(chComer)
                        .addGap(0, 0, 0)
                        .addComponent(chBailar)
                        .addGap(18, 18, 18)
                        .addComponent(chLeer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(198, 198, 198)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(123, 123, 123)
                                    .addComponent(jLabel3)
                                    .addGap(105, 105, 105)
                                    .addComponent(jLabel4)
                                    .addGap(10, 10, 10))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtCum)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(128, 128, 128))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(113, 113, 113)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEliminarLogico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rMas)
                    .addComponent(rFem)
                    .addComponent(chMusica)
                    .addComponent(chComer)
                    .addComponent(chBailar)
                    .addComponent(chLeer))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnEliminarLogico)
                    .addComponent(btnModificar)
                    .addComponent(btnInsertar))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbFacultadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFacultadItemStateChanged
        comboCarrera();
    }//GEN-LAST:event_cmbFacultadItemStateChanged

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        insertar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarLogicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarLogicoMouseClicked
        eliminarLogico();
    }//GEN-LAST:event_btnEliminarLogicoMouseClicked

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        limpiar();
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        llenarTabla();
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        Character s = evt.getKeyChar();
        if(!s.isLetter(s) && s != KeyEvent.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtCumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCumKeyTyped
        Character s = evt.getKeyChar();
        if(!Character.isDigit(s) && s!='.'){
            evt.consume();
        }
        if(s=='.' && txtCum.getText().contains(".")){
            evt.consume();
        }
    }//GEN-LAST:event_txtCumKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarLogico;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chBailar;
    private javax.swing.JCheckBox chComer;
    private javax.swing.JCheckBox chLeer;
    private javax.swing.JCheckBox chMusica;
    private javax.swing.JComboBox<String> cmbCarrera;
    private javax.swing.JComboBox<String> cmbFacultad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rFem;
    private javax.swing.JRadioButton rMas;
    private javax.swing.JSpinner sEdad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCum;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
