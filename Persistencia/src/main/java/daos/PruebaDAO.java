/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import dtos.ClientePruebaCompletaDTO;
import entidades.PruebaEntidad;
import exception.PersistenciaException;
import interfaces.IPruebaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */

/**
 * Clase de acceso a datos para la entidad Prueba.
 */
public class PruebaDAO implements IPruebaDAO {

    private IConexionBD conexion;

    public PruebaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Consulta todas las pruebas asociadas a un doctor.
     * @param idDoctor Identificador del doctor.
     * @return Lista de pruebas.
     * @throws PersistenciaException Si ocurre un error de base de datos.
     */
    public List<PruebaEntidad> consultarPorDoctor(Long idDoctor) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<PruebaEntidad> criteria = builder.createQuery(PruebaEntidad.class);
            Root<PruebaEntidad> root = criteria.from(PruebaEntidad.class);

            criteria.select(root).where(builder.equal(root.get("doctor").get("id_doctor"), idDoctor));

            return em.createQuery(criteria).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca una prueba específica mediante su folio.
     * @param folio Folio de la prueba.
     * @return La prueba encontrada.
     * @throws PersistenciaException Si no existe la prueba o hay error.
     */
    public PruebaEntidad buscarPorFolio(String folio) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<PruebaEntidad> criteria = builder.createQuery(PruebaEntidad.class);
            Root<PruebaEntidad> root = criteria.from(PruebaEntidad.class);
            criteria.select(root).where(builder.equal(root.get("folio"), folio));
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("No se encontró la prueba con folio: " + folio);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene una lista de DTOs con clientes que tienen sus pruebas completamente llenas.
     * @return Lista de ClientePruebaCompletaDTO.
     */
    @Override
    public List<ClientePruebaCompletaDTO> obtenerClientesConPruebasCompletas() {
        EntityManager em = conexion.getEntityManager();
        try {
            String jpql = "SELECT new dtos.ClientePruebaCompletaDTO(p.folio, p.cliente.nombre) "
                    + "FROM PruebaEntidad p "
                    + "WHERE NOT EXISTS (SELECT r FROM ResultadoEntidad r "
                    + "WHERE r.prueba = p AND (r.valor_obtenido IS NULL OR r.valor_obtenido = '' OR r.observacion IS NULL OR r.observacion = ''))";

            return em.createQuery(jpql, ClientePruebaCompletaDTO.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PruebaDAO.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public PruebaEntidad guardar(PruebaEntidad nuevaPrueba) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevaPrueba);
            em.getTransaction().commit();

            return nuevaPrueba;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar la solicitud: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<PruebaEntidad> consultarTodos(int pagina, int tamanoPagina) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            int offset = (pagina - 1) * tamanoPagina;

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<PruebaEntidad> criteria = builder.createQuery(PruebaEntidad.class);
            Root<PruebaEntidad> root = criteria.from(PruebaEntidad.class);

            criteria.select(root).orderBy(builder.desc(root.get("id_prueba")));

            TypedQuery<PruebaEntidad> query = em.createQuery(criteria);
            query.setFirstResult(offset);
            query.setMaxResults(tamanoPagina);

            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar las solicitudes: " + ex.getMessage());
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
            Root<PruebaEntidad> root = criteria.from(PruebaEntidad.class);

            criteria.select(builder.count(root));

            return em.createQuery(criteria).getSingleResult();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al contar las solicitudes: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<PruebaEntidad> buscarPorFiltro(String texto) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<PruebaEntidad> criteria = builder.createQuery(PruebaEntidad.class);
            Root<PruebaEntidad> root = criteria.from(PruebaEntidad.class);

            String comodin = "%" + texto.toLowerCase() + "%";
            Predicate porFolio = builder.like(builder.lower(root.get("folio")), comodin);
            Predicate porCliente = builder.like(builder.lower(root.get("cliente").get("nombre")), comodin);

            criteria.select(root).where(builder.or(porFolio, porCliente)).orderBy(builder.desc(root.get("id_prueba")));

            return em.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al filtrar las solicitudes: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
