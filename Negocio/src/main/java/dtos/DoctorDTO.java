package dtos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author golea
 */
public class DoctorDTO {

    private Long id;
    private String nombreCompleto;

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

    @Override
    public String toString() {
        return nombreCompleto;
    }
}
