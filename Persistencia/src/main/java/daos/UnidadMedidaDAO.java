/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.UnidadMedidaEntidad;
import exception.PersistenciaException;
import interfaces.IUnidadMedidaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rafaelgb
 */
public class UnidadMedidaDAO implements IUnidadMedidaDAO {

    private final IConexionBD conexion;

    public UnidadMedidaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<UnidadMedidaEntidad> consultarTodas() throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<UnidadMedidaEntidad> criteria = builder.createQuery(UnidadMedidaEntidad.class);
            Root<UnidadMedidaEntidad> root = criteria.from(UnidadMedidaEntidad.class);

            criteria.select(root).orderBy(builder.asc(root.get("nombre")));

            return em.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar las unidades de medida: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public UnidadMedidaEntidad buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            UnidadMedidaEntidad unidad = em.find(UnidadMedidaEntidad.class, id);

            if (unidad == null) {
                throw new PersistenciaException("No se encontró la unidad de medida con el Id: " + id);
            }

            return unidad;
        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar la unidad de medida por Id: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
