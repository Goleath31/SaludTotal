/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO (Data Transfer Object) que representa la información de un análisis.
 * Se utiliza para transferir datos de análisis entre las capas de lógica y la
 * interfaz de usuario, tanto en el flujo de reportes/resultados (id, nombre)
 * como en el de administración del catálogo (notaDescriptiva, muestraId,
 * nombreMuestra, parametros). El "código" (ej. AN-001) no existe en la base
 * de datos: se genera aquí a partir del id solo para mostrarlo en pantalla.
 *
 * @author golea
 * @author rafaelgb
 */
public class AnalisisDTO {

    private Long id;
    private String nombre;
    private String notaDescriptiva;
    private Long muestraId;
    private String nombreMuestra;
    private List<ParametroDTO> parametros = new ArrayList<>();

    public AnalisisDTO() {
    }

    /**
     * Constructor para inicializar el DTO de análisis.
     * @param id El identificador único del análisis.
     * @param nombre El nombre descriptivo del análisis clínico.
     */
    public AnalisisDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public AnalisisDTO(Long id, String nombre, String notaDescriptiva, String nombreMuestra) {
        this.id = id;
        this.nombre = nombre;
        this.notaDescriptiva = notaDescriptiva;
        this.nombreMuestra = nombreMuestra;
    }

    /**
     * Obtiene el identificador del análisis.
     * @return El ID del análisis.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del análisis.
     * @return El nombre del análisis clínico.
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotaDescriptiva() {
        return notaDescriptiva;
    }

    public void setNotaDescriptiva(String notaDescriptiva) {
        this.notaDescriptiva = notaDescriptiva;
    }

    public Long getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Long muestraId) {
        this.muestraId = muestraId;
    }

    public String getNombreMuestra() {
        return nombreMuestra;
    }

    public void setNombreMuestra(String nombreMuestra) {
        this.nombreMuestra = nombreMuestra;
    }

    public List<ParametroDTO> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroDTO> parametros) {
        this.parametros = parametros;
    }

    public String getCodigo() {
        return "AN-" + (id != null ? String.format("%03d", id) : "NEW");
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
