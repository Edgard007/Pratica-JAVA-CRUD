
package com.dao;

import com.conexion.Conexion;
import com.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *Nombre de la Clase: DaoUsuario
 * Fecha: 18-08-19
 * Version:1.0
 * Copyright: ITCA-FEPADE
 * @author Francisco Hernandez
 */
public class DaoUsuario extends Conexion{
    
    public boolean registrar(Usuarios usr) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO usuarios (usuario, password, nombre, correo, id_tipo) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getEmail());
            ps.setInt(5, usr.getIdTipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean login(Usuarios usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT id, usuario, password, nombre, id_tipo FROM usuarios WHERE usuario = ? LIMIT 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getPassword().equals(rs.getString(3))) {
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setIdTipo(rs.getInt(5));
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public int existeUsuario(String usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        return mather.find();

    }
    
    
    public List<Usuarios> mostrarUsuario() throws Exception
    {
        ResultSet rs;
        List<Usuarios>lst=new ArrayList();
        
        try 
        {
         getConexion();
         String sql="select usuarios.id,usuarios.usuario,usuarios.password,usuarios.nombre,usuarios.correo,usuarios.borradoLogico,"
                 + "usuarios.id_tipo from usuarios JOIN tipo_usuario ON usuarios.id_tipo=tipo_usuario.id";
         PreparedStatement pre=this.getConexion().prepareStatement(sql);
         rs=pre.executeQuery();
         while(rs.next())
         {
             Usuarios user=new Usuarios();
             user.setId(rs.getInt("id"));
             user.setUsuario(rs.getString("usuario"));
             user.setNombre(rs.getString("nombre"));
             user.setEmail(rs.getString("correo"));
             user.setIdTipo(rs.getInt("id_tipo"));
             lst.add(user);
         }
            
        } catch (Exception e) 
        {
            throw e;
        }
        finally
        {
            this.desconectar();
        }
        
        return lst;
    
    }
    
    
    
    public String modificarUsuario(Usuarios user)
    {
        try 
        {
            getConexion();
            String sql="update usuarios set usuario=?,nombre=?,correo=?,borradoLogico=?,id_tipo=? "
                    + "where id=?;";
            PreparedStatement pre=this.getConexion().prepareStatement(sql);
            pre.setString(1,user.getUsuario());
            pre.setString(2,user.getNombre());
            pre.setString(3,user.getEmail());
            pre.setInt(4,user.getBorrado());
            pre.setInt(5,user.getIdTipo());
            pre.setInt(6,user.getId());
            pre.executeUpdate();
            
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al Modificar "+e);
        }
        finally
        {
            this.desconectar();
        }
        
        return "Modifcado Correctamente";
        
    }
    
    
     public String eliminarUsuario(Usuarios user)
    {
        try 
        {
            getConexion();
            String sql="delete from usuarios where id=?;";
            PreparedStatement pre=this.getConexion().prepareStatement(sql);
            pre.setInt(1,user.getId());
            pre.executeUpdate();
            
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"Error al Eliminar "+e);
        }
        finally
        {
            this.desconectar();
        }
        
        return "Eliminado Correctamente";
        
    }
    
    
    
}
