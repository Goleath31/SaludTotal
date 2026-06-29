/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import entidades.ClienteEntidad;
import exception.PersistenciaException;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * ClienteEntidad. Proporciona métodos para la búsqueda y gestión de información
 * de clientes.
 *
 * @author golea
 */
public interface IClienteDAO {

    /**
     * Busca un cliente en la base de datos por su nombre.
     *
     * @param nombre El nombre del cliente que se desea consultar.
     * @return El objeto ClienteEntidad correspondiente al nombre proporcionado.
     * @throws PersistenciaException Si ocurre un error al acceder a la base de
     * datos o si no se puede realizar la consulta.
     */
    ClienteEntidad consultarPorNombre(String nombre) throws PersistenciaException;
}
