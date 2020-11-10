
package com.modelo;

/**
 *Nombre de la Clase: Conexion
 * Fecha: 18-08-19
 * Version:1.0
 * Copyright: ITCA-FEPADE
 * @author Francisco Hernandez
 */
public class Usuarios {
    
     private int id;
    private String usuario;
    private String password;
    private String nombre;
    private String email;
    private int borrado;
    private int idTipo;

    public Usuarios() {
    }
    
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    
    
}
