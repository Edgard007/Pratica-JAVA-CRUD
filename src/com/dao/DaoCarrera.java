
package com.dao;

import com.conexion.Conexion;
import com.modelo.Carrera;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoCarrera
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class DaoCarrera extends Conexion {
    
    public List<Carrera> mostrarCarrera() throws ClassNotFoundException{
        ResultSet rs;
        List<Carrera> ls = new ArrayList();
        try {
            getConexion();
            String sql =" SELECT carrera.codigoCarrera, carrera.nombre, carrera.contidadMateria, "
                    + " facultad.nombre as facultad from carrera JOIN facultad  "
                    + " ON carrera.codigoFacultad=facultad.codigoFacultad WHERE carrera.borradoLogico=1";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                Carrera c = new Carrera();
                c.setCodigo(rs.getInt("codigoCarrera"));
                c.setNombre(rs.getString("nombre"));
                c.setCantidad(rs.getInt("contidadMateria"));
                c.setNombreFacultdad(rs.getString("facultad"));
                ls.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar  carrera "+e.getMessage());
        }
        finally{
            desconectar();
        }
        return ls;
    }
    
    /*public List mostarCombo() throws ClassNotFoundException{
        ResultSet rs;
        List ls = null;
        try {
            conectar();
            String sql = "SELECT nombre from facultad";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                Object [] obj = new Object[2];
                obj[0]=rs.getString("nombre");
                ls.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar combobox "+e.getMessage());
        }
        finally{
            desconectar();
        }
        return ls;
    }*/
    
    public  ArrayList<String> mostrarCombo(){
        ArrayList<String> ls = new ArrayList<String>();
        ResultSet rs;
        try {
            String sql = "SELECT nombre FROM facultad";
            getConexion();
            PreparedStatement pre = getConexion().prepareStatement(sql);
            rs= pre.executeQuery();
            while(rs.next()){
                ls.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
        }
        return ls;
    }
    
    public int sacarCodigo(String nombre) throws ClassNotFoundException{
        int id = 0;
        ResultSet rs;
        try {
            getConexion();
            String sql = "SELECT codigoFacultad FROM facultad WHERE nombre=?";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            pre.setString(1, nombre);
            rs= pre.executeQuery();
            while(rs.next()){
                id= rs.getInt("codigoFacultad");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener codigo "+e.getMessage());
        }
        finally{
            desconectar();
        }
        
        return id;
    }
    
    public boolean insertarCarrera(Carrera c) throws ClassNotFoundException{
        try {
            getConexion();
            String sql ="INSERT INTO `carrera`(`nombre`, `contidadMateria`, `codigoFacultad`) VALUES (?,?,?)";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            pre.setString(1, c.getNombre());
            pre.setInt(2, c.getCantidad());
            pre.setInt(3, this.sacarCodigo(c.getNombreFacultdad()));
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar "+e.getMessage());
            return false;
        }
        finally{
            desconectar();
        }
    }
    
    public boolean modificarCarrera(Carrera c) throws ClassNotFoundException{
        try {
            getConexion();
            String sql ="UPDATE carrera SET nombre=?, contidadMateria=?, codigoFacultad=? WHERE codigoCarrera=?";
            PreparedStatement pre = getConexion().prepareStatement(sql);
            pre.setInt(4, c.getCodigo());
            pre.setString(1, c.getNombre());
            pre.setInt(2, c.getCantidad());
            pre.setInt(3, this.sacarCodigo(c.getNombreFacultdad()));
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar "+e.getMessage());
            return false;
        }
        finally{
            desconectar();
        }
    }
    
    public boolean eliminarCarrera(Carrera c) throws ClassNotFoundException{
        try {
            getConexion();
            String sql ="UPDATE carrera SET borradoLogico=1 WHERE codigoCarrera=?";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            pre.setInt(1, c.getCodigo());
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar "+e.getMessage());
            return false;
        } 
        finally{
            desconectar();
        }
    }
}
