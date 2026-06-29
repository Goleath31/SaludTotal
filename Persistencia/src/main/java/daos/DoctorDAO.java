/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.ConexionBD;
import conexion.IConexionBD;
import entidades.DoctorEntidad;
import exception.PersistenciaException;
import interfaces.IDoctorDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */

/**
 * Clase encargada de la persistencia y acceso a datos de DoctorEntidad.
 */
public class DoctorDAO implements IDoctorDAO {

    private IConexionBD conexion;

    /**
     * Constructor que recibe la implementación de la conexión.
     * @param conexion Interfaz de conexión a base de datos.
     */
    public DoctorDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Recupera todos los doctores registrados en la base de datos.
     * @return Lista de objetos DoctorEntidad.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<DoctorEntidad> consultarTodos() throws PersistenciaException {
        // Obtenemos el EntityManager del singleton que creamos anteriormente
        EntityManager em = conexion.getEntityManager();

        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();

            CriteriaQuery<DoctorEntidad> criteria = builder.createQuery(DoctorEntidad.class);

            Root<DoctorEntidad> root = criteria.from(DoctorEntidad.class);

            criteria.select(root);

            TypedQuery<DoctorEntidad> query = em.createQuery(criteria);
            return query.getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar médicos con Criteria API: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
