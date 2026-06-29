/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;

/**
 * Interfaz que define el contrato para obtener la conexión a la base de datos.
 *
 * @author golea
 */
public interface IConexionBD {

    /**
     * Obtiene una instancia de EntityManager para realizar operaciones de
     * persistencia.
     *
     * @return Objeto EntityManager configurado para la unidad de persistencia.
     */
    public EntityManager getEntityManager();
}
