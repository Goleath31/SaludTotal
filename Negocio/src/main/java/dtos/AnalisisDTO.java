/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * DTO (Data Transfer Object) que representa la información básica de un análisis.
 * Se utiliza para transferir datos de análisis entre las capas de lógica y la interfaz de usuario.
 * @author golea
 */
public class AnalisisDTO {

    private Long id;
    private String nombre;

    /**
     * Constructor para inicializar el DTO de análisis.
     * @param id El identificador único del análisis.
     * @param nombre El nombre descriptivo del análisis clínico.
     */
    public AnalisisDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador del análisis.
     * @return El ID del análisis.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del análisis.
     * @return El nombre del análisis clínico.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Representación en cadena del objeto, útil para componentes de UI 
     * como ComboBox o Listas donde se muestra directamente el nombre.
     */
    @Override
    public String toString() {
        return this.nombre;
    }
}
