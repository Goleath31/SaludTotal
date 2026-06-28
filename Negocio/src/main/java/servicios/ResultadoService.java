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
 *
 * @author golea
 */
public class ResultadoService {

    private final EntityManagerFactory emf;

    public ResultadoService() {
        // Ajusta "TuUnidadPersistencia" al nombre que definiste en tu persistence.xml
        this.emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

//    public void registrarResultado(Long idParametro, Long idPrueba, String valor,String observacion) throws Exception {
//        if (idParametro == null || idPrueba == null) {
//            throw new Exception("Error: Datos incompletos (ID nulo).");
//        }
//
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//
//            ParametroEntidad param = em.find(ParametroEntidad.class, idParametro);
//            PruebaEntidad prueba = em.find(PruebaEntidad.class, idPrueba);
//
//            if (param == null || prueba == null) {
//                throw new Exception("Entidad no encontrada.");
//            }
//
//            ResultadoEntidad res = new ResultadoEntidad();
//            res.setParametro(param);
//            res.setPrueba(prueba);
//            res.setValor_obtenido(valor);
//            // Asignamos la observación capturada desde la interfaz
//            res.setObservacion(observacion != null && !observacion.isEmpty() ? observacion : "Sin observación");
//
//            em.persist(res);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            throw e;
//        } finally {
//            em.close();
//        }
//    }
//}
    public void registrarResultado(Long idParametro, Long idPrueba, String valor, String observacion) throws Exception {
        if (idParametro == null || idPrueba == null) {
            throw new Exception("Error: Datos incompletos.");
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // 1. Intentar buscar si ya existe un resultado para este parámetro y prueba
            // Asegúrate de que los nombres de los campos en la query coincidan con los de tu entidad
            String jpql = "SELECT r FROM ResultadoEntidad r WHERE r.prueba.id_prueba = :idPrueba AND r.parametro.id_parametro = :idParametro";

            List<ResultadoEntidad> resultados = em.createQuery(jpql, ResultadoEntidad.class)
                    .setParameter("idPrueba", idPrueba)
                    .setParameter("idParametro", idParametro)
                    .getResultList();

            ResultadoEntidad res;

            if (!resultados.isEmpty()) {
                // Si existe, usamos el existente
                res = resultados.get(0);
            } else {
                // Si no existe, creamos uno nuevo
                res = new ResultadoEntidad();
                res.setParametro(em.find(ParametroEntidad.class, idParametro));
                res.setPrueba(em.find(PruebaEntidad.class, idPrueba));
            }

            // 2. Actualizar o establecer los valores
            res.setValor_obtenido(valor);
            res.setObservacion(observacion != null && !observacion.isEmpty() ? observacion : "Sin observación");

            // 3. Persistir o actualizar (Merge)
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
