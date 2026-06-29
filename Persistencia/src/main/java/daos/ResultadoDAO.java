/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.ResultadoEntidad;
import exception.PersistenciaException;
import interfaces.IResultadoDAO;
import javax.persistence.EntityManager;

/**
 * Implementación de la interfaz IResultadoDAO que maneja las operaciones 
 * de persistencia para la entidad ResultadoEntidad[cite: 2].
 * @author golea
 */
public class ResultadoDAO implements IResultadoDAO {

    private IConexionBD conexion;

    /**
     * Constructor para inicializar el acceso a la base de datos[cite: 2].
     * @param conexion Objeto que proporciona el EntityManager[cite: 2].
     */
    public ResultadoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    
    /**
     * Guarda un nuevo resultado en la base de datos de manera transaccional[cite: 2].
     * @param resultado La entidad a persistir[cite: 2].
     * @throws PersistenciaException Si ocurre un error durante el proceso de guardado[cite: 2].
     */
    @Override
    public void guardar(ResultadoEntidad resultado) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(resultado);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar resultado: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un resultado específico mediante los IDs de prueba y parámetro asociados[cite: 2].
     * @param idPrueba Identificador de la prueba[cite: 2].
     * @param idParametro Identificador del parámetro[cite: 2].
     * @return El ResultadoEntidad encontrado, o null si no existe el registro[cite: 2].
     */
    public ResultadoEntidad buscarPorPruebayParametro(Long idPrueba, Long idParametro) {
        EntityManager em = conexion.getEntityManager();
        try {
            // Usamos una consulta JPQL para buscar el registro único
            String jpql = "SELECT r FROM ResultadoEntidad r WHERE r.prueba.id_prueba = :idPrueba AND r.parametro.id_parametro = :idParametro";

            return em.createQuery(jpql, ResultadoEntidad.class)
                    .setParameter("idPrueba", idPrueba)
                    .setParameter("idParametro", idParametro)
                    .getSingleResult();
        } catch (Exception e) {
            // Si no se encuentra, retorna null
            return null;
        } finally {
            em.close();
        }
    }
}
