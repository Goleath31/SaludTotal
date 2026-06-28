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

    private Long id;
    private String nombre;
    private double rangoMinimo;
    private double rangoMaximo;
    private String unidad;
    private String valorIngresado;

    public ParametroDTO(String nombre, double rangoMinimo, double rangoMaximo, String unidad) {
        this.nombre = nombre;
        this.rangoMinimo = rangoMinimo;
        this.rangoMaximo = rangoMaximo;
        this.unidad = unidad;

    }

    public ParametroDTO(Long id, String nombre, double rangoMinimo, double rangoMaximo, String unidad, String valorIngresado) {
        this.id = id;
        this.nombre = nombre;
        this.rangoMinimo = rangoMinimo;
        this.rangoMaximo = rangoMaximo;
        this.unidad = unidad;
        this.valorIngresado = valorIngresado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getRangoMinimo() {
        return rangoMinimo;
    }

    public void setRangoMinimo(double rangoMinimo) {
        this.rangoMinimo = rangoMinimo;
    }

    public double getRangoMaximo() {
        return rangoMaximo;
    }

    public void setRangoMaximo(double rangoMaximo) {
        this.rangoMaximo = rangoMaximo;
    }

    public String getValorIngresado() {
        return valorIngresado;
    }

    public void setValorIngresado(String valorIngresado) {
        this.valorIngresado = valorIngresado;
    }
    
}
