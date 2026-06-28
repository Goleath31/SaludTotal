/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

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

    public PruebaDetalleDTO(String folio, String nombrePaciente, String idPaciente,
            String sexo, String nombreMedico, String fechaToma) {
        this.folio = folio;
        this.nombrePaciente = nombrePaciente;
        this.idPaciente = idPaciente;
        this.sexo = sexo;
        this.nombreMedico = nombreMedico;
        this.fechaToma = fechaToma;
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

}
