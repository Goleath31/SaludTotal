/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ClientePruebaCompletaDTO;
import entidades.PruebaEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * PruebaEntidad. Proporciona métodos para consultar pruebas asociadas a
 * médicos, buscar pruebas por folio y obtener reportes de clientes con pruebas
 * completadas.
 *
 * @author golea
 */
public interface IPruebaDAO {

    /**
     * Consulta las pruebas médicas asignadas a un doctor específico.
     *
     * @param idDoctor El identificador único del doctor.
     * @return Una lista de objetos PruebaEntidad asociados al doctor.
     * @throws PersistenciaException Si ocurre un error al acceder a la base de
     * datos.
     */
    List<PruebaEntidad> consultarPorDoctor(Long idDoctor) throws PersistenciaException;

    /**
     * Busca una prueba médica única a partir de su folio.
     *
     * @param folio El identificador alfanumérico único de la prueba.
     * @return El objeto PruebaEntidad correspondiente al folio proporcionado.
     * @throws PersistenciaException Si la prueba no existe o hay un error de
     * conexión.
     */
    PruebaEntidad buscarPorFolio(String folio) throws PersistenciaException;

    /**
     * Obtiene una lista de clientes que han completado todas sus pruebas
     * médicas.
     *
     * @return Una lista de DTOs que contienen la información de los clientes y
     * sus pruebas completas.
     */
    List<ClientePruebaCompletaDTO> obtenerClientesConPruebasCompletas();

    PruebaEntidad guardar(PruebaEntidad nuevaPrueba) throws PersistenciaException;

    List<PruebaEntidad> consultarTodos(int pagina, int tamanoPagina) throws PersistenciaException;

    long contarTodos() throws PersistenciaException;

    List<PruebaEntidad> buscarPorFiltro(String texto) throws PersistenciaException;
}
