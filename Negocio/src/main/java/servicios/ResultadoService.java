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

    public void registrarResultado(Long idParametro, Long idPrueba, String valor,String observacion) throws Exception {
        if (idParametro == null || idPrueba == null) {
            throw new Exception("Error: Datos incompletos (ID nulo).");
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            ParametroEntidad param = em.find(ParametroEntidad.class, idParametro);
            PruebaEntidad prueba = em.find(PruebaEntidad.class, idPrueba);

            if (param == null || prueba == null) {
                throw new Exception("Entidad no encontrada.");
            }

            ResultadoEntidad res = new ResultadoEntidad();
            res.setParametro(param);
            res.setPrueba(prueba);
            res.setValor_obtenido(valor);
            // Asignamos la observación capturada desde la interfaz
            res.setObservacion(observacion != null && !observacion.isEmpty() ? observacion : "Sin observación");

            em.persist(res);
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
