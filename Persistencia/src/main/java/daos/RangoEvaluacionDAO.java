/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.RangoEvaluacionEntidad;
import exception.PersistenciaException;
import interfaces.IRangoEvaluacionDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rafaelgb
 */
public class RangoEvaluacionDAO implements IRangoEvaluacionDAO {

    private final IConexionBD conexion;

    public RangoEvaluacionDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public RangoEvaluacionEntidad guardar(RangoEvaluacionEntidad nuevoRango) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoRango);
            em.getTransaction().commit();

            return nuevoRango;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar rango" + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<RangoEvaluacionEntidad> buscarPorParametro(Long idParametro) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<RangoEvaluacionEntidad> criteria = builder.createQuery(RangoEvaluacionEntidad.class);
            Root<RangoEvaluacionEntidad> root = criteria.from(RangoEvaluacionEntidad.class);

            criteria.select(root).where(builder.equal(root.get("parametro").get("id_parametro"), idParametro));

            return em.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar rangos por parámetro: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public RangoEvaluacionEntidad eliminar(RangoEvaluacionEntidad rango) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();

            rango.setFechaEliminacion(new Date());
            RangoEvaluacionEntidad actualizado = em.merge(rango);

            em.getTransaction().commit();

            return actualizado;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar rango: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
