/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 * Excepción personalizada para manejar errores de lógica de negocio. Se utiliza
 * en la capa de servicios para reportar violaciones de reglas del sistema que
 * impiden continuar con el flujo de trabajo.
 *
 * @author golea
 */
public class NegocioException extends Exception {

    /**
     * Crea una nueva instancia de NegocioException con un mensaje descriptivo.
     *
     * @param mensaje El mensaje que explica el error de negocio ocurrido.
     */
    public NegocioException(String mensaje) {
        super(mensaje);
    }
}
