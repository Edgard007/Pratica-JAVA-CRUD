
package com.modelo;

/**
 * Nombre de la clase: Estudiante
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class Estudiante {
    private int codigo;
    private String nombre;
    private int edad;
    private String genero;
    private Double cum;
    private String intereses;
    private String nombreCarrera;
    private String nombreFacultad;

    public Estudiante() {
    }

    public Estudiante(int codigo, String nombre, int edad, String genero, Double cum, String intereses, String nombreCarrera, String nombreFacultad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.cum = cum;
        this.intereses = intereses;
        this.nombreCarrera = nombreCarrera;
        this.nombreFacultad = nombreFacultad;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getCum() {
        return cum;
    }

    public void setCum(Double cum) {
        this.cum = cum;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }    
}
