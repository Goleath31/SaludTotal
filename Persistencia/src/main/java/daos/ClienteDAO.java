/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.ClienteEntidad;
import exception.PersistenciaException;
import interfaces.IClienteDAO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */
public class ClienteDAO implements IClienteDAO {

    private IConexionBD conexion;

    // Inyectamos la interfaz en el constructor
    public ClienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public ClienteEntidad consultarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ClienteEntidad> criteria = builder.createQuery(ClienteEntidad.class);
            Root<ClienteEntidad> root = criteria.from(ClienteEntidad.class);

            // Filtro: WHERE nombre = ?
            criteria.where(builder.equal(root.get("nombre"), nombre));

            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null; // O lanza una excepción propia
        } finally {
            em.close();
        }
    }

}
