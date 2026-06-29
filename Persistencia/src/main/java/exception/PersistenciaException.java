/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 * Excepción personalizada para manejar errores relacionados con la capa de persistencia.
 * Esta excepción es lanzada cuando ocurre un problema al interactuar con la base de datos.
 * @author golea
 */
public class PersistenciaException extends Exception {

    /**
     * Crea una nueva instancia de PersistenciaException con un mensaje descriptivo.
     * @param mensaje El mensaje que explica la causa del error.
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }
}
