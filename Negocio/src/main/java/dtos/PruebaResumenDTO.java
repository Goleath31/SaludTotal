/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * DTO para resúmenes de pruebas. Optimiza la carga de datos en listas al
 * excluir información técnica detallada que no es necesaria en una vista de
 * catálogo.
 *
 * @author golea
 */
public class PruebaResumenDTO {

    private String folio;
    private String nombreCliente;

    public PruebaResumenDTO(String folio, String nombreCliente) {
        this.folio = folio;
        this.nombreCliente = nombreCliente;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Sobrescribe toString para facilitar la visualización rápida en
     * componentes de UI como JLists o ComboBoxes.
     */
    @Override
    public String toString() {
        return "Folio: " + folio + " | Cliente: " + nombreCliente;
    }
}
