/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author golea
 */
public class PruebaDetalleDTO {

    private String folio;
    private String nombrePaciente;
    private String idPaciente;
    private String sexo;
    private String nombreMedico;
    private String fechaToma;
    private List<ResultadoDTO> resultados;

    public PruebaDetalleDTO(String folio, String nombrePaciente, String idPaciente,
            String sexo, String nombreMedico, String fechaToma) {
        this.folio = folio;
        this.nombrePaciente = nombrePaciente;
        this.idPaciente = idPaciente;
        this.sexo = sexo;
        this.nombreMedico = nombreMedico;
        this.fechaToma = fechaToma;
    }

    public PruebaDetalleDTO(String folio, String nombrePaciente, String idPaciente, String sexo, String nombreMedico, String fechaToma, List<ResultadoDTO> resultados) {
        this.folio = folio;
        this.nombrePaciente = nombrePaciente;
        this.idPaciente = idPaciente;
        this.sexo = sexo;
        this.nombreMedico = nombreMedico;
        this.fechaToma = fechaToma;
        this.resultados = resultados;
    }

    
    
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public String getSexo() {
        return sexo;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public String getFechaToma() {
        return fechaToma;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public List<ResultadoDTO> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadoDTO> resultados) {
        this.resultados = resultados;
    }

}
