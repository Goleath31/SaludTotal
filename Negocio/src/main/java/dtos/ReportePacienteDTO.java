/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 * DTO que consolida toda la información de un reporte clínico para un paciente.
 * Agrupa los datos del cliente, médico, análisis y la lista detallada de
 * resultados.
 *
 * @author golea
 */
public class ReportePacienteDTO {

    private String folio;
    private String nombrePaciente;
    private String sexo;
    private String nombreMedico;
    private String fechaToma;
    private List<ResultadoReporteDTO> resultados;
    private String nombreAnalisis;

    public ReportePacienteDTO(String folio, String nombrePaciente, String sexo, String nombreMedico, String fechaToma, String nombreAnalisis, List<ResultadoReporteDTO> resultados) {
        this.folio = folio;
        this.nombrePaciente = nombrePaciente;
        this.sexo = sexo;
        this.nombreMedico = nombreMedico;
        this.fechaToma = fechaToma;
        this.nombreAnalisis = nombreAnalisis;
        this.resultados = resultados;
    }

    public String getNombreAnalisis() {
        return nombreAnalisis;
    }

    public void setNombreAnalisis(String nombreAnalisis) {
        this.nombreAnalisis = nombreAnalisis;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(String fechaToma) {
        this.fechaToma = fechaToma;
    }

    public List<ResultadoReporteDTO> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadoReporteDTO> resultados) {
        this.resultados = resultados;
    }

}
