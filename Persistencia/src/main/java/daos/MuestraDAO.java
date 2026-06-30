/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.MuestraEntidad;
import exception.PersistenciaException;
import interfaces.IMuestraDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rafaelgb
 */
public class MuestraDAO implements IMuestraDAO {

    private final IConexionBD conexion;

    public MuestraDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<MuestraEntidad> consultarTodas() throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<MuestraEntidad> criteria = builder.createQuery(MuestraEntidad.class);
            Root<MuestraEntidad> root = criteria.from(MuestraEntidad.class);

            criteria.select(root).orderBy(builder.asc(root.get("nombre")));

            return em.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar las muestras: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public MuestraEntidad buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            MuestraEntidad muestra = em.find(MuestraEntidad.class, id);

            if (muestra == null) {
                throw new PersistenciaException("No se encontró la muestra con el Id: " + id);
            }

            return muestra;
        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar la muestra por Id: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
