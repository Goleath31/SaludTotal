/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * "Solicitud" en pantalla es la entidad Prueba. Pedir un análisis significa
 * crear, por cada Parametro de ese análisis, un Resultado sin valor todavía
 * (pendiente de captura en el módulo de Resultados).
 *
 * @author rafaelgb
 */
public class SolicitudDTO {

    private Long id;
    private String folio;
    private String nombreAnalisisConcatenado;
    private String fechaIngresoFormateada;
    private Long clienteId;
    private Long doctorId;
    private List<Long> analisisIds = new ArrayList<>();

    public SolicitudDTO() {
    }

    public SolicitudDTO(Long id, String folio, String nombreAnalisisConcatenado, String fechaIngresoFormateada) {
        this.id = id;
        this.folio = folio;
        this.nombreAnalisisConcatenado = nombreAnalisisConcatenado;
        this.fechaIngresoFormateada = fechaIngresoFormateada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNombreAnalisisConcatenado() {
        return nombreAnalisisConcatenado;
    }

    public void setNombreAnalisisConcatenado(String nombreAnalisisConcatenado) {
        this.nombreAnalisisConcatenado = nombreAnalisisConcatenado;
    }

    public String getFechaIngresoFormateada() {
        return fechaIngresoFormateada;
    }

    public void setFechaIngresoFormateada(String fechaIngresoFormateada) {
        this.fechaIngresoFormateada = fechaIngresoFormateada;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public List<Long> getAnalisisIds() {
        return analisisIds;
    }

    public void setAnalisisIds(List<Long> analisisIds) {
        this.analisisIds = analisisIds;
    }
}
