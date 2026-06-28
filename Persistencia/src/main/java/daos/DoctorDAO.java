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
public class DoctorDAO implements IDoctorDAO {

    private IConexionBD conexion;

    // Inyectamos la interfaz en el constructor
    public DoctorDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    // Singleton para el EntityManagerFactory
    @Override
    public List<DoctorEntidad> consultarTodos() throws PersistenciaException {
        // Obtenemos el EntityManager del singleton que creamos anteriormente
        EntityManager em = conexion.getEntityManager();

        try {
            // 1. Iniciamos el constructor de criterios
            CriteriaBuilder builder = em.getCriteriaBuilder();

            // 2. Definimos que queremos obtener DoctorEntidad
            CriteriaQuery<DoctorEntidad> criteria = builder.createQuery(DoctorEntidad.class);

            // 3. Definimos la raíz (la tabla FROM)
            Root<DoctorEntidad> root = criteria.from(DoctorEntidad.class);

            // 4. Seleccionamos la raíz (SELECT * equivalente)
            criteria.select(root);

            // 5. Ejecutamos la consulta
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
