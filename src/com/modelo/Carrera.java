
package com.modelo;

/**
 * Nombre de la clase: Facultad
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class Carrera {
    private int codigo;
    private String nombre;
    private int cantidad;
    private String nombreFacultdad;

    public Carrera() {
    }

    public Carrera(int codigo, String nombre, int cantidad, String nombreFacultdad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.nombreFacultdad = nombreFacultdad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreFacultdad() {
        return nombreFacultdad;
    }

    public void setNombreFacultdad(String nombreFacultdad) {
        this.nombreFacultdad = nombreFacultdad;
    }

       
}
