/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author golea
 */
public class ParametroDTO {

    private String nombre;
    private double rangoMinimo;
    private double rangoMaximo;
    private String unidad;

    public ParametroDTO(String nombre, double rangoMinimo, double rangoMaximo, String unidad) {
        this.nombre = nombre;
        this.rangoMinimo = rangoMinimo;
        this.rangoMaximo = rangoMaximo;
        this.unidad = unidad;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getRangoFormateado() {
        return rangoMinimo + " - " + rangoMaximo;
    }

    public String getUnidad() {
        return unidad;
    }
}
