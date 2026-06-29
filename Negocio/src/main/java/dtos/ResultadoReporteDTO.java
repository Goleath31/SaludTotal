/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * DTO optimizado para la generación de reportes impresos o digitales. Incluye
 * los valores de referencia para contrastar el resultado obtenido.
 *
 * @author golea
 */
public class ResultadoReporteDTO {

    private String nombreParametro;
    private String valor;
    private String observacion;
    private String unidades;
    private String valorMin;
    private String valorMax;

    public ResultadoReporteDTO(String nombreParametro, String valor, String observacion, String unidades, String valorMin, String valorMax) {
        this.nombreParametro = nombreParametro;
        this.valor = valor;
        this.observacion = observacion;
        this.unidades = unidades;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
    }

    public String getValorMin() {
        return valorMin;
    }

    public void setValorMin(String valorMin) {
        this.valorMin = valorMin;
    }

    public String getValorMax() {
        return valorMax;
    }

    public void setValorMax(String valorMax) {
        this.valorMax = valorMax;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
