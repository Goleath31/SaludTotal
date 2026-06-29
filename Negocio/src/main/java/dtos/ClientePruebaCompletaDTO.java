/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * DTO utilizado para mostrar una relación rápida entre una prueba y su cliente.
 * Es ideal para tablas o listados de consulta donde no se requieren todos los
 * datos del cliente.
 *
 * @author golea
 */
public class ClientePruebaCompletaDTO {

    private String folio;
    private String nombreCliente;

    /**
     * Constructor para inicializar la relación entre folio y nombre.
     *
     * @param folio El folio de la prueba médica.
     * @param nombreCliente El nombre del cliente asociado a la prueba.
     */
    public ClientePruebaCompletaDTO(String folio, String nombreCliente) {
        this.folio = folio;
        this.nombreCliente = nombreCliente;
    }

    public String getFolio() {
        return folio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
}
