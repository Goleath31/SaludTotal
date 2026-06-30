/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.AnalisisEntidad;
import entidades.ParametroEntidad;
import entidades.RangoEvaluacionEntidad;
import entidades.ResultadoEntidad;
import exception.PersistenciaException;
import interfaces.IAnalisisDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */
/**
 * Clase encargada de realizar las operaciones de persistencia relacionadas con
 * Analisis.
 */
public class AnalisisDAO implements IAnalisisDAO {

    private IConexionBD conexion;

    public AnalisisDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Consulta los análisis asociados a una prueba específica utilizando
     * Criteria API. Realiza un recorrido desde Resultado -> Parametro ->
     * Analisis.
     *
     * * @param idPrueba ID de la prueba para filtrar.
     * @return Lista de análisis encontrados.
     * @throws PersistenciaException Si ocurre un error en la conexión o
     * ejecución.
     */
    @Override
    public List<AnalisisEntidad> consultarAnalisisPorPrueba(Long idPrueba) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<AnalisisEntidad> criteria = builder.createQuery(AnalisisEntidad.class);
            Root<ResultadoEntidad> root = criteria.from(ResultadoEntidad.class);

            Join<ResultadoEntidad, javax.persistence.criteria.Path> paramJoin = root.join("parametro");
            Join<Object, AnalisisEntidad> analisisJoin = paramJoin.join("analisis");

            criteria.select(analisisJoin).distinct(true);
            criteria.where(builder.equal(root.get("prueba").get("id_prueba"), idPrueba));

            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar análisis por prueba: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public AnalisisEntidad guardar(AnalisisEntidad nuevoAnalisis) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoAnalisis);
            em.getTransaction().commit();

            return nuevoAnalisis;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al guardar análisis" + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Da de baja lógica el análisis y, en cascada, sus parámetros y rangos de
     * evaluación, registrando la fecha de eliminación en vez de borrarlos
     * físicamente. Esto preserva el historial de resultados de pacientes que
     * referencian estos parámetros, así como los reportes que navegan los
     * rangos directamente vía relación (sin pasar por el filtro de catálogo).
     */
    @Override
    public AnalisisEntidad eliminar(AnalisisEntidad analisis) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();

            Date fechaEliminacion = new Date();
            analisis.setFechaEliminacion(fechaEliminacion);
            if (analisis.getParametros() != null) {
                for (ParametroEntidad parametro : analisis.getParametros()) {
                    parametro.setFechaEliminacion(fechaEliminacion);
                    if (parametro.getRangos() != null) {
                        for (RangoEvaluacionEntidad rango : parametro.getRangos()) {
                            rango.setFechaEliminacion(fechaEliminacion);
                        }
                    }
                }
            }

            AnalisisEntidad actualizado = em.merge(analisis);
            em.getTransaction().commit();

            return actualizado;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al eliminar análisis" + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public AnalisisEntidad buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<AnalisisEntidad> criteria = builder.createQuery(AnalisisEntidad.class);
            Root<AnalisisEntidad> root = criteria.from(AnalisisEntidad.class);

            criteria.select(root).where(builder.equal(root.get("nombre"), nombre));

            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException ex) {
            throw new PersistenciaException("No se encontró el análisis con el nombre: " + nombre);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el analisis por nombre" + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public AnalisisEntidad buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            AnalisisEntidad analisis = em.find(AnalisisEntidad.class, id);

            if (analisis == null) {
                throw new PersistenciaException("No se encontró el analisis con el Id: " + id);
            }

            return analisis;
        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el analisis por Id" + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<AnalisisEntidad> consultarTodos(int pagina, int tamanoPagina) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            int offset = (pagina - 1) * tamanoPagina;

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<AnalisisEntidad> criteria = builder.createQuery(AnalisisEntidad.class);
            Root<AnalisisEntidad> root = criteria.from(AnalisisEntidad.class);

            criteria.select(root)
                    .where(builder.isNull(root.get("fechaEliminacion")))
                    .orderBy(builder.asc(root.get("id_analisis")));

            TypedQuery<AnalisisEntidad> query = em.createQuery(criteria);
            query.setFirstResult(offset);
            query.setMaxResults(tamanoPagina);

            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar el catálogo de análisis: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public long contarTodos() throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
            Root<AnalisisEntidad> root = criteria.from(AnalisisEntidad.class);

            criteria.select(builder.count(root)).where(builder.isNull(root.get("fechaEliminacion")));

            return em.createQuery(criteria).getSingleResult();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al contar el catálogo de análisis: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<AnalisisEntidad> buscarPorFiltro(String texto) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<AnalisisEntidad> criteria = builder.createQuery(AnalisisEntidad.class);
            Root<AnalisisEntidad> root = criteria.from(AnalisisEntidad.class);

            Predicate porNombre = builder.like(builder.lower(root.get("nombre")), "%" + texto.toLowerCase() + "%");
            Predicate noEliminado = builder.isNull(root.get("fechaEliminacion"));

            criteria.select(root).where(builder.and(porNombre, noEliminado)).orderBy(builder.asc(root.get("id_analisis")));

            return em.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al filtrar el catálogo de análisis: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
