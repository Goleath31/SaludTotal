/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import entidades.ResultadoEntidad;
import exception.PersistenciaException;
import interfaces.IResultadoDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author golea
 */
public class ResultadoDAO implements IResultadoDAO {

    private IConexionBD conexion;

    public ResultadoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(ResultadoEntidad resultado) throws PersistenciaException {
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(resultado);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar resultado: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
