/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * DTO con los datos de un rango de referencia capturado en frmRango.
 * "orden" y "etiqueta" no existen como columnas en la base de datos:
 * se calculan aquí únicamente para mostrarlos en la tabla de frmParametro.
 *
 * @author rafaelgb
 */
public class RangoDTO {

    private Long id;
    private int orden;
    private double rangoInicial;
    private double rangoFinal;
    private int edadInicial;
    private int edadFinal;
    private String sexo;

    public RangoDTO() {
    }

    public RangoDTO(int orden, double rangoInicial, double rangoFinal, int edadInicial, int edadFinal, String sexo) {
        this.orden = orden;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public double getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(double rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public double getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(double rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public int getEdadInicial() {
        return edadInicial;
    }

    public void setEdadInicial(int edadInicial) {
        this.edadInicial = edadInicial;
    }

    public int getEdadFinal() {
        return edadFinal;
    }

    public void setEdadFinal(int edadFinal) {
        this.edadFinal = edadFinal;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRangoFormateado() {
        return rangoInicial + " - " + rangoFinal;
    }

    public String getEtiqueta() {
        return edadInicial + "-" + edadFinal + " años, " + sexo;
    }
}
