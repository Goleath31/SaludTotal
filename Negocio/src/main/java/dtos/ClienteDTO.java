/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author golea
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Formatea fechaNacimiento para mostrarla en pantalla. No es una columna
     * de la base de datos, se calcula a partir de fechaNacimiento.
     */
    public String getFechaNacimientoFormateada() {
        if (fechaNacimiento == null) {
            return "";
        }
        return new SimpleDateFormat("d 'de' MMMM yyyy", new Locale("es", "ES")).format(fechaNacimiento);
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
