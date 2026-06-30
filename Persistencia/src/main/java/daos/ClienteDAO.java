/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.ClienteEntidad;
import exception.PersistenciaException;
import interfaces.IClienteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */

/**
 * Clase que gestiona el acceso a datos para la entidad ClienteEntidad.
 */
public class ClienteDAO implements IClienteDAO {

    private IConexionBD conexion;

    /**
     * Constructor que inyecta la dependencia de conexión a base de datos.
     * @param conexion Objeto que gestiona la persistencia.
     */
    public ClienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Consulta un cliente por su nombre utilizando JPA Criteria API.
     * @param nombre Nombre del cliente a buscar.
     * @return El ClienteEntidad encontrado o null si no existe.
     * @throws PersistenciaException En caso de errores en la conexión o ejecución.
     */
    @Override
    public ClienteEntidad consultarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ClienteEntidad> criteria = builder.createQuery(ClienteEntidad.class);
            Root<ClienteEntidad> root = criteria.from(ClienteEntidad.class);

            criteria.where(builder.equal(root.get("nombre"), nombre));

            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public ClienteEntidad buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            ClienteEntidad cliente = em.find(ClienteEntidad.class, id);

            if (cliente == null) {
                throw new PersistenciaException("No se encontró el cliente con el Id: " + id);
            }

            return cliente;
        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el cliente por Id: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<ClienteEntidad> buscarPorFiltro(String texto) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ClienteEntidad> criteria = builder.createQuery(ClienteEntidad.class);
            Root<ClienteEntidad> root = criteria.from(ClienteEntidad.class);

            String comodin = "%" + texto.toLowerCase() + "%";
            Predicate porNombre = builder.like(builder.lower(root.get("nombre")), comodin);
            Predicate porApellidoPaterno = builder.like(builder.lower(root.get("apellido_paterno")), comodin);
            Predicate porApellidoMaterno = builder.like(builder.lower(root.get("apellido_materno")), comodin);

            criteria.select(root).where(builder.or(porNombre, porApellidoPaterno, porApellidoMaterno));

            return em.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al filtrar clientes: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

}
