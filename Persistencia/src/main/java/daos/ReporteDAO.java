/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexionBD;
import dtos.ReportePacienteDTO;
import dtos.ResultadoReporteDTO;
import entidades.ParametroEntidad;
import entidades.PruebaEntidad;
import entidades.RangoEvaluacionEntidad;
import entidades.ResultadoEntidad;
import interfaces.IReporteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implementación de la interfaz IReporteDAO para la gestión de acceso a datos 
 * relacionados con los reportes de pacientes.
 * * @author golea
 */
public class ReporteDAO implements IReporteDAO {

    private IConexionBD conexion;

    /**
     * Constructor que inicializa la conexión con la base de datos.
     * * @param conexion Implementación de la interfaz IConexionBD para gestionar el EntityManager.
     */
    public ReporteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Obtiene los datos necesarios para generar un reporte de un paciente basado en un folio de prueba.
     * * @param folio El identificador único de la prueba médica.
     * @return Un objeto ReportePacienteDTO con la información del paciente, doctor, fecha y resultados, 
     * o null si ocurre un error o el folio no existe.
     */
    @Override
    public ReportePacienteDTO obtenerDatosReporte(String folio) {
        EntityManager em = conexion.getEntityManager();
        try {
            String jpql = "SELECT p FROM PruebaEntidad p WHERE p.folio = :folio";
            TypedQuery<PruebaEntidad> query = em.createQuery(jpql, PruebaEntidad.class);
            query.setParameter("folio", folio);
            PruebaEntidad prueba = query.getSingleResult();

            List<ResultadoReporteDTO> listaResultados = new ArrayList<>();
            String nombreAnalisis = "Sin análisis";

            for (ResultadoEntidad res : prueba.getResultados()) {
                ParametroEntidad p = res.getParametro();

                nombreAnalisis = (p.getAnalisis() != null) ? p.getAnalisis().getNombre() : "N/A";

                String unidad = (p.getUnidadMedida() != null) ? p.getUnidadMedida().getNombre() : "N/A";

                String vMin = "N/A", vMax = "N/A";
                if (p.getRangos() != null && !p.getRangos().isEmpty()) {
                    vMin = String.valueOf(p.getRangos().get(0).getRangoInicial());
                    vMax = String.valueOf(p.getRangos().get(0).getRangoFinal());
                }

                listaResultados.add(new ResultadoReporteDTO(
                        p.getNombre(), res.getValor_obtenido(), res.getObservacion(), unidad, vMin, vMax
                ));
            }

            return new ReportePacienteDTO(
                    prueba.getFolio(),
                    prueba.getCliente().getNombre() + " " + prueba.getCliente().getApellido_paterno(),
                    prueba.getCliente().getSexo(),
                    prueba.getDoctor().getNombre() + " " + prueba.getDoctor().getApellido_paterno(),
                    prueba.getFechaHoraGeneracion().toString(),
                    nombreAnalisis,
                    listaResultados
            );
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}

