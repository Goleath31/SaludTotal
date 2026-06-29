package dtos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * DTO que representa la información de un médico para ser presentada en la
 * interfaz. Consolida el nombre completo para facilitar el llenado de
 * componentes de UI.
 *
 * @author golea
 */
public class DoctorDTO {

    private Long id;
    private String nombreCompleto;

    /**
     * Constructor para inicializar los datos del médico. Concatena el nombre y
     * apellidos para una presentación unificada.
     *
     * @param id Identificador único del médico.
     * @param nombre Nombre de pila.
     * @param apPaterno Apellido paterno.
     * @param apMaterno Apellido materno.
     */
    public DoctorDTO(Long id, String nombre, String apPaterno, String apMaterno) {
        this.id = id;
        this.nombreCompleto = nombre + " " + apPaterno + " " + apMaterno;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sobrescribe toString para que componentes como ComboBox muestren el
     * nombre del médico directamente.
     */
    @Override
    public String toString() {
        return nombreCompleto;
    }
}
