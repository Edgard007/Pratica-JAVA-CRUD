
package com.dao;

import com.conexion.Conexion;
import com.modelo.Facultad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoFacultad
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class DaoFacultad extends Conexion {
    
    public List<Facultad> mostrarFacultad(){
        ResultSet rs;
        List<Facultad> ls = new ArrayList();
        try {
            getConexion();
            String sql = "SELECT * FROM facultad WHERE borradoLogico=1";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            rs= pre.executeQuery();
            while(rs.next()){
                Facultad f = new Facultad();
                f.setCodigo(rs.getInt("codigoFacultad"));
                f.setNombre(rs.getString("nombre"));
                f.setTelefono(rs.getString("telefono"));
                ls.add(f);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar "+e.getMessage());
        }
        finally{
            desconectar();
        }
        return ls;
    }
    
    public boolean insertarFacultad(Facultad f) throws ClassNotFoundException{
        try {
            getConexion();
            String sql = "INSERT INTO facultad(nombre, telefono) VALUES(?,?)";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            pre.setString(1, f.getNombre());
            pre.setString(2, f.getTelefono());
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
    
    public boolean modificarFacultad(Facultad f) throws ClassNotFoundException{
        try {
            getConexion();
            String sql = "UPDATE facultad SET nombre=?, telefono=? WHERE codigoFacultad=?";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            pre.setString(1, f.getNombre());
            pre.setString(2, f.getTelefono());
            pre.setInt(3, f.getCodigo());
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
    
    public boolean eliminarFacultad(Facultad f) throws ClassNotFoundException{
        try {
            getConexion();
            String sql = "UPDATE facultad SET borradoLogico=2 WHERE codigoFacultad=?";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            pre.setInt(1, f.getCodigo());
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
