/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.DoctorEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad DoctorEntidad.
 * Proporciona métodos para la obtención de registros de doctores.
 * @author golea
 */
public interface IDoctorDAO {
    
    /**
     * Consulta y retorna todos los doctores registrados en el sistema.
     * @return Una lista de objetos DoctorEntidad.
     * @throws PersistenciaException Si ocurre un error al intentar acceder a la base de datos.
     */
    List<DoctorEntidad> consultarTodos() throws PersistenciaException;
}
