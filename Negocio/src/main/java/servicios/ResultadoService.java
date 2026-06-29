/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.ConexionBD;
import conexion.IConexionBD;
import daos.ResultadoDAO;
import entidades.ParametroEntidad;
import entidades.PruebaEntidad;
import entidades.ResultadoEntidad;
import interfaces.IResultadoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Servicio encargado de gestionar el registro y actualización de resultados clínicos.
 * Implementa lógica transaccional para garantizar la integridad de los datos.
 * @author golea
 */
public class ResultadoService {

    private final EntityManagerFactory emf;

    /**
     * Constructor que inicializa la fábrica de conexiones JPA.
     */
    public ResultadoService() {
        this.emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    /**
     * Registra o actualiza el resultado de un parámetro dentro de una prueba.
     * @param idParametro ID del parámetro médico.
     * @param idPrueba ID de la prueba médica.
     * @param valor El valor numérico o clínico obtenido.
     * @param observacion Notas adicionales sobre el resultado.
     * @throws Exception Si ocurre un error durante la transacción en la base de datos.
     */
    public void registrarResultado(Long idParametro, Long idPrueba, String valor, String observacion) throws Exception {
        if (idParametro == null || idPrueba == null) {
            throw new Exception("Error: Datos incompletos.");
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

           
            String jpql = "SELECT r FROM ResultadoEntidad r WHERE r.prueba.id_prueba = :idPrueba AND r.parametro.id_parametro = :idParametro";

            List<ResultadoEntidad> resultados = em.createQuery(jpql, ResultadoEntidad.class)
                    .setParameter("idPrueba", idPrueba)
                    .setParameter("idParametro", idParametro)
                    .getResultList();

            ResultadoEntidad res;

            if (!resultados.isEmpty()) {
                res = resultados.get(0);
            } else {
                res = new ResultadoEntidad();
                res.setParametro(em.find(ParametroEntidad.class, idParametro));
                res.setPrueba(em.find(PruebaEntidad.class, idPrueba));
            }

            res.setValor_obtenido(valor);
            res.setObservacion(observacion != null && !observacion.isEmpty() ? observacion : "Sin observación");

            if (res.getId_resultado() == null) {
                em.persist(res);
            } else {
                em.merge(res);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
