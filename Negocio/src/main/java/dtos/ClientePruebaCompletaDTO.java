/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author golea
 */
public class ClientePruebaCompletaDTO {

    private String folio;
    private String nombreCliente;

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
