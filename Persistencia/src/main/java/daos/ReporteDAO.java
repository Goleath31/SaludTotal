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
 *
 * @author golea
 */
public class ReporteDAO implements IReporteDAO {

    private IConexionBD conexion;

    public ReporteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public ReportePacienteDTO obtenerDatosReporte(String folio) {
        EntityManager em = conexion.getEntityManager();
        try {
            // Buscamos la prueba por folio
            String jpql = "SELECT p FROM PruebaEntidad p WHERE p.folio = :folio";
            TypedQuery<PruebaEntidad> query = em.createQuery(jpql, PruebaEntidad.class);
            query.setParameter("folio", folio);

            PruebaEntidad prueba = query.getSingleResult();

            // Mapeo de Entidad a DTO
            List<ResultadoReporteDTO> listaResultados = new ArrayList<>();

            for (ResultadoEntidad res : prueba.getResultados()) {
                ParametroEntidad p = res.getParametro();

                // 1. Obtener Unidad de Medida (a través de la relación)
                String unidad = (p.getUnidadMedida() != null) ? p.getUnidadMedida().getNombre() : "N/A";

                // 2. Obtener Rango (usando el primero disponible como ejemplo)
                String vMin = "N/A";
                String vMax = "N/A";
                if (p.getRangos() != null && !p.getRangos().isEmpty()) {
                    // Aquí podrías agregar lógica para filtrar por sexo si lo requieres
                    RangoEvaluacionEntidad rango = p.getRangos().get(0);
                    vMin = String.valueOf(rango.getRangoInicial());
                    vMax = String.valueOf(rango.getRangoFinal());
                }

                // 3. Crear el DTO con los 6 parámetros requeridos por el constructor
                listaResultados.add(new ResultadoReporteDTO(
                        p.getNombre(),
                        res.getValor_obtenido(),
                        res.getObservacion(),
                        unidad,
                        vMin,
                        vMax
                ));
            }

            return new ReportePacienteDTO(
                    prueba.getFolio(),
                    prueba.getCliente().getNombre() + " " + prueba.getCliente().getApellido_paterno(),
                    prueba.getCliente().getSexo(),
                    prueba.getDoctor().getNombre() + " " + prueba.getDoctor().getApellido_paterno(),
                    prueba.getFechaHoraGeneracion().toString(),
                    listaResultados
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
