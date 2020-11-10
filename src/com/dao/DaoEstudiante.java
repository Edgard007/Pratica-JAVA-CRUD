
package com.dao;

import com.conexion.Conexion;
import com.modelo.Estudiante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoEstudiante
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class DaoEstudiante extends Conexion{
    
    public List<Estudiante> mostrarEstudiante() throws ClassNotFoundException{
        ResultSet rs;
         List<Estudiante> ls = new ArrayList();
        try {
            getConexion();
            String sql = "SELECT estudiante.codigoEstudiante, estudiante.nombre, "
                    + "estudiante.edad, estudiante.genero, estudiante.cum, "
                    + "estudiante.intereses, carrera.nombre AS carrera, facultad.nombre AS facultad "
                    + " FROM estudiante JOIN carrera "
                    + " ON estudiante.codigoCarrera = carrera.codigoCarrera "
                    + " JOIN facultad ON carrera.codigoFacultad = facultad.codigoFacultad "
                    + "WHERE estudiante.borradoLogico=1";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            rs= pre.executeQuery();
            while(rs.next()){
                Estudiante e = new Estudiante();
                e.setCodigo(rs.getInt("codigoEstudiante"));
                e.setNombre(rs.getString("nombre"));
                e.setEdad(rs.getInt("edad"));
                e.setGenero(rs.getString("genero"));
                e.setCum(rs.getDouble("cum"));
                e.setIntereses(rs.getString("intereses"));
                e.setNombreCarrera(rs.getString("carrera"));
               e.setNombreFacultad(rs.getString("facultad"));
               ls.add(e);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en mostrarEstudiante "+e.getMessage());
        }
        finally{
            desconectar();
        }
        return ls;
    }
    
    public int sacarCodigoFacultad(String nombre) throws ClassNotFoundException{
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
    
    public int sacarCodigoCarrera(String nombre) throws ClassNotFoundException{
        int id = 0;
        ResultSet rs;
        try {
            getConexion();
            String sql = "SELECT codigoCarrera FROM carrera WHERE nombre=?";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            pre.setString(1, nombre);
            rs= pre.executeQuery();
            while(rs.next()){
                id= rs.getInt("codigoCarrera");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener codigo "+e.getMessage());
        }
        finally{
            desconectar();
        }
        
        return id;
    }
    
    public  ArrayList<String> comboFacultad(){
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
    
    public  ArrayList<String> comboCarrera(String facultad){
        ArrayList<String> ls = new ArrayList<String>();
        ResultSet rs;
        try {
            String sql = "SELECT nombre FROM carrera WHERE codigoFacultad=?";
            getConexion();
            PreparedStatement pre = getConexion().prepareStatement(sql);
            pre.setInt(1, sacarCodigoFacultad(facultad));
            rs= pre.executeQuery();
            while(rs.next()){
                ls.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
        }
        return ls;
    }
    
    public boolean insertarEstudiante(Estudiante e) throws ClassNotFoundException{
        try {
            getConexion();
            String sql="INSERT INTO estudiante(nombre, edad, genero, cum, intereses, codigoCarrera,borradoLogico) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pre = getConexion().prepareStatement(sql);
            pre.setString(1, e.getNombre());
            pre.setInt(2, e.getEdad());
            pre.setString(3, e.getGenero());
            pre.setDouble(4, e.getCum());
            pre.setString(5, e.getIntereses());
            pre.setInt(6, sacarCodigoCarrera(e.getNombreCarrera()));
            pre.setInt(7, 1);
            pre.executeUpdate();
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al insertar el estudiante  en dao "+ex.toString());
            return false;
        }finally{
            desconectar();
        }   
    }
    
    public boolean modificarEstudiante(Estudiante e) throws ClassNotFoundException{
        try {
            getConexion();
            String sql="UPDATE estudiante SET nombre=?, edad=?, genero=?, cum=?, intereses=?, codigoCarrera=? where codigoEstudiante=?";
            PreparedStatement pre = getConexion().prepareStatement(sql);
            pre.setString(1, e.getNombre());
            pre.setInt(2, e.getEdad());
            pre.setString(3, e.getGenero());
            pre.setDouble(4, e.getCum());
            pre.setString(5, e.getIntereses());
            pre.setInt(6, sacarCodigoCarrera(e.getNombreCarrera()));
            pre.setInt(7, e.getCodigo());
            pre.executeUpdate();
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al modificar el estudiante "+ex.getMessage());
            return false;
        }finally{
            desconectar();
        }   
    }
    
    public boolean eliminarFisicoEstudiante(Estudiante e) throws ClassNotFoundException{
        try {
            getConexion();
            String sql="DELETE FROM estudiante WHERE codigoEstudiante=? ";
            PreparedStatement pre = getConexion().prepareStatement(sql);
            pre.setInt(1, e.getCodigo());
            pre.executeUpdate();
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al eliminar el estudiante "+ex.getMessage());
            return false;
        }finally{
            desconectar();
        }   
    }
    
    public boolean eliminarLogicoEstudiante(Estudiante e) throws ClassNotFoundException{
        try {
            getConexion();
            int b= 2;
            String sql="UPDATE estudiante SET borradoLogico=? WHERE codigoEstudiante=?";
            PreparedStatement pre = getConexion().prepareStatement(sql);
            pre.setInt(1, b);
            pre.setInt(2, e.getCodigo());
            pre.executeUpdate();
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al eliminar el estudiante "+ex.getMessage());
            return false;
        }finally{
            desconectar();
        }   
    }
}
