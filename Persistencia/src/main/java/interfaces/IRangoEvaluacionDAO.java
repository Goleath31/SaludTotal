/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.RangoEvaluacionEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author rafaelgb
 */
public interface IRangoEvaluacionDAO {

    RangoEvaluacionEntidad guardar(RangoEvaluacionEntidad nuevoRango) throws PersistenciaException;

    List<RangoEvaluacionEntidad> buscarPorParametro(Long idParametro) throws PersistenciaException;

    /**
     * Da de baja lógica el rango, registrando la fecha de eliminación en vez
     * de borrarlo físicamente (para no afectar reportes históricos que lo
     * referencian vía la relación con su Parametro).
     */
    RangoEvaluacionEntidad eliminar(RangoEvaluacionEntidad rango) throws PersistenciaException;
}
