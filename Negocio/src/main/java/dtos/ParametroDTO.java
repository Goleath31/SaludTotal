/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO que representa un parámetro clínico. Se usa en dos contextos: para
 * comparar un valor capturado contra su rango de referencia (rangoMinimo,
 * rangoMaximo, unidad, valorIngresado), y para administrar el catálogo de
 * parámetros de un análisis (orden, notaDescriptiva, unidadMedidaId,
 * rangos). El "código" (ej. PAR-GLU-001) no existe en la base de datos: se
 * genera aquí a partir del id y el nombre solo para mostrarlo en pantalla.
 *
 * @author golea
 * @author rafaelgb
 */
public class ParametroDTO {

    private Long id;
    private String nombre;
    private double rangoMinimo;
    private double rangoMaximo;
    private String unidad;
    private String valorIngresado;

    private int orden;
    private String notaDescriptiva;
    private Long unidadMedidaId;
    private String nombreUnidadMedida;
    private List<RangoDTO> rangos = new ArrayList<>();

    public ParametroDTO() {
    }

    // Constructor para inicialización básica sin resultados
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

    public ParametroDTO(String nombre, int orden, String notaDescriptiva, Long unidadMedidaId, String nombreUnidadMedida) {
        this.nombre = nombre;
        this.orden = orden;
        this.notaDescriptiva = notaDescriptiva;
        this.unidadMedidaId = unidadMedidaId;
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el rango de referencia en un formato legible.
     *
     * @return String con formato "min - max".
     */
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getNotaDescriptiva() {
        return notaDescriptiva;
    }

    public void setNotaDescriptiva(String notaDescriptiva) {
        this.notaDescriptiva = notaDescriptiva;
    }

    public Long getUnidadMedidaId() {
        return unidadMedidaId;
    }

    public void setUnidadMedidaId(Long unidadMedidaId) {
        this.unidadMedidaId = unidadMedidaId;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }

    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    public List<RangoDTO> getRangos() {
        return rangos;
    }

    public void setRangos(List<RangoDTO> rangos) {
        this.rangos = rangos;
    }

    public String getCodigo() {
        String prefijo = nombre == null || nombre.isBlank()
                ? "PAR"
                : nombre.trim().substring(0, Math.min(3, nombre.trim().length())).toUpperCase();
        String sufijo = id != null ? String.format("%03d", id) : "NEW";
        return "PAR-" + prefijo + "-" + sufijo;
    }

    public String getRangosResumen() {
        if (rangos == null || rangos.isEmpty()) {
            return "Sin rangos";
        }
        RangoDTO primero = rangos.get(0);
        return primero.getRangoFormateado() + (rangos.size() > 1 ? " (+" + (rangos.size() - 1) + ")" : "");
    }

}
