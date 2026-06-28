/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.AnalisisEntidad;
import entidades.ResultadoEntidad;
import exception.PersistenciaException;
import interfaces.IAnalisisDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */
public class AnalisisDAO implements IAnalisisDAO {

    private IConexionBD conexion;

    public AnalisisDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<AnalisisEntidad> consultarAnalisisPorPrueba(Long idPrueba) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            // Queremos obtener AnalisisEntidad
            CriteriaQuery<AnalisisEntidad> criteria = builder.createQuery(AnalisisEntidad.class);
            Root<ResultadoEntidad> root = criteria.from(ResultadoEntidad.class);

            // JOIN: Resultado -> Parametro -> Analisis
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
}
