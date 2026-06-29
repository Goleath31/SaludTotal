/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.AnalisisEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * AnalisisEntidad. Proporciona métodos para consultar información sobre los
 * análisis clínicos en el sistema.
 *
 * * @author golea
 */
public interface IAnalisisDAO {

    /**
     * Consulta los análisis asociados a una prueba específica.
     *
     * * @param idPrueba El identificador único de la prueba cuya lista de
     * análisis se desea obtener.
     * @return Una lista de objetos AnalisisEntidad relacionados con la prueba.
     * @throws PersistenciaException Si ocurre un error durante la comunicación
     * con la base de datos.
     */
    List<AnalisisEntidad> consultarAnalisisPorPrueba(Long idPrueba) throws PersistenciaException;
}
