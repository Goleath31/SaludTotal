/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author golea
 */
import java.util.Date;

public class ClienteDTO {

    private Long id;
    private String nombreCompleto;
    private String sexo;
    private String tipoSangre;
    private Date fechaNacimiento;
    private String nombreMedico;
    private String fechaToma;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo) {
        this.id = id;
        this.nombreCompleto = nombre + " " + apellidoPaterno + " " + apellidoMaterno;
        this.sexo = sexo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getSexo() {
        return sexo;
    }

    public Long getId() {
        return id;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
    
    
}
