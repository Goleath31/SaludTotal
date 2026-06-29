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

/**
 * Constructor vacío estándar.
 */
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

    /**
     * Constructor para inicializar los datos básicos del cliente. Concatena
     * automáticamente los nombres y apellidos para la vista.
     *
     * * @param id Identificador único del cliente.
     * @param nombre Nombre de pila.
     * @param apellidoPaterno Apellido paterno.
     * @param apellidoMaterno Apellido materno.
     * @param sexo Género del cliente.
     */
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
