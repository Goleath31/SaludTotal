/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.ParametroEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * ParametroEntidad. Proporciona métodos para consultar los parámetros
 * relacionados con análisis clínicos.
 *
 * @author golea
 */
public interface IParametroDAO {

    /**
     * Consulta los parámetros asociados a un análisis médico específico.
     *
     * @param idAnalisis El identificador único del análisis del cual se desean
     * obtener sus parámetros.
     * @return Una lista de objetos ParametroEntidad vinculados al análisis.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta
     * en la base de datos.
     */
    List<ParametroEntidad> consultarPorAnalisis(Long idAnalisis) throws PersistenciaException;
}
