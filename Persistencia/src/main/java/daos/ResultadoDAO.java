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
 *
 * @author golea
 */
public class ResultadoDAO implements IResultadoDAO {

    private IConexionBD conexion;

    public ResultadoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

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
