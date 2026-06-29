/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.ResultadoEntidad;
import exception.PersistenciaException;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * ResultadoEntidad. Proporciona métodos para registrar resultados de pruebas y
 * consultar resultados específicos.
 *
 * @author golea
 */
public interface IResultadoDAO {

    /**
     * Persiste un resultado clínico en la base de datos.
     *
     * @param resultado El objeto ResultadoEntidad que contiene los datos a
     * guardar.
     * @throws PersistenciaException Si ocurre un error de comunicación o
     * durante la transacción en la base de datos.
     */
    void guardar(ResultadoEntidad resultado) throws PersistenciaException;

    /**
     * Busca un resultado específico asociado a una prueba y un parámetro
     * determinados.
     *
     * @param idPrueba El identificador único de la prueba médica.
     * @param idParametro El identificador único del parámetro médico analizado.
     * @return El objeto ResultadoEntidad correspondiente a los IDs
     * proporcionados, o null si no se encuentra.
     */
    ResultadoEntidad buscarPorPruebayParametro(Long idPrueba, Long idParametro);
}
