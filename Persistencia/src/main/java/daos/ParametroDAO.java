/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.DoctorEntidad;
import entidades.ParametroEntidad;
import exception.PersistenciaException;
import interfaces.IDoctorDAO;
import interfaces.IParametroDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author golea
 */

/**
 * Clase que gestiona el acceso a datos para la entidad ParametroEntidad.
 */
public class ParametroDAO implements IParametroDAO {

    private IConexionBD conexion;

    /**
     * Constructor que inyecta la conexión a la base de datos.
     * @param conexion Interfaz para obtener el EntityManager.
     */
    public ParametroDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Consulta los parámetros vinculados a un análisis dado su ID.
     * @param idAnalisis Identificador único del análisis.
     * @return Lista de parámetros encontrados.
     */
    public List<ParametroEntidad> consultarPorAnalisis(Long idAnalisis) {
        EntityManager em = conexion.getEntityManager();
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaQuery<ParametroEntidad> criteria = builder.createQuery(ParametroEntidad.class);
        Root<ParametroEntidad> root = criteria.from(ParametroEntidad.class);
        
        criteria.select(root).where(builder.equal(root.get("analisis").get("id_analisis"), idAnalisis));
        return em.createQuery(criteria).getResultList();
        
    }

   
}
