/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.PruebaEntidad;
import exception.PersistenciaException;
import interfaces.IPruebaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */
public class PruebaDAO implements IPruebaDAO {

    private IConexionBD conexion;

    // Inyectamos la interfaz en el constructor
    public PruebaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    public List<PruebaEntidad> consultarPorDoctor(Long idDoctor) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<PruebaEntidad> criteria = builder.createQuery(PruebaEntidad.class);
            Root<PruebaEntidad> root = criteria.from(PruebaEntidad.class);

            // Filtro: WHERE doctor.id_doctor = :idDoctor
            criteria.select(root).where(builder.equal(root.get("doctor").get("id_doctor"), idDoctor));

            return em.createQuery(criteria).getResultList();
        } finally {
            em.close();
        }
    }

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
    
    
}
