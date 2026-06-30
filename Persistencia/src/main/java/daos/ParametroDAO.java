/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.ParametroEntidad;
import entidades.RangoEvaluacionEntidad;
import exception.PersistenciaException;
import interfaces.IParametroDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */

/**
 * Clase que gestiona el acceso a datos para la entidad ParametroEntidad.
 */
public class ParametroDAO implements IParametroDAO {

    private IConexionBD conexion;

    /**
     * Constructor que inyecta la conexión a la base de datos.
     * @param conexion Interfaz para obtener el EntityManager.
     */
    public ParametroDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Consulta los parámetros vinculados a un análisis dado su ID.
     * @param idAnalisis Identificador único del análisis.
     * @return Lista de parámetros encontrados.
     */
    public List<ParametroEntidad> consultarPorAnalisis(Long idAnalisis) {
        EntityManager em = conexion.getEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<ParametroEntidad> criteria = builder.createQuery(ParametroEntidad.class);
        Root<ParametroEntidad> root = criteria.from(ParametroEntidad.class);

        criteria.select(root).where(builder.equal(root.get("analisis").get("id_analisis"), idAnalisis));
        return em.createQuery(criteria).getResultList();

    }

    @Override
    public ParametroEntidad guardar(ParametroEntidad nuevoParametro) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoParametro);
            em.getTransaction().commit();

            return nuevoParametro;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar parámetro" + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public ParametroEntidad buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ParametroEntidad> criteria = builder.createQuery(ParametroEntidad.class);
            Root<ParametroEntidad> root = criteria.from(ParametroEntidad.class);

            criteria.select(root).where(builder.equal(root.get("nombre"), nombre));

            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException ex) {
            throw new PersistenciaException("No se encontro el parámetro con el nombre: " + nombre);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar parámetro por nombre" + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Da de baja lógica el parámetro y, en cascada, sus rangos de evaluación.
     */
    @Override
    public ParametroEntidad eliminar(ParametroEntidad parametro) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();

            Date fechaEliminacion = new Date();
            parametro.setFechaEliminacion(fechaEliminacion);
            if (parametro.getRangos() != null) {
                for (RangoEvaluacionEntidad rango : parametro.getRangos()) {
                    rango.setFechaEliminacion(fechaEliminacion);
                }
            }

            ParametroEntidad actualizado = em.merge(parametro);
            em.getTransaction().commit();

            return actualizado;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar parámetro: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
