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
 * AnalisisEntidad: consulta de análisis asociados a una prueba (reportes), y
 * administración del catálogo de análisis (alta, baja lógica, búsqueda y
 * paginación).
 *
 * @author golea
 * @author rafaelgb
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

    AnalisisEntidad guardar(AnalisisEntidad nuevoAnalisis) throws PersistenciaException;

    /**
     * Da de baja lógica el análisis (y en cascada sus parámetros y rangos),
     * registrando la fecha de eliminación en vez de borrar físicamente el
     * registro, para no perder el historial de resultados que lo referencian.
     */
    AnalisisEntidad eliminar(AnalisisEntidad analisis) throws PersistenciaException;

    AnalisisEntidad buscarPorNombre(String nombre) throws PersistenciaException;

    AnalisisEntidad buscarPorId(Long id) throws PersistenciaException;

    List<AnalisisEntidad> consultarTodos(int pagina, int tamanoPagina) throws PersistenciaException;

    long contarTodos() throws PersistenciaException;

    List<AnalisisEntidad> buscarPorFiltro(String texto) throws PersistenciaException;
}
